package mvi

import ApplicationState
import BrowserStorage
import findNextWord
import lib.Mvi
import network.requestStr

val store = Mvi.store<ApplicationState, Intent, SideEffect>(
    ApplicationState(),
    sideEffectHandler = { store, effect ->
        when (effect) {
            is SideEffect.LoadDeployTime -> {
                requestStr("build_date.txt")
                    .onSuccess { str ->
                        store.dispatch(Intent.SetDeployTime(str))
                    }.onFailure {
                        store.dispatch(Intent.SetDeployTime("offline"))
                    }
            }
            is SideEffect.StoreWord -> {
                //todo storage
            }
        }.let {}
    }
) { state, intent ->
    when (intent) {
        is Intent.LoadDeployTime -> {
            SideEffect.LoadDeployTime.onlySideEffect()
        }
        is Intent.SetDeployTime -> {
            state.copy(
                deployTime = intent.deployTime
            ).onlyState()
        }
        is Intent.ChooseDictionary -> {
            if (state.screen is Screen.Dictionaries) {
                val contains = state.screen.selected.contains(intent.dictionary)
                state.copy(
                    screen = state.screen.copy(
                        selected = if (contains) {
                            state.screen.selected - intent.dictionary
                        } else {
                            state.screen.selected + intent.dictionary
                        }
                    )
                ).onlyState()
            } else {
                doNothing
            }
        }
        is Intent.StartWordScreen -> {
            if (state.screen is Screen.Dictionaries) {
                val words = state.screen.selected.flatMap { it.words }.distinct()
                state.copy(
                    screen = Screen.Words(
                        words = words,
                        word = findNextWord(null, words, BrowserStorage)
                    )
                ).onlyState()
            } else {
                doNothing
            }
        }
        is Intent.MarkCurrentWord -> {
            if (state.screen is Screen.Words) {
                state.copy(
                    screen = state.screen.copy(
                        word = findNextWord(state.screen.word, state.screen.words, BrowserStorage)
                    )
                ).andEffect(
                    SideEffect.StoreWord(state.screen.word.hint, intent.success)
                )
            } else {
                doNothing
            }
        }
    }
}
