package com.suyf.neverjavacrash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HookLooper.hook()
    }

    fun testCrash(view: View) {
        crash()
    }


    fun testThreadCrash(view: View) {
        thread { crash() }
    }


    private fun crash() {
        val i = 0
        val ret = 10 / i
        print("ret$ret")
    }

    fun normalToast(view: View) {
        Toast.makeText(this,"show toast normally...",Toast.LENGTH_SHORT).show()
    }

}