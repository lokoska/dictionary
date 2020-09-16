@OptIn(ExperimentalStdlibApi::class)
val dictionary4
    get() = Dictionary(
        name = "Словарь4",
        useByDefault = true,
        words = buildList {
            word("Слово", "Перевод")
            word("Слово2", "Перевод2")
        }
    )
