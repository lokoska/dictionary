import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import network.requestStr
import react.buildElement
import react.dom.render
import view.ApplicationComponent
import kotlin.browser.document
import kotlin.coroutines.CoroutineContext

private class Application : CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    fun start() {
        document.getElementById("react-app")?.let {
            render(buildElement {
                child(ApplicationComponent::class) {
                    attrs.coroutineScope = this@Application
                }
            }, it)
        }
        launch {
            println("make request to github")
            requestStr("build_date.txt").onSuccess {
                println(it)
            }.onFailure {

            }

        }
    }
}

fun main() {
    GlobalStyles.inject()
    Application().start()
}
