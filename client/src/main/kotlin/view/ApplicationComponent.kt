package view

import ApplicationState
import allDictionaries
import lib.MviComponent
import kotlinx.css.marginBottom
import kotlinx.css.padding
import kotlinx.css.px
import kotlinx.html.js.onClickFunction
import mvi.Intent
import mvi.store
import react.dom.br
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
                    when (state.screen) {
                        is Screen.Dictionaries -> {
                            allDictionaries.forEach { dictionary ->
                                styledDiv {
                                    val selected = state.screen.selected.contains(dictionary)
                                    checkBox(
                                        "Словарь ${dictionary.name} (${dictionary.words.size} слов)",
                                        selected
                                    ) {
                                        store.dispatch(Intent.ChooseDictionary(dictionary))
                                    }
                                }
                            }
                            button {
                                attrs {
                                    onClickFunction = {
                                        store.dispatch(Intent.StartWordScreen)
                                    }
                                }
                                +"Начать"
                            }
                            button {
                                attrs {
                                    onClickFunction = {
                                        //todo
                                    }
                                }
                                +"Очистить статистику"
                            }
                        }
                        is Screen.Words -> {
                            state.screen.words.forEach {
                                styledDiv {
                                    +"${it.hint} - ${it.secret}"
                                }
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
