package com.mage.navigationdemo.includenesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mage.navigationdemo.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle("首页")
    }
}