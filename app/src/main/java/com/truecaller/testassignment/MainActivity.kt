package com.truecaller.testassignment

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.truecaller.testassignment.databinding.ActivityMainBinding
import com.truecaller.testassignment.utils.network.ApiResponse
import com.truecaller.testassignment.viewModels.ContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding

    private val mContentViewModel: ContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding?.root)

        build()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_request -> {
                requestGetContent()
            }
        }
    }

    private fun build() {

        setOnClickListener()

        observeGetContent()
    }

    private fun setOnClickListener() {
        mBinding?.btnRequest?.setOnClickListener(this)
    }

    private fun requestGetContent() {
        mContentViewModel.getContent()
    }

    private fun observeGetContent() {
        mContentViewModel.findTenthCharacter.observe(this) {
            when (it) {
                is ApiResponse.Error -> {
                    mBinding?.tvTenthCharResult?.text = it.errorMessage
                }
                is ApiResponse.ErrorTryAgain -> {
                    mBinding?.tvTenthCharResult?.text = it.errorMessage
                }
                is ApiResponse.Loading -> {

                }
                is ApiResponse.Success -> {
                    mBinding?.tvTenthCharResult?.text = it.data
                }
            }
        }
    }
}