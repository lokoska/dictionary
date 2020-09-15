fun findNextWord(word: Word?, words: List<Word>, storage: Storage): Word {
    return if (word != null && words.size > 1) {
        words - word
    } else {
        words
    }.first()
}
