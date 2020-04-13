package github

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.test.Test

class GitHubServiceTest {

    @Test
    fun getGitHubRepos() {
        GlobalScope.launch {
            val result = GitHubService.getGitHubRepos("Kotlin")
            println(result)
        }
    }

}
