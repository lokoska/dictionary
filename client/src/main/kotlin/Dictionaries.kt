fun MutableList<Word>.word(hint: String, secret: String) {
    add(Word(hint, secret))
}

@OptIn(ExperimentalStdlibApi::class)
val allDictionaries by lazy {
    buildList {
        add(dictionary1)
        add(dictionary2)
        add(dictionary3)
        add(dictionary4)
        add(dictionary5)
    }
}
