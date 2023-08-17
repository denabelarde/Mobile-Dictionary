package com.kotlin101.dictionary.feature_dictionary.domain.repository

import com.kotlin101.dictionary.feature_dictionary.domain.model.WordInfo
import com.kotlin101.dictionary.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}