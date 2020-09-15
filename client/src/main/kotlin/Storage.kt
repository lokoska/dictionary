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
        TODO("Not yet implemented")
    }

    override fun saveItem(key: String, item: StoreItem) {
        TODO("Not yet implemented")
    }

}
