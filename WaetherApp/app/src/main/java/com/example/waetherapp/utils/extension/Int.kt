package com.example.waetherapp.utils.extension

import android.content.res.Resources

val Int.px: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.withSign(): String = when {
    this > 0 -> "+$this"
    else -> this.toString()
}