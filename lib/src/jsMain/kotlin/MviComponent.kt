import kotlinext.js.jsObject
import lib.Mvi
import org.w3c.dom.Element
import react.*
import react.dom.render

abstract class MviComponent<St, In, Se>(val store: Mvi.Store<St, In>) : RComponent<RProps, St>()
        where St : RState {

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
