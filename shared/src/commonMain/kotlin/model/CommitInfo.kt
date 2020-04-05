package model

import kotlinx.serialization.Serializable

@Serializable
data class CommitInfo(
    val author:String,
    val title:String,
    val time:String
)