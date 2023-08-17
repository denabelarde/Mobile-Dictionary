package com.kotlin101.dictionary.feature_dictionary.domain.use_case

import com.kotlin101.dictionary.feature_dictionary.domain.model.WordInfo
import com.kotlin101.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.kotlin101.dictionary.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}