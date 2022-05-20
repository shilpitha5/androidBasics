package com.example.androidbasics.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.androidbasics.R

class LifeCycleSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ActivityLifeCycle","Activity Second OnCreate ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_second)

        val textView=findViewById<TextView>(R.id.textView)
        textView.setOnClickListener {
            startActivity(Intent(this,TransparentActivity::class.java))
        }
    }

    override fun onStart() {
        Log.i("ActivityLifeCycle","Activity Second OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i("ActivityLifeCycle","Activity Second OnResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i("ActivityLifeCycle","Activity Second OnPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("ActivityLifeCycle","Activity Second OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("ActivityLifeCycle","Activity Second OnDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.i("ActivityLifeCycle","Activity Second OnRestart")
        super.onRestart()
    }
}