package contrib.ringui.island

import react.RElementBuilder
import react.RHandler
import react.dom.WithClassName

// https://github.com/JetBrains/ring-ui/blob/master/components/island/content.js
external interface IslandContentProps : WithClassName

fun RElementBuilder<IslandProps>.ringIslandContent(handler: RHandler<IslandContentProps>) {
    IslandModule.Content {
        handler()
    }
}