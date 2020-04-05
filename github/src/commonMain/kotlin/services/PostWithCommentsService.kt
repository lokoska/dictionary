package services

import model.GitHubRepo

expect class PostWithCommentsService {
    suspend fun getGitHubRepos(organization:String): List<GitHubRepo>

}