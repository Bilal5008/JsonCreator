package com.example.proxy

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup


class MyDispatcherTwo(context: Context) : ViewGroup(context) {
    private val TAG: String = MyDispatcherTwo::class.java.getSimpleName()
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(TAG, "onInterceptTouchEvent")
        val action = ev!!.actionMasked
        when (action) {
            MotionEvent.ACTION_DOWN -> Log.i(TAG, "onInterceptTouchEvent.ACTION_DOWN")
            MotionEvent.ACTION_MOVE -> Log.i(TAG, "onInterceptTouchEvent.ACTION_MOVE")
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> Log.i(
                TAG,
                "onInterceptTouchEvent.ACTION_UP"
            )
        }
        return super.onInterceptTouchEvent(ev)
    }
}

