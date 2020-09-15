interface Storage {
    fun getItem(key: String)
    fun saveItem(key: String, item: StoreItem)
}

class StoreItem(
    val successCount: Int,
    val failCount: Int,
    val changedUnixTime: Int
)

object BrowserStorage:Storage {

    override fun getItem(key: String) {
        kotlin.browser.localStorage.getItem(key.hashCode().toString())
        TODO("Not yet implemented")
    }

    override fun saveItem(key: String, item: StoreItem) {
//        kotlin.browser.localStorage.setItem(key.hashCode().toString(), item)
        TODO("Not yet implemented")
    }

}
