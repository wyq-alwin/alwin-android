package com.alwin.lib

import android.app.Activity

class Binding {

    companion object {

        fun binding(activity: Activity) {
            try {
                val bindClass = Class.forName(activity.javaClass.canonicalName + "Binding")
                bindClass.getDeclaredConstructor(activity.javaClass).newInstance(activity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}