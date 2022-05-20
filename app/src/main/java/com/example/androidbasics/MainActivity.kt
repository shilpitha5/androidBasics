package com.example.androidbasics

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbasics.activitylifecycle.LifeCycleFirstActivity


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById<View>(R.id.activityLifeCycle) as Button
        button.setOnClickListener { _ ->
            startActivity(Intent(this, LifeCycleFirstActivity::class.java))
        }
    }


}