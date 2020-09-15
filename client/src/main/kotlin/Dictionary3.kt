@OptIn(ExperimentalStdlibApi::class)
val dictionary3 get() = Dictionary(
    "Немецко - русский",
    buildList {
        word("Ich sits ünter Leninstraße", "Я сижу на улице ленина")
    }
)
