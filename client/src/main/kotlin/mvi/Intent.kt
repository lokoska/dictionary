package mvi

import Dictionary

sealed class Intent {
    object LoadDeployTime : Intent()
    class SetDeployTime(val deployTime: String) : Intent()
    class ChooseDictionary(val dictionary: Dictionary) : Intent()
}

sealed class SideEffect {
    object LoadDeployTime: SideEffect()
}
