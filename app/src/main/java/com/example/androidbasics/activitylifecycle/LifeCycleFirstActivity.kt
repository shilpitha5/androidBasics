package com.example.androidbasics.activitylifecycle

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbasics.R

class LifeCycleFirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ActivityLifeCycle", "Activity First OnCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_first)

        val button1: Button = findViewById<View>(R.id.alert) as Button
        val button2: Button = findViewById<View>(R.id.nextActivity) as Button

        button1.setOnClickListener { _ ->
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Dummy Alert ?")
                .setCancelable(false)
                .setPositiveButton("Close Alert", DialogInterface.OnClickListener { dialog, id ->
                     dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("AlertDialogExample")
            alert.show()
        }
        button2.setOnClickListener { _ ->
            startActivity(Intent(this, LifeCycleSecondActivity::class.java))
        }
    }

    override fun onStart() {
        Log.i("ActivityLifeCycle", "Activity First OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i("ActivityLifeCycle", "Activity First OnResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i("ActivityLifeCycle", "Activity First OnPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i("ActivityLifeCycle", "Activity First OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("ActivityLifeCycle", "Activity First OnDestroy")
        super.onDestroy()
        Log.i("ActivityLifeCycle", "Activity ShutDown")
    }

    override fun onRestart() {
        Log.i("ActivityLifeCycle", "Activity First OnRestart")
        super.onRestart()
    }
}