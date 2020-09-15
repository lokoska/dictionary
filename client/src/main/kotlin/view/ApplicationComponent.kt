package view

import AppIntent
import ApplicationState
import lib.MviComponent
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.px
import react.dom.div
import react.dom.h2
import store
import styled.css
import styled.styledDiv

class ApplicationComponent : MviComponent<ApplicationState, AppIntent>(
    store,
    { state ->
        styledDiv {
            css {
                padding(32.px, 16.px)
            }
            h2 {
                +("Deploy time: " + state.deployTime)
            }
            div {
                styledDiv {
                    css {
                        marginBottom = 32.px
                    }
//                    gitHubRepoView(
//                        GitHubRepoProps(
//                            gitHubRepo = repo,
//                            onClickCommitLogBtn = {
//                                store.dispatch(
//                                    AppIntent.LoadCommits(
//                                        repo.organization,
//                                        repo.name
//                                    )
//                                )
//                            }
//                        )
//                    )
                }
            }
        }
    }
)