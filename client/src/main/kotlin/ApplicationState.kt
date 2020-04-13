import github.GitHubRepo
import react.RState

data class ApplicationState(
    val deployTime: String = "",
    val organization: String = "",
    val gitHubRepos: List<GitHubRepo> = emptyList()
) : RState