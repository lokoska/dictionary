import kotlinx.html.currentTimeMillis
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

interface Storage {
    fun getItem(key: String): StoreItem?
    fun saveItem(key: String, item: StoreItem)
}

@Serializable
data class StoreItem(
    val successCount: Int = 0,
    val failCount: Int = 0,
    val changedUnixTime: Int = 0
)

object BrowserStorage : Storage {

    override fun getItem(key: String): StoreItem? {
        try {
            val fromStorage: String? =
                kotlinx.browser.localStorage.getItem(key.hashCode().toString())

            if (fromStorage != null) {
                return Json.decodeFromString(StoreItem.serializer(), fromStorage)
            } else {
                return null
            }
        } catch (t: Throwable) {
            return null
        }
    }

    override fun saveItem(key: String, item: StoreItem) {
        kotlinx.browser.localStorage.setItem(
            key.hashCode().toString(),
            Json.encodeToString(StoreItem.serializer(), item)
        )
    }

    fun clear() {
        kotlinx.browser.localStorage.clear()
    }

}

fun currentUnixTime() = (currentTimeMillis() / 1000).toInt()
