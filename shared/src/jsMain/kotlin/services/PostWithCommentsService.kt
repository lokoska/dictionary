package services

import model.Comment
import model.Post
import model.PostWithComments
import rpc.Transport
import kotlin.coroutines.CoroutineContext

actual class PostWithCommentsService(coroutineContext: CoroutineContext) {
    private val transport = Transport(coroutineContext)

    actual suspend fun getPostsWithComments(): List<PostWithComments> {
        return listOf(
            PostWithComments(
                post = Post(1, 2, "user1", "body1"),
                comments = listOf(
                    Comment(1, 2, "comment1", "mail1@mail.com", "commend body")
                )
            )
        )
        return transport.getList("getPostsWithComments", PostWithComments.serializer())
    }
}