@OptIn(ExperimentalStdlibApi::class)
val dictionary2 get() = Dictionary(
    "Англо-русский",
    buildList {
        word("Dog", "Собака")
        word("Cat", "Кошка")
    }
)

