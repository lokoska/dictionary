package contrib.ringui.header

import contrib.ringui.RingUI
import react.RBuilder
import react.RClass
import react.RHandler
import react.dom.WithClassName

@JsModule("@jetbrains/ring-ui/components/header/header")
internal external object HeaderModule {
    val Logo: RClass<HeaderLogoProps>
}

// https://github.com/JetBrains/ring-ui/blob/master/components/header/header.js
external interface HeaderProps : WithClassName

fun RBuilder.ringHeader(handler: RHandler<HeaderProps>) {
    RingUI.Header {
        handler()
    }
}
