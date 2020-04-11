package view

import github.GitHubRepo
import github.GitHubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.px
import lib.MviElm
import network.requestStr
import react.*
import react.dom.div
import react.dom.h2
import styled.StyleSheet
import styled.css
import styled.styledDiv

private object ApplicationStyles : StyleSheet("ApplicationStyles", isStatic = true) {
    val wrapper by css {
        padding(32.px, 16.px)
    }

    val post by css {
        marginBottom = 32.px
    }
}

interface ApplicationProps : RProps {
    var coroutineScope: CoroutineScope
}

data class ApplicationState(
    val deployTime: String = "",
    val organization: String = "",
    val gitHubRepos: List<GitHubRepo> = emptyList()
) : RState

class ApplicationComponent : RComponent<ApplicationProps, ApplicationState>() {
    val store = MviElm.store(
        ApplicationState(),
        { store, effect: SideEffect ->
            fun Any.exhaustive() = Unit
            when (effect) {
                is SideEffect.LoadCommits -> {
                    GitHubService.loadCommitLog(effect.organization, effect.repoName)
                        .onSuccess { commits ->
                            store.dispatch(Intent.LoadedCommits(effect.repoName, commits))
                        }
                }
                is SideEffect.LoadRepos -> {
                    GitHubService.getGitHubRepos(effect.organization).onSuccess {
                        store.dispatch(Intent.LoadedRepos(it))
                    }
                }
                is SideEffect.LoadDeployTime -> {
                    requestStr("build_date.txt")
                        .onSuccess { str ->
                            setState {
                                store.dispatch(Intent.SetDeployTime(str))
                            }
                        }.onFailure {
                            setState {
                                store.dispatch(Intent.SetDeployTime("offline"))
                            }
                        }

                }
            }.exhaustive()
        }
    ) { state, intent: Intent ->
        when (intent) {
            is Intent.LoadCommits ->
                SideEffect.LoadCommits(intent.organization, intent.repoName).onlySideEffect()
            is Intent.LoadedCommits -> {
                state.copy(
                    gitHubRepos = state.gitHubRepos.map {
                        if (it.name != intent.repoName) it else it.copy(commitLogs = it.commitLogs + intent.commits)
                    }
                ).onlyState()
            }
            is Intent.LoadRepos -> {
                state.copy(
                    organization = intent.organization
                ) andEffect SideEffect.LoadRepos(intent.organization)
            }
            is Intent.LoadedRepos -> {
                state.copy(
                    gitHubRepos = intent.repos
                ).onlyState()
            }
            is Intent.LoadDeployTime -> {
                SideEffect.LoadDeployTime.onlySideEffect()
            }
            is Intent.SetDeployTime -> {
                state.copy(
                    deployTime = intent.deployTime
                ).onlyState()
            }
        }
    }

    init {
        store.subscribeToState { newState ->
            setState(transformState = { newState })
        }
        state = store.state
        store.dispatch(Intent.LoadRepos("Kotlin"))
        store.dispatch(Intent.LoadDeployTime)
    }

    override fun componentDidMount() {
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +ApplicationStyles.wrapper
            }
            h2 {
                +("Deploy time: " + state.deployTime)
            }
            div {
                state.gitHubRepos.forEach { repo: GitHubRepo ->
                    styledDiv {
                        css {
                            +ApplicationStyles.post
                        }
                        gitHubRepoView(repo) {
                            store.dispatch(Intent.LoadCommits(repo.organization, repo.name))
                        }
                    }
                }

            }
        }
    }

}
