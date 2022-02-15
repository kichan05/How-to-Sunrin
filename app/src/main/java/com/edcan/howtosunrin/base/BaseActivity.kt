package com.edcan.howtosunrin.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.edcan.howtosunrin.R

abstract class BaseActivity<D : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int
) : AppCompatActivity(){
    protected lateinit var binding : D

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_gotoEDCAN -> gotoWebEDCAN()
        }

        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.anim_hold, R.anim.anim_slide_to_right)
    }

    protected val gotoWebEDCAN = {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://edcan.kr"))
        startActivity(intent)
    }
}