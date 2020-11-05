package com.example.utils

import android.graphics.Point
import android.view.View

class  Utilities {
companion object
{
    var actionIndex : Int  =0
    var bounds : String?  = null
    var gUID : String?  = null
    var packageName : String?  = null
    var file64Byte : String ? =null

     fun isViewContains(view: View, rx: Int, ry: Int): Boolean {
        val l = IntArray(2)
        view.getLocationOnScreen(l)
        val x = l[0]
        val y = l[1]
        val w: Int = view.width
        val h: Int = view.height
        return !(rx < x || rx > x + w || ry < y || ry > y + h)
    }

    fun getLocationOnScreen(view: View): Point? {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return Point(location[0], location[1])
    }
}


}