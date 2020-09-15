import react.RState

data class ApplicationState(
    val deployTime: String = "",
    val dictionary: Dictionary? = null
) : RState
