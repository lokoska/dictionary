package view

import github.CommitInfo
import github.GitHubRepo
import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*
import styled.css
import styled.styledDiv
import styled.styledSpan

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
            userView(props.gitHubRepo)
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
                commitView(it)
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
    onClickCommitLogBtn: () -> Unit
) {
    child(GitHubRepoView::class) {
        attrs {
            gitHubRepo = post
            this.onClickCommitLogBtn = onClickCommitLogBtn
        }
    }
}

fun RBuilder.userView(repo: GitHubRepo) {
    styledDiv {
        css {
            marginBottom = 16.px
        }
        table {
            tr {
                th {
                    img(src = repo.imageUrl) {
                        attrs.width = "100px"
                    }
                }
                th {
                    h4 {
                        +repo.name
                    }
                    +repo.organization
                }
            }
        }
    }
}

fun RBuilder.commitView(commit: CommitInfo) {
    styledDiv {
        css {
            lastOfType {
                borderBottomStyle = BorderStyle.dashed
            }
        }
        styledSpan {
            +commit.time
        }
        styledSpan {
            css {
                marginLeft = 8.px
                marginRight = 8.px
                fontWeight = FontWeight.bold
            }
            +commit.author
        }
        styledSpan {
            +commit.title
        }
    }
}
