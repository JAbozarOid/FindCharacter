package com.truecaller.testassignment.data.repository.contentDataSource

import androidx.lifecycle.LiveData
import com.truecaller.testassignment.data.network.ContentApi
import com.truecaller.testassignment.utils.manager.FindCharacterManager
import com.truecaller.testassignment.utils.network.ApiResponse
import javax.inject.Inject

class ContentDataSource @Inject constructor(
    private var contentApi: ContentApi,
    private var findCharacterManager: FindCharacterManager
) {

    fun getContent(): LiveData<ApiResponse<String>> {
        return contentApi.getContent()
    }

    fun findTenthCharacter(content: String) {
        findCharacterManager.findTenthCharacter(text = content)
    }

    fun findEveryTenthCharacter(content: String) {
        findCharacterManager.findEveryTenthCharacter(text = content)
    }

    fun wordsCounter(content: String) {
        findCharacterManager.findOccurrenceOfEachWord(
            findCharacterManager.findWordsFromString(
                content
            )
        )
    }
}