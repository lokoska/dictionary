package contrib.ringui

import react.RBuilder
import react.RHandler
import react.dom.WithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/icon/icon.js
external interface IconProps : WithClassName {
    var glyph: dynamic /* string | func */
}

