package services

import model.GitHubRepo
import kotlin.coroutines.CoroutineContext

actual class PostWithCommentsService(coroutineContext: CoroutineContext) {

    actual suspend fun getGitHubRepos(organization: String): List<GitHubRepo> = listOf(
        GitHubRepo(
            name = "kotlin-examples",
            imageUrl = "https://avatars3.githubusercontent.com/u/1446536?v=4",
            organization = organization
        ),
        GitHubRepo(
            name = "kotlin-examples 2",
            imageUrl = "https://avatars3.githubusercontent.com/u/1446536?v=4",
            organization = organization
        )
    )
}