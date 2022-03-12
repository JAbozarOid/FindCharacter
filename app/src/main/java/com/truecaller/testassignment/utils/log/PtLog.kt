package com.truecaller.testassignment.utils.log

import android.util.Log
import com.truecaller.testassignment.BuildConfig

class PtLog {

    companion object {

        private val IS_DEBUG_MODE = BuildConfig.DEBUG

        fun v(tag: String?, msg: String) {
            if (IS_DEBUG_MODE)
                Log.v(tag, msg)
        }

        fun d(tag: String?, msg: String) {
            if (IS_DEBUG_MODE)
                Log.d(tag, msg)
        }

        fun i(tag: String?, msg: String) {
            if (IS_DEBUG_MODE)
                Log.i(tag, msg)
        }

        fun w(tag: String?, msg: String) {
            if (IS_DEBUG_MODE)
                Log.w(tag, msg)
        }

        fun e(tag: String?, msg: String, tr: Throwable? = null) {
            if (IS_DEBUG_MODE)
                Log.e(tag, msg, tr)
        }
    }
}