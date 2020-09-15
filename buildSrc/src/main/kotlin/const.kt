import java.text.SimpleDateFormat
import java.util.*

val KOTLIN_WRAPPER_VERSION = "pre.94-kotlin-1.3.70"
val SERIALIZATION_VERSION = "0.20.0"
val COROUTINES_VERSION = "1.3.3"
val BUILD_TIME_STR = Date().formatTo("yyyy-MM-dd' 'HH:mm", TimeZone.getTimeZone("Europe/Moscow"))

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

