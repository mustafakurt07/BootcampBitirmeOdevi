package com.example.waetherapp.ui.kurt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waetherapp.R
import com.example.waetherapp.utils.extension.startActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    private val DELAY  :  Long = 5 * 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Timer().schedule(object : TimerTask() {
            override fun run() {

                /* val i = Intent(applicationContext, MainActivity::class.java)
                 startActivity(i)*/
                startActivity<MainActivity>{
                    Intent()
                    //After splash activity is shown, other activity passes
                }
                finish()
            }
        }, DELAY)
    }
}