fun MutableList<Word>.word(hint: String, secret: String) {
    add(Word(hint, secret))
}

@OptIn(ExperimentalStdlibApi::class)
val dictionaries get() = buildList {
    add(dictionary1)
    add(dictionary2)
    add(dictionary3)
}
