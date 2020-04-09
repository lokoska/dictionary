package view

import github.GitHubRepo
import github.GitHubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.px
import lib.Redux
import network.requestStr
import react.*
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

class ApplicationState : RState {
    var deployTime = "loading deploy time"
    var organization = "Kotlin"
    var gitHubRepos: List<GitHubRepo> = emptyList()
}

class ApplicationComponent : RComponent<ApplicationProps, ApplicationState>() {
    init {
        val store = Redux.store(ApplicationState()) { s, a: Action ->
            s
        }
        store.subscribeToState {
            setState(it)
        }
        state = store.state
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext

    override fun componentDidMount() {
        props.coroutineScope.launch {
            GitHubService.getGitHubRepos("Kotlin").onSuccess {
                setState(ApplicationState())
                setState {
                    gitHubRepos += it
                }
            }
        }

        props.coroutineScope.launch {
            requestStr("build_date.txt")
                .onSuccess { str ->
                    setState {
                        deployTime = str
                    }
                }.onFailure {
                    setState {
                        deployTime = "offline"
                    }
                }
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +ApplicationStyles.wrapper
            }
            h2 {
                +("Deploy time: " + state.deployTime)
            }
        }

        styledDiv {
            css {
                +ApplicationStyles.wrapper
            }

            state.gitHubRepos.map { repo: GitHubRepo ->
                styledDiv {
                    css {
                        +ApplicationStyles.post
                    }
                    gitHubRepoView(
                        repo,
                        onLoadCommmits = {
                            onLoadCommitsLog(repo.organization, repo.name)
                        }
                    )
                }
            }
        }
    }

    private fun onLoadCommitsLog(organization: String, repoName: String) {
        props.coroutineScope.launch {
            val commitLogs = GitHubService.loadCommitLog(organization, repoName)
                .onSuccess { commits ->
                    setState {
                        gitHubRepos = gitHubRepos.map {
                            if (it.name != repoName) it else it.copy(commitLogs = it.commitLogs + commits)
                        }
                    }
                }

        }
    }

}
