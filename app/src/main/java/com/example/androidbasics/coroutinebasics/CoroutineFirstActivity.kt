package com.example.androidbasics.coroutinebasics

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidbasics.R
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class CoroutineFirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_first)

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val first = fun1("sequential")
                val second = fun2("sequential")
                Log.i("coroutines", "First function$first")
                Log.i("coroutines", "Second function$second")
            }
            Log.i("coroutines", "Total Time seq$time")

        }

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
               /* Just for reference
                var first: String? = null
                var second: String? = null
                val job1=launch { first = fun1("async") }
                val job2=launch { second = fun2("async") }
                job1.join()
                job2.join()
                */

                val firstFun=async { fun1("ASYNC") }
                val secondFun=async { fun2("ASYNC") }




                Log.i("coroutines", "First function" + firstFun.await())
                Log.i("coroutines", "Second function" + secondFun.await())
            }
            Log.i("coroutines", "Total Time async $time")

        }


    }

    private suspend fun fun1(s: String): String {
        Log.i("coroutines", "Fun 1 $s")
        delay(3000L)
        Log.i("coroutines", "Fun 1 after delay $s")
        return "Fun1 $s"
    }

    private suspend fun fun2(s: String): String {
        Log.i("coroutines", "Fun 2 $s")
        delay(3000L)
        Log.i("coroutines", "Fun 2 after delay $s")
        return "Fun2 $s"
    }
}
