package view

import contrib.ringui.island.ringIsland
import contrib.ringui.island.ringIslandContent
import contrib.ringui.island.ringIslandHeader
import contrib.ringui.ringButton
import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import model.GitHubRepo
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv

object PostStyles : StyleSheet("PostStyles", isStatic = true) {
    val noComments by css {
        marginBottom = 8.px
    }

    val body by css {
        +noComments

        paddingBottom = 8.px
        borderBottom(1.px, BorderStyle.solid, Color("#000").withAlpha(0.1))
    }

    val comment by css {
        +body

        lastOfType {
            borderBottomStyle = BorderStyle.none
        }
    }
}

interface PostProps : RProps {
    var gitHubRepo: GitHubRepo
    var onMoreComments: () -> Unit
}

class PostState : RState {
    var noMore: Boolean = false
    var loading: Boolean = false
}

class PostView : RComponent<PostProps, PostState>() {
    private val repo
        get() = props.gitHubRepo

    private val commitLogs
        get() = props.gitHubRepo.commitLogs

    init {
        state = PostState()
    }

    override fun componentDidUpdate(prevProps: PostProps, prevState: PostState, snapshot: Any) {
        if (state.loading && prevProps != props) {
            setState {

                noMore = prevProps.gitHubRepo.commitLogs.size == props.gitHubRepo.commitLogs.size
                loading = false
            }
        }
    }

    override fun RBuilder.render() {
        ringIsland {
            ringIslandHeader {
                attrs {
                    border = true
                }
                +repo.name
            }

            ringIslandContent {
                userView(props.gitHubRepo.name, props.gitHubRepo.imageUrl) {
                    css {
                        marginBottom = 16.px
                    }
                }

                styledDiv {
                    css {
                        if (commitLogs.isNotEmpty()) {
                            +PostStyles.body
                        } else {
                            +PostStyles.noComments
                        }
                    }
                    +"Additional info"
                }

                commitLogs.forEach {
                    commentView(it) {
                        css {
                            +PostStyles.comment
                        }
                    }
                }

                if (!state.noMore) {
                    ringButton {
                        attrs {
                            loader = state.loading
                            onMouseDown = {
                                setState {
                                    loading = true
                                }
                                props.onMoreComments()
                            }
                        }

                        +"Load more comments"
                    }
                }
            }
        }
    }
}

fun RBuilder.postView(
    post: GitHubRepo,
    onLoadCommmits: () -> Unit,
    handler: RHandler<PostProps> = {}
) {
    child(PostView::class) {
        attrs.gitHubRepo = post
        attrs.onMoreComments = onLoadCommmits
        handler()
    }
}