package com.example.assignment.utils

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.assignment.R

/**
 *
​
 * Purpose – Singleton class contains common used methods
​
 * @author ​Rishabh Gupta
​
 * Created on February 14, 2020
​
 * Modified on February 14, 2020
 *
 * */

object Utils {

    /**
     * Method returns Visibility state Visible: Gone
     */
    fun getVisiblility(constraint: Boolean): Int = if (constraint) View.VISIBLE else View.GONE
}