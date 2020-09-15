package view

import State
import allDictionaries
import kotlinx.css.*
import lib.MviComponent
import kotlinx.html.js.onClickFunction
import mvi.Intent
import mvi.store
import react.dom.br
import react.dom.button
import react.dom.div
import react.dom.h2
import styled.css
import styled.styledDiv

class ApplicationComponent : MviComponent<State, Intent>(
    store,
    { state ->
        styledDiv {
            css {
                padding(32.px, 16.px)
            }

            div {
                styledDiv {
                    css {
                        marginBottom = 32.px
                        textAlign = TextAlign.center
                        fontSize = 14.pt
                    }
                    when (state.screen) {
                        is Screen.Dictionaries -> {
                            button {
                                attrs {
                                    onClickFunction = {
                                        //todo
                                    }
                                }
                                styledDiv {
                                    css {
                                        fontSize = 20.pt
                                    }
                                    +"Очистить статистику"
                                }
                            }
                            h2 {
                                +("Обновление словарей: " + state.deployTime)
                            }
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
                                styledDiv {
                                    css {
                                        fontSize = 30.pt
                                    }
                                    +"Начать"
                                }
                            }
                        }
                        is Screen.Words -> {
                            when (state.screen.wordState) {
                                is WordState.Hidden -> {
                                    styledDiv {
                                        +"Знаешь слово ?"
                                        br {}
                                        br {}
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.OpenWord)
                                                }
                                                styledDiv {
                                                    css {
                                                        fontSize = 20.pt
                                                    }
                                                    +"Показать перевод"
                                                }
                                            }
                                        }
                                        br {}
                                        styledDiv {
                                            css {
                                                fontWeight = FontWeight.bold
                                            }
                                            +state.screen.word.hint
                                        }
                                        br {}
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.MarkWord(true))
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = 30.pt
                                                    color = Color.darkGreen
                                                }
                                                +"Да"
                                            }
                                        }
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.MarkWord(false))
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = 30.pt
                                                    color = Color.darkRed
                                                }
                                                +"Нет"
                                            }
                                        }
                                    }
                                }
                                is WordState.Open -> {
                                    styledDiv {
                                        +"Знаешь слово ?"
                                        br {}
                                        br {}
                                        styledDiv {
                                            css {
//                                                fontWeight = FontWeight.bold
                                            }
                                            +state.screen.word.hint
                                        }
                                        styledDiv {
                                            css {
                                                fontWeight = FontWeight.bold
                                            }
                                            +state.screen.word.secret
                                        }

                                        br {}
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.MarkWord(true))
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = 30.pt
                                                    color = Color.darkGreen
                                                }
                                                +"Да"
                                            }
                                        }
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.MarkWord(false))
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = 30.pt
                                                    color = Color.darkRed
                                                }
                                                +"Нет"
                                            }
                                        }
                                    }
                                }
                                is WordState.Fail -> {
                                    styledDiv {
                                        +"Вот как правильно:"
                                        br {}
                                        br {}
                                        styledDiv {
                                            css {
//                                                fontWeight = FontWeight.bold
                                            }
                                            +state.screen.word.hint
                                        }
                                        styledDiv {
                                            css {
                                                fontWeight = FontWeight.bold
                                            }
                                            +state.screen.word.secret
                                        }

                                        br {}
                                        button {
                                            attrs {
                                                onClickFunction = {
                                                    store.dispatch(Intent.NextWord)
                                                }
                                            }
                                            styledDiv {
                                                css {
                                                    fontSize = 30.pt
                                                }
                                                +"Дальше"
                                            }
                                        }
                                    }
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
