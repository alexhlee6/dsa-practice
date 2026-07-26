class WordDictionary {
    // val root: Array<MutableList<Letter>> = Array(27) {mutableListOf<Letter>()}
    val root: Letter = Letter('/')

    fun addWord(word: String) {
        val firstChar = word[0]
        val firstIndex = word[0] - 'a'
        root.nextLetters.add(firstChar)

        var curr = Letter(word[0])
        root.next[firstIndex].add(curr)

        for (i in 1 until word.length) {
            val char = word[i]
            val charIdx = word[i] - 'a'
            curr.nextLetters.add(char)
            val letter = Letter(char)
            curr.next[charIdx].add(letter)
            curr = letter
        }
        curr.isEndLetter = true 
    }

    fun search(word: String): Boolean {
        val firstChar = word[0]
        if (firstChar == '.') {
            // check every letter instance for every char
            for (char in root.nextLetters) {
                val charIdx = char - 'a'
                val matchingLetters = root.next[charIdx]
                for (letter in matchingLetters) {
                    if (search(word, 0, letter)) {
                        return true
                    }
                }
            }
            return false
        }

        // first char is NOT '.'
        val charIdx = firstChar - 'a'
        val matchingLetters = root.next[charIdx]
        for (letter in matchingLetters) {
            if (search(word, 0, letter)) {
                return true
            }
        }

        return false
    }

    fun search(word: String, i: Int = 0, letter: Letter): Boolean {
        if (i == word.length-1) {
            return (word[i] == '.' || word[i] == letter.char) && letter.isEndLetter 
        } else if (i >= word.length) {
            return false
        }

        if (word[i] != '.' && letter.char != word[i]) {
            return false
        } 

        // current letter guaranteed valid (match or '.'), next char exists

        if (word[i+1] == '.') { // next char is '.'
            for (char in letter.nextLetters) {
                val idx = char - 'a'
                for (l in letter.next[idx]) {
                    if (search(word, i+1, l)) {
                        return true
                    }
                }
            }
            return false
        }

        val nextChar = word[i+1]
        if (!letter.nextLetters.contains(nextChar)) {
            return false
        }
        val nextLetterIdx = nextChar - 'a'
        val nextLetters = letter.next[nextLetterIdx]
        for (l in nextLetters) {
            if (search(word, i+1, l)) {
                return true
            }
        }
        return false
    }
}

data class Letter(
    val char: Char, 
    val nextLetters: MutableSet<Char> = mutableSetOf(), 
    var isEndLetter: Boolean = false,
    val next: Array<MutableList<Letter>> = Array(26) { mutableListOf<Letter>() }
)