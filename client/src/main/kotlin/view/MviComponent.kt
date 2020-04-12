package view

import AppIntent
import github.GitHubRepo
import lib.Mvi
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

data class ApplicationState(
    val deployTime: String = "",
    val organization: String = "",
    val gitHubRepos: List<GitHubRepo> = emptyList()
) : RState

typealias StoreType = Mvi.Store<ApplicationState, AppIntent>

abstract class MviComponent<St, In, Se> : RComponent<RProps, St>()
        where St : RState {

    abstract suspend fun sideEffectHandler2(store: Mvi.Store<St, In>, sideEffect: Se)
    abstract fun Mvi.ReduceContext<St, Se>.reducer(state: St, intent: In): Mvi.Reduce<St, Se>
    abstract fun initState(): St

    val store = Mvi.store<St, In, Se>(
        initState(),
        ::sideEffectHandler2,
        ::reducerWrapper
    )

    private fun reducerWrapper(reduceContext: Mvi.ReduceContext<St, Se>, state: St, intent: In): Mvi.Reduce<St, Se> {
        return reduceContext.reducer(state, intent)
    }

    init {
        store.subscribeToState { newState ->
            setState(transformState = { newState })
        }
        state = store.state
        afterInit(store)
    }

    abstract fun afterInit(store: Mvi.Store<St, In>)

    override fun componentDidMount() {
    }

    abstract fun RBuilder.render2(state: St)

    override fun RBuilder.render() {
        render2(state)
    }

}
