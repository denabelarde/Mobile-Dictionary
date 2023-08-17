package com.kotlin101.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlin101.dictionary.feature_dictionary.domain.model.Meaning
import com.kotlin101.dictionary.feature_dictionary.domain.model.Phonetic
import com.kotlin101.dictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val phonetic: String,
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            word = word,
            phonetic = phonetic,
            phonetics = phonetics
        )
    }
}
