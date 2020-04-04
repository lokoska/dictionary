package services

import model.PostWithComments

expect class PostWithCommentsService {
    suspend fun getPostsWithComments(): List<PostWithComments>

}