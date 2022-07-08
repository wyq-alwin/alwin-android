package com.alwin.lib_refection

import androidx.fragment.app.FragmentActivity

class Binding {

    companion object {

        fun binding(activity: FragmentActivity) {
            for (field in activity.javaClass.declaredFields) {
                field.getAnnotation(BindView::class.java)?.let {
                    try {
                        field.isAccessible = true
                        field.set(activity, activity.findViewById(it.id))
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    }
                }
            }
        }

    }
}