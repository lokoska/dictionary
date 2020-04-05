package model

data class GitHubRepo(
    val imageUrl: String,
    val name: String,
    val organization: String,
    val commitLogs:List<CommitInfo> = emptyList()
)
