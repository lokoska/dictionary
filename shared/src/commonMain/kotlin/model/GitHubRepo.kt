package model

import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepo(
    val imageUrl: String,
    val name: String,
    val organization: String,
    val commitLogs:List<CommitInfo> = emptyList()
)
