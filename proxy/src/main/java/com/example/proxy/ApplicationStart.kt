package com.example.proxy

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.Window



open class ApplicationStart : Application(), Application.ActivityLifecycleCallbacks{

    override fun onCreate() {
        super.onCreate()
        println("Notification onCreatetaeAxiaAplication")
        registerActivityLifecycleCallbacks(this)
    }
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {

        println("Notification onCreatetaeAxiaAplication $p1")
        println("onActivityCreated $p0")
        val win: Window = p0.window
        val localCallback: Window.Callback = win.callback
        win.callback = MyWindowCallback(localCallback, p0)
         (win.callback as MyWindowCallback).writeFileFirstTime()

    }



    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityResumed(p0: Activity) {

    }

    override fun onActivityPaused(p0: Activity) {

    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityDestroyed(p0: Activity) {

    }

}
