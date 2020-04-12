import react.buildElement
import react.dom.render
import view.ApplicationComponent
import kotlin.browser.document

fun main() {
    GlobalStyles.inject()
    render(
        buildElement {
            child(ApplicationComponent::class) {

            }
        },
        document.getElementById("react-app")!!
    )
}
