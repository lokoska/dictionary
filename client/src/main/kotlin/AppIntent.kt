import github.CommitInfo
import github.GitHubRepo

sealed class AppIntent {
    object LoadDeployTime : AppIntent()
    class LoadRepos(val organization: String) : AppIntent()
    class LoadCommits(val organization: String, val repoName: String) : AppIntent()
    class LoadedCommits(val repoName:String, val commits: List<CommitInfo>) : AppIntent()
    class LoadedRepos(val repos: List<GitHubRepo>) : AppIntent()
    class SetDeployTime(val deployTime: String) : AppIntent()

}

sealed class SideEffect {
    object LoadDeployTime: SideEffect()
    class LoadRepos(val organization: String): SideEffect()
    class LoadCommits(val organization: String, val repoName: String) : SideEffect()
}
