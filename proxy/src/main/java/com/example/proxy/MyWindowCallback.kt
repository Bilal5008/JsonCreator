package com.example.proxy

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.*
import android.view.accessibility.AccessibilityEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.core.view.get
import com.an.deviceinfo.device.model.App
import com.an.deviceinfo.device.model.Device
import com.example.model.*
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.simple.JSONObject
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*


class MyWindowCallback() : Window.Callback {
    val FILE_NAME: String = "File-name"
    val READ_BLOCK_SIZE = 10000
    var localCallback: Window.Callback? = null
    var activity: Activity? = null
    var mouseEventList: ArrayList<MouseEvent>? = null
    var mouseEventListFinal: ArrayList<MouseEvent>? = null
    var finalView: View? = null

    companion object {
        const val FOO = "MyWindowCallback"
    }

    constructor(localCallback: Window.Callback, activity: Activity) : this() {
        this.activity = activity
        this.localCallback = localCallback
        mouseEventList = arrayListOf()
        var viewGroupSize =
            ((this.activity?.window?.decorView?.findViewById<View>(R.id.content) as? ViewGroup)?.getChildAt(
                0
            ) as? ViewGroup)?.childCount

        if (this.activity != null && this.activity?.window?.decorView?.findViewById<View>(R.id.content) as? ViewGroup != null) {


            for (i in 0 until viewGroupSize!!) {
                finalView =
                    ((this.activity?.window?.decorView?.findViewById<View>(R.id.content) as? ViewGroup)?.getChildAt(
                        0
                    ) as? ViewGroup)?.get(i)
                if (finalView is Button) {
                    addOnTouchListener(finalView as Button, i)

                } else if (finalView is EditText) {
                    addSetTextListener(finalView as EditText, i)
                }
                Log.i(FOO, "UID $i")

            }


        }
    }

