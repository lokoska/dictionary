package view

import kotlinx.css.FontWeight
import kotlinx.css.fontWeight
import kotlinx.css.marginRight
import kotlinx.css.px
import model.CommitInfo
import react.RBuilder
import styled.DIVBuilder
import styled.css
import styled.styledDiv
import styled.styledSpan

fun RBuilder.commentView(commit: CommitInfo, builder: DIVBuilder) {
    styledDiv {
        styledSpan {
            css {
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