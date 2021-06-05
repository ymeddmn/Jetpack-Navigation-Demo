package com.mage.navigationdemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_des).setOnClickListener {
//            findNavController(R.id.nav_host_fragment).navigate(R.id.desActivity)
            val uri:Uri =
            findNavController(R.id.nav_host_fragment).navigate(uri)
        }
    }
}