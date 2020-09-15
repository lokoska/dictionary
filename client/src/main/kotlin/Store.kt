import lib.Mvi
import network.requestStr

val store = Mvi.store<ApplicationState, AppIntent, SideEffect>(
    ApplicationState(),
    sideEffectHandler = { store, effect ->
        when (effect) {
            is SideEffect.LoadDeployTime -> {
                requestStr("build_date.txt")
                    .onSuccess { str ->
                        store.dispatch(AppIntent.SetDeployTime(str))
                    }.onFailure {
                        store.dispatch(AppIntent.SetDeployTime("offline"))
                    }
            }
        }.let {}
    }
) { state, intent ->
    when (intent) {
        is AppIntent.LoadDeployTime -> {
            SideEffect.LoadDeployTime.onlySideEffect()
        }
        is AppIntent.SetDeployTime -> {
            state.copy(
                deployTime = intent.deployTime
            ).onlyState()
        }
    }
}
