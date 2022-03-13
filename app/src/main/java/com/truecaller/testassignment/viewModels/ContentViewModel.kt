package com.truecaller.testassignment.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.truecaller.testassignment.data.repository.BlogContentRepository
import com.truecaller.testassignment.util.StringUtil.findEveryTenthCharacter
import com.truecaller.testassignment.util.StringUtil.findOccurrenceOfEachWord
import com.truecaller.testassignment.util.StringUtil.findTenthCharacter
import com.truecaller.testassignment.util.StringUtil.findWordsFromString
import com.truecaller.testassignment.util.network.ApiResponse
import com.truecaller.testassignment.util.network.NetworkUtil.flowResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val blogContentRepository: BlogContentRepository,
    application: Application
) : AndroidViewModel(application) {

    companion object {
        const val TAG = "ContentViewModel"
    }

    private val _isProgressEnable: MutableLiveData<Boolean> = MutableLiveData()
    val isProgressEnable: LiveData<Boolean> get() = _isProgressEnable

    private val _findTenthCharacter: MutableLiveData<String> = MutableLiveData()
    val findTenthCharacter: LiveData<String> get() = _findTenthCharacter

    private val _findEveryTenthCharacter: MutableLiveData<String> = MutableLiveData()
    val findEveryTenthCharacter: LiveData<String> get() = _findEveryTenthCharacter

    private val _findOccurrenceOfEachWord: MutableLiveData<String> = MutableLiveData()
    val findOccurrenceOfEachWord: LiveData<String> get() = _findOccurrenceOfEachWord

    private fun setProgressBarVisibility(isVisible: Boolean) {
        _isProgressEnable.value = isVisible
    }

    private fun showToastErrorMsg(errorMsg: String) {
        Toast.makeText(getApplication(), errorMsg, Toast.LENGTH_SHORT).show()
    }

    fun onRequestClicked() {
        getBlogContentsTenthCharacter()
        getBlogContentsEveryTenthCharacter()
        getBlogContentsOccurrenceOfEachWord()
    }

    private fun getBlogContentsTenthCharacter() {
        viewModelScope.launch {
            flowResponse { blogContentRepository.getBlogContent() }.collect {
                when (it) {
                    is ApiResponse.Loading -> {
                        setProgressBarVisibility(true)
                    }
                    is ApiResponse.Success -> {
                        setProgressBarVisibility(false)
                        withContext(Dispatchers.IO) {
                            _findTenthCharacter.postValue(findTenthCharacter(it.data))
                        }
                    }
                    is ApiResponse.Failure -> {
                        setProgressBarVisibility(false)
                        showToastErrorMsg(it.error)
                    }
                    is ApiResponse.Complete -> {
                        setProgressBarVisibility(false)

                    }
                }
            }
        }
    }

    private fun getBlogContentsEveryTenthCharacter() {
        viewModelScope.launch {
            flowResponse { blogContentRepository.getBlogContent() }.collect {
                when (it) {
                    is ApiResponse.Loading -> {
                        setProgressBarVisibility(true)
                    }
                    is ApiResponse.Success -> {
                        setProgressBarVisibility(false)
                        withContext(Dispatchers.IO) {
                            _findEveryTenthCharacter.postValue(findEveryTenthCharacter(it.data).toString())
                        }
                    }
                    is ApiResponse.Failure -> {
                        setProgressBarVisibility(false)
                        showToastErrorMsg(it.error)
                    }
                    is ApiResponse.Complete -> {
                        setProgressBarVisibility(false)
                    }
                }
            }
        }
    }

    private fun getBlogContentsOccurrenceOfEachWord() {
        viewModelScope.launch {
            flowResponse { blogContentRepository.getBlogContent() }.collect {
                when (it) {
                    is ApiResponse.Loading -> {
                        setProgressBarVisibility(true)
                    }
                    is ApiResponse.Success -> {
                        setProgressBarVisibility(false)
                        withContext(Dispatchers.IO) {
                            _findOccurrenceOfEachWord.postValue(
                                findOccurrenceOfEachWord(
                                    findWordsFromString(it.data)
                                ).toString()
                            )
                        }
                    }
                    is ApiResponse.Failure -> {
                        setProgressBarVisibility(false)
                        showToastErrorMsg(it.error)
                    }
                    is ApiResponse.Complete -> {
                        setProgressBarVisibility(false)
                    }
                }
            }
        }
    }

}