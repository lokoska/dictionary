package view

import github.GitHubRepo
import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import styled.css
import styled.styledDiv

interface GitHubRepoProps : RProps {
    var gitHubRepo: GitHubRepo
    var onClickCommitLogBtn: () -> Unit
}

class GitHubRepoView : RComponent<GitHubRepoProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                padding(all = 10.px)
                backgroundColor = Color.lightGray
            }

            repoView(props.gitHubRepo) {
                css {
                    marginBottom = 16.px
                }
            }

            styledDiv {
                css {
                    marginBottom = 8.px
                    if (props.gitHubRepo.commitLogs.isNotEmpty()) {
                        paddingBottom = 8.px
                        borderBottom(1.px, BorderStyle.solid, Color("#000").withAlpha(0.1))
                    }
                }
                +props.gitHubRepo.description
            }

            props.gitHubRepo.commitLogs.forEach {
                commitView(it) {
                    css {
                        lastOfType {
                            borderBottomStyle = BorderStyle.dashed
                        }
                    }
                }
            }

            button {
                attrs {
                    onClickFunction = {
                        props.onClickCommitLogBtn()
                    }
                }
                +"Load commit history"
            }
        }
    }
}

fun RBuilder.gitHubRepoView(
    post: GitHubRepo,
    onLoadCommits: () -> Unit
) {
    child(GitHubRepoView::class) {
        attrs.gitHubRepo = post
        attrs.onClickCommitLogBtn = onLoadCommits
    }
}
