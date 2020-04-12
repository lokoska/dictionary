package view

import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent

fun <T : RProps> stateLessRComponent(
    render2: RBuilder.(T) -> Unit
): RBuilder.(T) -> Unit {
    return { props ->
        child(
            functionalComponent = functionalComponent<T> {
                render2(it)
            },
            props = props
        )
    }
}

