package com.suyf.neverjavacrash

import android.os.Looper
import android.util.Log
import java.lang.Exception

/**
 * @author Created by suyongfeng on 2022/5/16
 */
object HookLooper {
    private const val TAG = "Suyf"

    fun hook() {
        Thread.setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler { t, e ->
            // 可以在这里收集崩溃信息。。。
            Log.d(
                TAG, "Thread t-id: " + t.id + ",t-name:" + t.name
                        + ",t-looper:" + Looper.myLooper() + ",isMainLooper:"
                        + (Looper.myLooper() == Looper.getMainLooper())
            )
            Log.d(TAG, "uncaughtException: " + e.message)
            if (Looper.myLooper() != Looper.getMainLooper()) {
                return@UncaughtExceptionHandler
            }
            while (true) {
                try {
                    Log.d(TAG, "Looper.loop===before")
                    if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
                        Looper.loop()
                    } else {
                        break
                    }
                    Log.d(TAG, "Looper.loop===after")
                } catch (exception: Exception) {
                    Log.d(TAG, "Looper.loop===error: " + e.message)
                }
            }
        })
    }
}