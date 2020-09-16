@OptIn(ExperimentalStdlibApi::class)
val dictionary5
    get() = Dictionary(
        name = "Словарь5",
        useByDefault = true,
        words = buildList {
            word("Слово3", "Перевод3")
            word("Слово4", "Перевод4")
        }
    )
    
