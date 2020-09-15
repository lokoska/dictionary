package mvi

import ApplicationState
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
            state.copy(
                dictionary = intent.dictionary
            ).onlyState()
        }
    }
}
