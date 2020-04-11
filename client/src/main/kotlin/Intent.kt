import github.CommitInfo
import github.GitHubRepo

sealed class Intent {
    object LoadDeployTime : Intent()
    class LoadRepos(val organization: String) : Intent()
    class LoadCommits(val organization: String, val repoName: String) : Intent()
    class LoadedCommits(val repoName:String, val commits: List<CommitInfo>) : Intent()
    class LoadedRepos(val repos: List<GitHubRepo>) : Intent()
    class SetDeployTime(val deployTime: String) : Intent()

}

sealed class SideEffect {
    object LoadDeployTime: SideEffect()
    class LoadRepos(val organization: String): SideEffect()
    class LoadCommits(val organization: String, val repoName: String) : SideEffect()
}
