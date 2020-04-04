package services

import model.Comment
import kotlin.coroutines.CoroutineContext

class CommentsService(coroutineContext: CoroutineContext) {

    suspend fun getComments(postId: String, count: Int = 5): List<Comment> =
        List(count) {
            Comment(
                1,
                2,
                "name1",
                "mail1",
                "body text...."
            )
        }
}