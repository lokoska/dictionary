package mvi

import Dictionary

sealed class Intent {
    object LoadDeployTime : Intent()
    class SetDeployTime(val deployTime: String) : Intent()
    class ChooseDictionary(val dictionary: Dictionary) : Intent()
    object StartWordScreen : Intent()
    class MarkCurrentWord(val success: Boolean) : Intent()
}

sealed class SideEffect {
    class StoreWord(val txt: String, val success: Boolean) : SideEffect()
    object LoadDeployTime : SideEffect()
}
