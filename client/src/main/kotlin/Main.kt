import github.GitHubRepo
import github.GitHubService
import kotlinext.js.jsObject
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.px
import lib.Mvi
import network.requestStr
import org.w3c.dom.Element
import react.*
import react.dom.div
import react.dom.h2
import react.dom.render
import styled.css
import styled.styledDiv
import view.MviComponent
import view.GitHubRepoProps
import view.gitHubRepoView
import view.renderReactMviComponent
import kotlin.browser.document

fun main() {
    GlobalStyles.inject()
    document.getElementById("react-app")
        ?.renderReactMviComponent<ApplicationComponent>()
}

data class ApplicationState(
    val deployTime: String = "",
    val organization: String = "",
    val gitHubRepos: List<GitHubRepo> = emptyList()
) : RState

class ApplicationComponent:MviComponent<ApplicationState, AppIntent, SideEffect>(
    ApplicationState()
) {

    override fun afterInit(store: Mvi.Store<ApplicationState, AppIntent>) {
        store.dispatch(AppIntent.LoadRepos("Kotlin"))
        store.dispatch(AppIntent.LoadDeployTime)
    }

    override fun Mvi.ReduceContext<ApplicationState, SideEffect>.reducer(
        state: ApplicationState,
        intent: AppIntent
    ): Mvi.Reduce<ApplicationState, SideEffect> = when (intent) {
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

    override fun RBuilder.render2(state: ApplicationState) {
        styledDiv {
            css {
                padding(32.px, 16.px)
            }
            h2 {
                +("Deploy time: " + state.deployTime)
            }
            div {
                state.gitHubRepos.forEach { repo: GitHubRepo ->
                    styledDiv {
                        css {
                            marginBottom = 32.px
                        }
                        gitHubRepoView(
                            GitHubRepoProps(
                                gitHubRepo = repo,
                                onClickCommitLogBtn = {
                                    store.dispatch(AppIntent.LoadCommits(repo.organization, repo.name))
                                }
                            )
                        )
                    }
                }

            }
        }
    }

    override suspend fun sideEffectHandler(store: Mvi.Store<ApplicationState, AppIntent>, effect: SideEffect) {
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


}