//package network
//
//import kotlinx.coroutines.await
//import org.w3c.fetch.RequestInit
//import kotlin.browser.window
//import kotlin.js.json
//
//enum class Method {
//    GET,
//    POST
//}
//
//suspend fun requestStr(
//    url: String,
//    method: Method,
//    headers: Map<String, String>,
//    body: String? = null
//): Result<String> {
//    val response = window.fetch(
//        url,
//        RequestInit(
//            method = method.toString(),
//            headers = json().apply {
//                headers.entries.forEach {
//                    set(it.key, it.value)
//                }
//            },
//            body = body ?: undefined
//        )
//    ).await()
//    return if (response.ok) {
//        Result.success<String>(response.text().await())
//    } else {
//        Result.failure<String>(Exception(response.statusText))
//    }
//}