package services

import model.CommitInfo
import network.requestStr
import kotlin.coroutines.CoroutineContext

class CommentsService(coroutineContext: CoroutineContext) {

    suspend fun loadCommitLog(organization:String, repo:String, count: Int = 5): List<CommitInfo> {
        val response = requestStr("https://api.github.com/repos/$organization/$repo/commits")
        val jsonStr: String = response.getOrThrow()
        val dynamicArray: Array<dynamic> = JSON.parse(jsonStr)
        return dynamicArray.map { obj: dynamic ->
            CommitInfo(
                author = obj.commit.author.email,
                title = obj.commit.message,
                time = obj.commit.author.date
            )
        }
    }
}