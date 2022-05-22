package com.example.androidbasics

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbasics.activitylifecycle.LifeCycleFirstActivity
import com.example.androidbasics.coroutinebasics.CoroutineFirstActivity
import com.example.androidbasics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityLifeCycle.setOnClickListener {
            startActivity(Intent(this, LifeCycleFirstActivity::class.java))
        }

        binding.coroutines.setOnClickListener {
            startActivity(Intent(this, CoroutineFirstActivity::class.java))
        }
    }
}
