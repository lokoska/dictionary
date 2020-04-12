package view

import Intent
import SideEffect
import github.GitHubRepo
import github.GitHubService
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.px
import lib.MviElm
import network.requestStr
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h2
import styled.css
import styled.styledDiv

interface ApplicationProps : RProps {

}

data class ApplicationState(
    val deployTime: String = "",
    val organization: String = "",
    val gitHubRepos: List<GitHubRepo> = emptyList()
) : RState

typealias StoreType = MviElm.Store<ApplicationState, Intent>

suspend fun sideEffectHandler(store: StoreType, effect: SideEffect) {
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
                    store.dispatch(Intent.SetDeployTime(str))
                }.onFailure {
                    store.dispatch(Intent.SetDeployTime("offline"))
                }

        }
    }.let {}
}

fun MviElm.ReduceContext<ApplicationState, SideEffect>.reducer(state: ApplicationState, intent: Intent): MviElm.Reduce<ApplicationState, SideEffect> =
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


class ApplicationComponent : RComponent<ApplicationProps, ApplicationState>() {
    private val store = MviElm.store(
        ApplicationState(),
        ::sideEffectHandler
    ) { a, b->
        reducer(a,b)
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
                                    store.dispatch(Intent.LoadCommits(repo.organization, repo.name))
                                }
                            )
                        )
                    }
                }

            }
        }
    }

}
