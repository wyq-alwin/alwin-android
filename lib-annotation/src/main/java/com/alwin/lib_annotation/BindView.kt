package com.alwin.lib_annotation

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD)
annotation class BindView(val id: Int, val name: String = "")

