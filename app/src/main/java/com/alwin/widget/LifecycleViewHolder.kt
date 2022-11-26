package com.alwin.widget

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

/**
 * 带生命周期的viewholder
 */
open class LifecycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    LifecycleOwner {

    private val lifecycleRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }

    init {
        itemView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            // View onDetached 的时候回调 onDestroy()
            override fun onViewDetachedFromWindow(v: View?) {
                itemView.removeOnAttachStateChangeListener(this)
                onDestroy()
            }

            // View onAttached 的时候回调 onCreate()
            override fun onViewAttachedToWindow(v: View?) {
                onCreate()
            }
        })
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    fun onCreate() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun onDestroy() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }
}
