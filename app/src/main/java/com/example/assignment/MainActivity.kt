package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.assignment.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
​
 * Purpose – Class act as First Screen of the application
​
 * @author ​Rishabh Gupta
​
 * Created on February 13, 2020
​
 * Modified on February 13, 2020
 *
 * */
class MainActivity : AppCompatActivity() {
    private lateinit var bindingView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindingView = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    /**
     * inital setUps
     */
    private fun init() {
        val findNavController = findNavController(R.id.nav_host_fragment)

        // Setup Action Bar
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(findNavController)

        // Setup Toolbar
        toolbar.setupWithNavController(findNavController)
    }
}
