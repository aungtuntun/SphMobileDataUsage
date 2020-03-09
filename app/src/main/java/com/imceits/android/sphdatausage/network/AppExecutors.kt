package com.imceits.android.sphdatausage.network

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
 open class AppExecutors(private val diskIO: Executor, private val networkIO: Executor,
                         private val mainThread: Executor) {
    @Inject
    constructor(): this (Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(4),
        MainThreadExecutor())

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }
    public class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }

    }
}