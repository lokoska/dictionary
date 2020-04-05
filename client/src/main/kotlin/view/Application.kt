package view

import contrib.ringui.header.ringHeader
import contrib.ringui.header.ringLogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.paddingLeft
import kotlinx.css.px
import model.GitHubRepo
import network.requestStr
import react.*
import react.dom.h2
import services.CommentsService
import services.PostWithCommentsService
import styled.StyleSheet
import styled.css
import styled.styledA
import styled.styledDiv

val jetbrainsLogo = kotlinext.js.require("@jetbrains/logos/jetbrains/jetbrains-simple.svg")

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
        state = ApplicationState()
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext

    override fun componentDidMount() {
        val postWithCommentsService = PostWithCommentsService(coroutineContext)

        props.coroutineScope.launch {
            val repos = postWithCommentsService.getGitHubRepos("Kotlin")

            setState {
                gitHubRepos += repos
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
        ringHeader {
            styledA("/") {
                css {
                    specific {
                        paddingLeft = 48.px
                    }
                }
                ringLogo {
                    attrs {
                        className = "logo"
                        glyph = jetbrainsLogo
                    }
                }
            }
        }

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
        val commitLoadService = CommentsService(coroutineContext)
        props.coroutineScope.launch {
            val commitLogs = commitLoadService.loadCommitLog(organization, repoName)
            setState {
                gitHubRepos = gitHubRepos.map {
                    if (it.name != repoName) it else it.copy(commitLogs = it.commitLogs + commitLogs)
                }
            }
        }
    }

}
