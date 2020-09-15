import kotlinext.js.invoke
import kotlinx.css.*
import lib.renderReactMviComponent
import mvi.Intent
import mvi.store
import styled.StyledComponents
import view.ApplicationComponent
import kotlin.browser.document

fun main() {
    val styles = CSSBuilder(allowClasses = false).apply {
        body {
            margin(0.px)
            padding(0.px)
        }
    }
    StyledComponents.createGlobalStyle(styles.toString())

    document.getElementById("react-app")?.renderReactMviComponent<ApplicationComponent>()
    store.dispatch(Intent.LoadDeployTime)
}
