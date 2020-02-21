package com.example.assignment.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
​
 * Purpose – Singleton class for main-safe operations using Coroutine(CoroutineScope)
​
 * @author ​Rishabh Gupta
​
 * Created on February 21, 2020
​
 * Modified on February 21, 2020
 *
 * */
object Coroutines {

    /**
     * method performs network operations
     */
    fun main(work: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
    }

    /**
     *  method performs database operations
     */
    fun io(work: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    }
}