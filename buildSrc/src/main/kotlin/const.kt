import java.text.SimpleDateFormat
import java.util.*

val KOTLIN_VERSION = "1.4.10"
//val KOTLIN_WRAPPER_VERSION = "pre.94-kotlin-1.3.70"
val KOTLIN_WRAPPER_VERSION = "pre.110-kotlin-1.4.0"
val SERIALIZATION_VERSION = "1.0.0-RC"
val COROUTINES_VERSION = "1.3.9"
val BUILD_TIME_STR = Date().formatTo("yyyy-MM-dd' 'HH:mm", TimeZone.getTimeZone("Europe/Moscow"))

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

