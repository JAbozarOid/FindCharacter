package com.truecaller.testassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.truecaller.testassignment.databinding.ActivityMainBinding
import com.truecaller.testassignment.viewModels.ContentViewModel

class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding?.root)

        build()
    }

    private fun build() {
        findTenthCharacter()
    }

    private fun findTenthCharacter() {

    }
}