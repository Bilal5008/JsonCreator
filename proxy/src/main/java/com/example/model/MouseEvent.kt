package com.example.model

import com.google.gson.GsonBuilder
import org.json.simple.JSONObject

class MouseEvent {
    var time: Long? = null
    var pointX: Int? = null

    var pointY: Int? = null
    var mouseEventType: String? = null
    constructor(time: Long?, pointX: Int?, pointY: Int?, mouseEventType: String?) {
        this.time = time
        this.pointX = pointX
        this.pointY = pointY
        this.mouseEventType = mouseEventType
    }



    fun toJSON(): JSONObject? {
        val gson = GsonBuilder().create()
        val jo = JSONObject()
        jo["time"] = time
        jo["pointX"] = pointX
        jo["pointY"] = pointY
        jo["mouseEventType"] = mouseEventType




        return jo
    }

}