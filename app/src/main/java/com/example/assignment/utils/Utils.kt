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
    /***
     * Method to show Fade In Animation on Texts
     */
    fun showFadeInAnimOnText(context: Context, textView: TextView, msg: String) {
        val animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fabe_in)
        textView.visibility = View.VISIBLE
        textView.text = msg
        textView.startAnimation(animFadeIn)
    }
}