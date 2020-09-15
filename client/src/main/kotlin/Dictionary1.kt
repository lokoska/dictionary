@OptIn(ExperimentalStdlibApi::class)
val dictionary1 get() = Dictionary(
    "Русско-английский",
    buildList {
        word("Кошка", "Cat")
        word("Собака", "Dog")
    }
)

