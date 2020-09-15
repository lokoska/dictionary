
sealed class AppIntent {
    object LoadDeployTime : AppIntent()
    class SetDeployTime(val deployTime: String) : AppIntent()
}

sealed class SideEffect {
    object LoadDeployTime: SideEffect()
}
