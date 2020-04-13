import lib.renderReactMviComponent
import view.ApplicationComponent
import kotlin.browser.document

fun main() {
    document.getElementById("react-app")?.renderReactMviComponent<ApplicationComponent>()
    store.dispatch(AppIntent.LoadRepos("Kotlin"))
    store.dispatch(AppIntent.LoadDeployTime)
}
