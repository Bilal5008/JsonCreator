package com.example.model

import com.google.gson.GsonBuilder
import org.json.simple.JSONObject
import java.util.*


class CaseScenario {
    // Setter Methods
    // Getter Methods
    var scenarioGUID: String? = null
    var scenarioName: String? = null
    var deviceConfigured: DeviceConfigured? = null
    var scenario = ArrayList<Scenario>()
    var appInfo: AppInfo? = null
    var threshold: Threshold? = null

    constructor(
        scenarioGUID: String?, scenarioName: String?,
        scenario: ArrayList<Scenario>,
        deviceConfigured: DeviceConfigured,
        threshold: Threshold,
        appInfo: AppInfo


    ) : super() {
        this.scenarioGUID = scenarioGUID
        this.scenarioName = scenarioName
        this.scenario = scenario
        this.threshold = threshold
        this.appInfo = appInfo
        this.deviceConfigured = deviceConfigured
    }

    fun toJSON(): JSONObject? {
        val gson = GsonBuilder().create()
        val jo = JSONObject()
        jo["scenarioGUID"] = scenarioGUID
        jo["scenarioName"] = scenarioName
        jo["device"] = gson.toJsonTree(deviceConfigured).asJsonObject
        jo["scenario"] = gson.toJsonTree(scenario).asJsonArray
        jo["appInfo"] = gson.toJsonTree(appInfo).asJsonObject
        jo["threshold"] = gson.toJsonTree(threshold).asJsonObject



        return jo
    }
}