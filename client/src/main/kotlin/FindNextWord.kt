import kotlin.random.Random

fun findNextWord(word: Word?, words: List<Word>, storage: Storage): Word {
    return if (word != null && words.size > 1) {
        words - word
    } else {
        words
    }.randomWithWeight { (storage.getItem(it.hint) ?: StoreItem()).weight() }
        ?: throw Error("words count <= 1")
}

fun <E> Collection<E>.randomWithWeight(calcWeight: (E) -> Double): E? {
    val list = toList()
    val weights = list.map { calcWeight(it) }
    val rnd = Random.nextDouble(weights.sum())
    var sum: Double = 0.0
    for (i in weights.indices) {
        sum += weights[i]
        if (sum >= rnd) {
            return list[i]
        }
    }
    return lastOrNull()
}

fun StoreItem.weight():Double {
    val timeKoef: Double = 1.0 + (currentUnixTime() - changedUnixTime) / 1e3 / 60 / 60 / 24
    val goodKoef: Double = 1.0 / (1.0 + successCount) / (1.0 + successCount)
    val badKoef: Double = 1.0 + failCount
    return timeKoef * goodKoef * badKoef
}
