package view

import github.GitHubRepo
import react.RBuilder
import react.dom.*
import styled.DIVBuilder
import styled.styledDiv

fun RBuilder.repoView(repo: GitHubRepo, builder: DIVBuilder = {}) {
    styledDiv {
        table {
            tr {
                th {
                    img(src = repo.imageUrl) {
                        attrs.width = "100px"
                    }
                }
                th {
                    h4 {
                        +repo.name
                    }
                    +repo.organization
                }
            }
        }
        builder()
    }
}