package github

import network.requestStr

object GitHubService {

    suspend fun getGitHubRepos(organization: String) =
        requestStr("https://api.github.com/orgs/$organization/repos")
            .map { jsonStr ->
                val dynamicArray: Array<dynamic> = JSON.parse(jsonStr)
                dynamicArray.map { obj: dynamic ->
                    GitHubRepo(
                        name = obj.name,
                        imageUrl = obj.owner.avatar_url,
                        organization = obj.owner.login,
                        description = obj.description
                    )
                }
            }

    suspend fun loadCommitLog(organization: String, repo: String, count: Int = 5) =
        requestStr("https://api.github.com/repos/$organization/$repo/commits")
            .map { jsonStr ->
                val dynamicArray: Array<dynamic> = JSON.parse(jsonStr)
                dynamicArray.map { obj: dynamic ->
                    CommitInfo(
                        author = obj.commit.author.email,
                        title = obj.commit.message,
                        time = obj.commit.author.date
                    )
                }
            }

}

