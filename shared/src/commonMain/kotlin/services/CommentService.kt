package services

import model.CommitInfo
import kotlin.coroutines.CoroutineContext

class CommentsService(coroutineContext: CoroutineContext) {

    suspend fun loadCommitLog(organization:String, repo:String, count: Int = 5): List<CommitInfo> =
        List(count) {
            CommitInfo(
                "user@user",
                "add something useful",
                "15:35 GMT"
            )
        }
}