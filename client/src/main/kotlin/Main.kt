import org.w3c.dom.Element
import react.buildElement
import react.dom.render
import view.ApplicationComponent
import kotlin.browser.document

private class Application {
    fun start() {
        val reactAppContainer: Element = document.getElementById("react-app")!!
        render(
            buildElement {
                child(ApplicationComponent::class) {

                }
            }, reactAppContainer
        )
    }
}

fun main() {
    GlobalStyles.inject()
    Application().start()
}
