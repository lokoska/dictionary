package network

import kotlinx.coroutines.await
import org.w3c.fetch.RequestInit
import kotlin.browser.window
import kotlin.js.json

suspend fun requestStr(
    hostPath: String,
    urlArgs: Map<String, String> = mapOf(),
    method: Method = Method.GET,
    headers: Map<String, String> = mapOf(),
    body: String? = null
): Result<String> {
    var url: String = hostPath
    if (urlArgs.isNotEmpty()) {
        url += "?"
        url += urlArgs.entries.joinToString("&", transform = { "${it.key}=${urlEncode(it.value)}" })
    }
    val response = window.fetch(
        url,
        RequestInit(
            method = method.toString(),
            headers = json().apply {
                headers.entries.forEach {
                    set(it.key, it.value)
                }
            },
            body = body ?: undefined
        )
    ).await()
    return if (response.ok) {
        Result.success<String>(response.text().await())
    } else {
        Result.failure<String>(Exception(response.statusText))
    }
}

enum class Method {
    GET,
    POST
}

@JsName("encodeURIComponent")
external fun urlEncode(value: String): String

@JsName("decodeURIComponent")
external fun urlDecode(encoded: String): String
