package view

import kotlinx.css.*
import model.CommitInfo
import react.RBuilder
import styled.DIVBuilder
import styled.css
import styled.styledDiv
import styled.styledSpan

fun RBuilder.commentView(commit: CommitInfo, builder: DIVBuilder) {
    styledDiv {
        styledSpan {
            +commit.time
        }
        styledSpan {
            css {
                marginLeft = 8.px
                marginRight = 8.px

                fontWeight = FontWeight.bold
            }

            +commit.author
        }

        styledSpan {
            +commit.title
        }

        builder()
    }
}