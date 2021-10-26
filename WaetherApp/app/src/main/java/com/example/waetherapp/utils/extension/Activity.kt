package com.example.waetherapp.utils.extension

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun<reified T : AppCompatActivity> Context.startActivity(block : Intent.() -> Unit = {}){
    val intent  = Intent(this , T::class.java)
    startActivity(
        intent.also {
            block.invoke(it)
        }
    )
    // for starting new Activity
}