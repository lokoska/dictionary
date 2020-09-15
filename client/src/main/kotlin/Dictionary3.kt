@OptIn(ExperimentalStdlibApi::class)
val dictionary3
    get() = Dictionary(
        name = "Немецко - русский",
        useByDefault = true,
        words = buildList {
            word("Ich sits ünter Leninstraße", "Я сижу на улице ленина")
            word("Vogel", "Птица")
            word("Katzen", "Кошка")
        }
    )
