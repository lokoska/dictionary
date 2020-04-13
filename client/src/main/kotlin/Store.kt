import github.GitHubService
import lib.Mvi
import network.requestStr

val store = Mvi.store<ApplicationState, AppIntent, SideEffect>(
    ApplicationState(),
    sideEffectHandler = { store, effect ->
        when (effect) {
            is SideEffect.LoadCommits -> {
                GitHubService.loadCommitLog(effect.organization, effect.repoName)
                    .onSuccess { commits ->
                        store.dispatch(AppIntent.LoadedCommits(effect.repoName, commits))
                    }
            }
            is SideEffect.LoadRepos -> {
                GitHubService.getGitHubRepos(effect.organization).onSuccess {
                    store.dispatch(AppIntent.LoadedRepos(it))
                }
            }
            is SideEffect.LoadDeployTime -> {
                requestStr("build_date.txt")
                    .onSuccess { str ->
                        store.dispatch(AppIntent.SetDeployTime(str))
                    }.onFailure {
                        store.dispatch(AppIntent.SetDeployTime("offline"))
                    }
            }
        }.let {}
    }
) { state, intent ->
    when (intent) {
        is AppIntent.LoadCommits ->
            SideEffect.LoadCommits(intent.organization, intent.repoName).onlySideEffect()
        is AppIntent.LoadedCommits -> {
            state.copy(
                gitHubRepos = state.gitHubRepos.map {
                    if (it.name != intent.repoName) it else it.copy(commitLogs = it.commitLogs + intent.commits)
                }
            ).onlyState()
        }
        is AppIntent.LoadRepos -> {
            state.copy(
                organization = intent.organization
            ) andEffect SideEffect.LoadRepos(intent.organization)
        }
        is AppIntent.LoadedRepos -> {
            state.copy(
                gitHubRepos = intent.repos
            ).onlyState()
        }
        is AppIntent.LoadDeployTime -> {
            SideEffect.LoadDeployTime.onlySideEffect()
        }
        is AppIntent.SetDeployTime -> {
            state.copy(
                deployTime = intent.deployTime
            ).onlyState()
        }
    }
}
