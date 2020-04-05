package view

import contrib.ringui.UserCardModel
import contrib.ringui.ringUserCard
import react.RBuilder
import styled.DIVBuilder
import styled.styledDiv

fun RBuilder.userView(name:String, avatarUrl: String, builder: DIVBuilder = {}) {
    styledDiv {
        ringUserCard(
            UserCardModel(
                name = name,
                login = name,//todo ошибка если не передавать login
                avatarUrl = avatarUrl
            )
        )
        builder()
    }
}