    private fun addSetTextListener(finalView: EditText, i: Int) {


        finalView.imeOptions = EditorInfo.IME_ACTION_DONE

        finalView.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                if (finalView.length() > 0) {
                    Log.i(FOO, "EditView ${finalView.text}")
                    Log.i(FOO, "UID $i")

                }

            }
            false
        })



    }

    private fun addOnTouchListener(finalView: Button, i: Int) {
        finalView?.setOnTouchListener { view, motionEvent ->
            Log.i(FOO, " rootViewGroupNEW viewcheck $view")

            when (motionEvent?.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.i(FOO, " rootViewGroup1 viewcheck ${view?.visibility}")
                }
                MotionEvent.ACTION_UP -> {
                    val rootGlobalRect = Rect()
                    Log.i(FOO, " rootViewGroup1 viewcheck ${view?.visibility}")
                    Log.i(FOO, " rootViewGroup1 viewcheck ${view?.getFocusedRect(rootGlobalRect)}")
                    Log.i(FOO, " rootViewGroup1 UID ${i}")

                }
            }
            true
        }
    }


    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return localCallback!!.dispatchKeyEvent(event)
    }

    @SuppressLint("NewApi")
    override fun dispatchKeyShortcutEvent(event: KeyEvent): Boolean {
        return localCallback!!.dispatchKeyShortcutEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {

        val action = event.actionMasked
        var actionCode = ""
        when (action) {
            MotionEvent.ACTION_DOWN -> actionCode = "PRESS"
            MotionEvent.ACTION_MOVE -> actionCode = "MOVE"
            MotionEvent.ACTION_UP -> actionCode = "RELEASE"
        }
//        Log.d(FOO, "The action is : $actionCode")
//        Log.d(FOO, "The action is time stemp is  : ${System.currentTimeMillis()}")
//        Log.d(FOO, "The action is time dateformat is  : ${getDateTime(System.currentTimeMillis())}")


        val index = event.actionIndex
        var xPos = -1
        var yPos = -1

//        if (actionCode === "Down") {
//
//
////            Log.d(FOO, "xPosition: $xPos, yPosition: $yPos  $actionCode")
//        }
////        else if (actionCode === "Move") {
////            xPos = event.getX(index).toInt()
////            yPos = event.getY(index).toInt()
////            upfinalPositionx = xPos
////            upfinalPositiony = yPos
////            Log.d(FOO, "xPosition: $xPos, yPosition: $yPos  $actionCode")
////        }
////        else if (actionCode === "Up"){
////
////           Log.d(FOO, "xPosition: $xPos, yPosition: $yPos  $actionCode")
////        }
//        else if (actionCode === "Pointer Down"){
//
//           //     Log.d(FOO, "pointerdown")
//        }
//        if (downfinalPositionx == upfinalPositionx  && downfinalPositiony ==upfinalPositiony)
//        {
//            Log.d(FOO, "remove all move")
//
//        }
//        else
//        {
//        //    Log.d(FOO, "do not remove all move")
//        }


        if (localCallback!!.dispatchTouchEvent(event)) {
            //    Log.d(FOO, "Wrting on file")

//            val rootViewGroup = activity?.window?.decorView?.findViewById<ViewGroup>(R.id.)?.childCount
//            val getChildView = activity?.window?.decorView?.findViewById<ViewGroup>(R.id.content)?.children


//            val x = event.x.toInt()
//
//            for (i in 0 until rootViewGroup!!) {
////                val c: View = getChildView
////                if (x > c.left && x < c.right) {
////                    return c.onTouchEvent(event)
////                }
//            }


            when {
                actionCode === "PRESS" -> {

//                    if (v !is EditText) {


                    xPos = event.getX(index).toInt()
                    yPos = event.getY(index).toInt()
                    Log.d(FOO, "PRESS on  $xPos - $yPos")
                    mouseEventList?.add(
                        MouseEvent(
                            System.currentTimeMillis(),
                            xPos,
                            yPos,
                            actionCode
                        )
                    )
//                    }
                }
                actionCode === "MOVE" -> {

//                    if (v !is EditText) {


                    xPos = event.getX(index).toInt()
                    yPos = event.getY(index).toInt()
                    Log.d(FOO, "MOVE on  $xPos - $yPos")
                    mouseEventList?.add(
                        MouseEvent(
                            System.currentTimeMillis(),
                            xPos,
                            yPos,
                            actionCode
                        )
                    )


                }
                actionCode === "RELEASE" -> {


                    mouseEventList?.add(
                        MouseEvent(
                            System.currentTimeMillis(),
                            xPos,
                            yPos,
                            actionCode
                        )
                    )

                    var a = App(activity)
                    var selectedComponent = SelectedComponent()
                    var scenario = Scenario(
                        "CLICKED",
                        "--",
                        a.activityName,
                        "XML_PLACE",
                        selectedComponent,
                        mouseEventList,
                        null,
                        null,
                        "",
                        "",
                        "",
                        AndroidPerformanceMonitors(),
                        "",
                        0,
                        null,
                        12345
                    )

                    ReadJsonOnMouseEvent(scenario.toJSON()!!)
                    mouseEventList?.clear()
                }


//                }
            }


        }
        return false

    }

    private fun ReadJsonOnMouseEvent(obj: JSONObject) {
        var convertedObject: JsonObject? = null
        try {
            var fileInputStream = activity?.openFileInput(FILE_NAME)
            val inputBuffer = CharArray(READ_BLOCK_SIZE)
            val InputRead = InputStreamReader(fileInputStream)
            var s: String? = ""
            var charRead: Int = 0
            while (InputRead.read(inputBuffer).also { charRead = it } > 0) {
                // char to string conversion
                val readstring = String(inputBuffer, 0, charRead)
                s += readstring
                convertedObject = Gson().fromJson(s, JsonObject::class.java)
            }

            InputRead.close()
            println("Input Read json as string$s")
            println("Input convertedObject ${convertedObject.toString()}")
        } catch (e: IOException) {
            e.printStackTrace();
        }
        var objectToBeAdded = Gson().fromJson(obj.toJSONString(), JsonObject::class.java)
        println("Input Read new object ${obj.toJSONString()}")
        println("Input Read new object $objectToBeAdded")


        val obj = convertedObject?.get("scenario")?.asJsonArray
        obj?.add(objectToBeAdded)

        writeFile(obj)
    }

    private fun writeFile(obj: JsonArray?) {
        var mouseEvent: ArrayList<MouseEvent> = arrayListOf()
        mouseEvent.add(MouseEvent(null, null, null, null))

        var d = Device(activity)
        var a = App(activity)
        var device = DeviceConfigured(
            true,
            d.releaseBuildVersion,
            d.manufacturer,
            "abc",
            "d.ram",
            "",
            d.manufacturer,
            d.model,
            d.board,
            "abc",
            d.osVersion,
            d.buildVersionCodeName,
            d.device,
            "abc"
        )
        var appInfo = AppInfo(
            a.packageName,
            a.appName,
            a.appVersionCode.toString(),
            "minSdk",
            "maxSdk",
            "compileSDK",
            "appIconFile",
            "appFile"
        )
//        var selectedComponent = SelectedComponent()
//        var scenario = Scenario(
//            "APP_LAUNCH",
//            "--",
//            a.activityName,
//            "XML_PLACE",
//            selectedComponent,
//            mouseEvent,
//            null,
//            null,
//            "",
//            "",
//            "",
//            AndroidPerformanceMonitors(),
//            "",
//            0,
//            null,
//            12345
//        )


        var scenarioList: ArrayList<Scenario> = arrayListOf()
        val jArray: JsonArray = obj as JsonArray
        val yourArray: ArrayList<Scenario> =
            Gson().fromJson(jArray.toString(), object : TypeToken<List<Scenario?>?>() {}.type)
        for (i in 0 until yourArray.size) {
            scenarioList.add(yourArray[i])
        }


        var threshold = Threshold()

        val obj = CaseScenario(
            "c6cf3e75-520f-49a7-a40b-57382f44115f",
            "Scenario_com.devfactori.axiaparticipant_3_1599821437021",
            scenarioList,
            device,
            threshold,
            appInfo

        )


        WriteCaseSenarioJson().writeCaseSenarioJson(obj.toJSON()!!, activity)

    }

    override fun dispatchTrackballEvent(event: MotionEvent): Boolean {
        return localCallback!!.dispatchTrackballEvent(event)
    }

    @SuppressLint("NewApi")
    override fun dispatchGenericMotionEvent(event: MotionEvent): Boolean {
        return localCallback!!.dispatchGenericMotionEvent(event)
    }

    override fun dispatchPopulateAccessibilityEvent(event: AccessibilityEvent): Boolean {
        return localCallback!!.dispatchPopulateAccessibilityEvent(event)
    }

    override fun onCreatePanelView(featureId: Int): View? {
        return localCallback!!.onCreatePanelView(featureId)
    }

    override fun onCreatePanelMenu(featureId: Int, menu: Menu): Boolean {
        return localCallback!!.onCreatePanelMenu(featureId, menu)
    }

    override fun onPreparePanel(featureId: Int, view: View?, menu: Menu): Boolean {
        return localCallback!!.onPreparePanel(featureId, view, menu)
    }

    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        return localCallback!!.onMenuOpened(featureId, menu)
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        return localCallback!!.onMenuItemSelected(featureId, item)
    }

    override fun onWindowAttributesChanged(attrs: WindowManager.LayoutParams) {
        localCallback!!.onWindowAttributesChanged(attrs)
    }

    override fun onContentChanged() {
        Log.d(FOO, "onContentChanged")
        localCallback!!.onContentChanged()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        Log.d(FOO, "ttest onfocus changed called")
        localCallback!!.onWindowFocusChanged(hasFocus)
    }

    override fun onAttachedToWindow() {
        localCallback!!.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        localCallback!!.onDetachedFromWindow()
    }

    override fun onPanelClosed(featureId: Int, menu: Menu) {
        localCallback!!.onPanelClosed(featureId, menu)
    }

    override fun onSearchRequested(): Boolean {
        return localCallback!!.onSearchRequested()
    }

    override fun onSearchRequested(searchEvent: SearchEvent): Boolean {
        return false
    }

    @SuppressLint("NewApi")
    override fun onWindowStartingActionMode(callback: ActionMode.Callback): ActionMode? {
        return localCallback!!.onWindowStartingActionMode(callback)
    }

    override fun onWindowStartingActionMode(callback: ActionMode.Callback, i: Int): ActionMode? {
        return null
    }

    @SuppressLint("NewApi")
    override fun onActionModeStarted(mode: ActionMode) {
        localCallback!!.onActionModeStarted(mode)
    }

    @SuppressLint("NewApi")
    override fun onActionModeFinished(mode: ActionMode) {
        localCallback!!.onActionModeFinished(mode)
    }

    private fun getDateTime(timeStamp: Long): String? {
        return try {
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault()
            )
            val netDate = Date(timeStamp)
            sdf.format(netDate)
        } catch (ex: java.lang.Exception) {
            "xx"
        }
    }

    fun writeFileFirstTime() {
        var mouseEvent: ArrayList<MouseEvent> = arrayListOf()
        mouseEvent.add(MouseEvent(null, null, null, null))

        var d = Device(activity)
        var a = App(activity)
        var device = DeviceConfigured(
            true,
            d.releaseBuildVersion,
            d.manufacturer,
            "abc",
            "d.ram",
            "",
            d.manufacturer,
            d.model,
            d.board,
            "abc",
            d.osVersion,
            d.buildVersionCodeName,
            d.device,
            "abc"
        )
        var appInfo = AppInfo(
            a.packageName,
            a.appName,
            a.appVersionCode.toString(),
            "minSdk",
            "maxSdk",
            "compileSDK",
            "appIconFile",
            "appFile"
        )
        var selectedComponent = SelectedComponent()
        var scenario = Scenario(
            "APP_LAUNCH",
            "--",
            a.activityName,
            "XML_PLACE",
            selectedComponent,
            mouseEvent,
            null,
            null,
            "",
            "",
            "",
            AndroidPerformanceMonitors(),
            "",
            0,
            null,
            12345
        )
        var scenarioList: ArrayList<Scenario> = arrayListOf()
        scenarioList.add(scenario)


        var threshold = Threshold()

        val obj = CaseScenario(
            "c6cf3e75-520f-49a7-a40b-57382f44115f",
            "Scenario_com.devfactori.axiaparticipant_3_1599821437021",
            scenarioList,
            device,
            threshold,
            appInfo

        )


        WriteCaseSenarioJson().writeCaseSenarioJson(obj.toJSON()!!, activity)

    }


}