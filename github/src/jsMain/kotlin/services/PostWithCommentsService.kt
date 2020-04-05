package services

import model.GitHubRepo
import network.requestStr
import kotlin.coroutines.CoroutineContext

actual class PostWithCommentsService(coroutineContext: CoroutineContext) {

    actual suspend fun getGitHubRepos(organization: String): List<GitHubRepo> {
        val response = requestStr("https://api.github.com/orgs/Kotlin/repos")
        val jsonStr: String = response.getOrThrow()
        val dynamicArray: Array<dynamic> = JSON.parse(jsonStr)
        return dynamicArray.map { obj: dynamic ->
            GitHubRepo(
                name = obj.name,
                imageUrl = obj.owner.avatar_url,
                organization = obj.owner.login,
                description = obj.description
            )
        }
    }
}
