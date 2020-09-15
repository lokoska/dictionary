import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

interface Storage {
    fun getItem(key: String): StoreItem?
    fun saveItem(key: String, item: StoreItem)
}

@Serializable
class StoreItem(
    val successCount: Int,
    val failCount: Int,
    val changedUnixTime: Int
)

object BrowserStorage : Storage {

    override fun getItem(key: String): StoreItem? {
        val fromStorage: String? =
            kotlinx.browser.localStorage.getItem(key.hashCode().toString())

        if (fromStorage != null) {
            return Json.decodeFromString(StoreItem.serializer(), fromStorage)
        } else {
            return null
        }
    }

    override fun saveItem(key: String, item: StoreItem) {
        kotlinx.browser.localStorage.setItem(
            key.hashCode().toString(),
            Json.encodeToString(StoreItem.serializer(), item)
        )
    }

}
