package com.alwin.lib_refection

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class BindView(val id: Int = 0, val name:String = "")


