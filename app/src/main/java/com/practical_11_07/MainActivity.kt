package com.practical_11_07

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.practical_11_07.databinding.ActivityMainBinding
import com.practical_11_07.dialog.FilterBottomSheet
import com.practical_11_07.model.PharmaResponse
import com.practical_11_07.utils.Utility

class MainActivity : AppCompatActivity() {

    lateinit var mBinder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mBinder.fabFilter.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.fabFilter -> {
                FilterBottomSheet().show(supportFragmentManager, "MainActivity")
            }
        }
    }
}