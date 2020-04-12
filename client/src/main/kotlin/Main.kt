import kotlinext.js.jsObject
import org.w3c.dom.Element
import react.RComponent
import react.RProps
import react.buildElement
import react.createElement
import react.dom.render
import view.ApplicationComponent
import view.ApplicationState
import kotlin.browser.document

fun main() {
    GlobalStyles.inject()
    document.getElementById("react-app")
        ?.renderReactMviComponent<ApplicationComponent>()
}

inline fun <reified T> Element.renderReactMviComponent()
        where T : RComponent<out RProps, out ApplicationState> {
    render(
        buildElement {
            childList.add(createElement(T::class.js, jsObject<RProps> { }))
        },
        this
    )
}
