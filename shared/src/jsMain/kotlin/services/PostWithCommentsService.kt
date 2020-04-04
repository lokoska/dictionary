package services

import model.Comment
import model.Post
import model.PostWithComments
import kotlin.coroutines.CoroutineContext

actual class PostWithCommentsService(coroutineContext: CoroutineContext) {

    actual suspend fun getPostsWithComments(): List<PostWithComments> = listOf(
        PostWithComments(
            post = Post(1, 2, "user1", "body1"),
            comments = listOf(
                Comment(1, 2, "comment1", "mail1@mail.com", "commend body")
            )
        ),
        PostWithComments(
            post = Post(3, 5, "user2", "body1"),
            comments = listOf(
                Comment(4, 5, "comment1", "mail1@mail.com", "commend body")
            )
        )
    )
}