package com.truecaller.testassignment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.truecaller.testassignment.databinding.ActivityMainBinding
import com.truecaller.testassignment.viewModels.ContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding

    private val mContentViewModel: ContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding?.root)

        build()
    }

    private fun build() {
        requestFindTenthCharacter()

        observeFindTenthCharacter()
    }

    private fun requestFindTenthCharacter() {
        mContentViewModel.findTenthCharacter()
    }

    private fun observeFindTenthCharacter() {
        mContentViewModel.findTenthCharacter.observe(this) {
            mBinding?.tvTenthCharResult?.text = it.toString()
        }
    }
}