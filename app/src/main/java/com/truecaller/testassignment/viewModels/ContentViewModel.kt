package com.truecaller.testassignment.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import com.truecaller.testassignment.data.repository.ContentRepository
import com.truecaller.testassignment.utils.network.ApiObjects.addSourceGeneric
import com.truecaller.testassignment.utils.network.ApiObjects.doIfConnectedResponse
import com.truecaller.testassignment.utils.network.ApiObjects.requestInTryCatch
import com.truecaller.testassignment.utils.network.ApiResponse
import com.truecaller.testassignment.utils.network.NetworkListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository,
    private val networkListener: NetworkListener,
) : ViewModel() {


    private val _findTenthCharacter: LiveEvent<ApiResponse<String>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val findTenthCharacter: LiveData<ApiResponse<String>> get() = _findTenthCharacter

    private val _findEveryTenthCharacter: LiveEvent<ApiResponse<String>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val findEveryTenthCharacter: LiveData<ApiResponse<String>> get() = _findEveryTenthCharacter

    private val _wordCounter: LiveEvent<ApiResponse<String>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val wordCounter: LiveData<ApiResponse<String>> get() = _wordCounter


    fun findTenthCharacter() {
        findTenthCharacterSafeCall()
    }

    fun findEveryTenthCharacter() {
        findEveryTenthCharacterSafeCall()
    }

    fun wordCounter() {
        wordCounterSafeCall()
    }

    private fun findTenthCharacterSafeCall() {
        _findTenthCharacter.requestInTryCatch {
            it.postValue(doIfConnectedResponse(networkListener) {
                val response =
                    contentRepository.remote.findTenthCharacter(
                    )
                _findTenthCharacter.addSourceGeneric(response)
            })
        }
    }

    private fun findEveryTenthCharacterSafeCall() {
        _findEveryTenthCharacter.requestInTryCatch {
            it.postValue(doIfConnectedResponse(networkListener) {
                val response =
                    contentRepository.remote.findEveryTenthCharacter(
                    )
                _findEveryTenthCharacter.addSourceGeneric(response)
            })
        }
    }

    private fun wordCounterSafeCall() {
        _wordCounter.requestInTryCatch {
            it.postValue(doIfConnectedResponse(networkListener) {
                val response =
                    contentRepository.remote.wordCounter(
                    )
                _wordCounter.addSourceGeneric(response)
            })
        }
    }
}