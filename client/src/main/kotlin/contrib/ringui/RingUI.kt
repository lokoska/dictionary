package contrib.ringui

import contrib.ringui.header.HeaderProps
import react.RClass

@JsModule("@jetbrains/ring-ui")
external object RingUI {
    val Header: RClass<HeaderProps>
}
