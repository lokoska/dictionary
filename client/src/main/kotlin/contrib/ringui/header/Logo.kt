package contrib.ringui.header

import contrib.ringui.IconProps
import kotlinx.html.A
import react.RHandler
import styled.StyledDOMBuilder

external interface HeaderLogoProps : IconProps

fun StyledDOMBuilder<A>.ringLogo(handler: RHandler<HeaderLogoProps>) {
    HeaderModule.Logo {
        handler()
    }
}
