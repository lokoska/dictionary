package view

import github.CommitInfo

sealed class Intent {
    class LoadCommits(val organization: String, val repoName: String) : Intent()
    class LoadedCommits(val repoName:String, val commits: List<CommitInfo>) : Intent()

}

sealed class SideEffect {
    class LoadCommits(val organization: String, val repoName: String) : SideEffect()
}
