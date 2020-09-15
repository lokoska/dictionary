package view

import ApplicationState
import dictionaries
import lib.MviComponent
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.px
import kotlinx.html.js.onClickFunction
import mvi.Intent
import mvi.store
import react.dom.button
import react.dom.div
import react.dom.h2
import styled.css
import styled.styledDiv

class ApplicationComponent : MviComponent<ApplicationState, Intent>(
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
                    if (state.dictionary != null) {
                        state.dictionary.words.forEach {
                            +"${it.hint} - ${it.secret}"
                        }
                    } else {
                        dictionaries.forEach { dictionary ->
                            button {
                                attrs {
                                    onClickFunction = {
                                        store.dispatch(Intent.ChooseDictionary(dictionary))
                                    }
                                }
                                +"Словарь ${dictionary.name}"
                            }
                        }
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
