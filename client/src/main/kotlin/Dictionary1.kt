@OptIn(ExperimentalStdlibApi::class)
val dictionary1
    get() = Dictionary(
        name = "Русско-английский",
        useByDefault = true,
        words = buildList {
            word("Кошка", "Cat")
            word("Собака", "Dog")
            word("Хомяк", "Hamster")
            word("Хомяк 2", "Hamster 2")
        }
    )

