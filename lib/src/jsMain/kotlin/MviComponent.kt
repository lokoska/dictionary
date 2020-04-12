import kotlinext.js.jsObject
import lib.Mvi
import org.w3c.dom.Element
import react.*
import react.dom.render

abstract class MviComponent<St, In, Se>(initState: St) : RComponent<RProps, St>()
        where St : RState {

    val store = Mvi.store<St, In, Se>(
        initState,
        ::sideEffectHandler,
        ::reducerWrapper
    )

    abstract suspend fun sideEffectHandler(store: Mvi.Store<St, In>, effect: Se)
    abstract fun Mvi.ReduceContext<St, Se>.reducer(state: St, intent: In): Mvi.Reduce<St, Se>

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

inline fun <reified Component> Element.renderReactMviComponent()
        where Component : RComponent<out RProps, out RState> {
    render(
        buildElement {
            childList.add(createElement(Component::class.js, jsObject<RProps> { }))
        },
        this
    )
}
