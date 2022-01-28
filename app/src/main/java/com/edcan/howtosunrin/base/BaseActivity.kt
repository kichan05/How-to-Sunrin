package com.edcan.howtosunrin.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

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

    protected val gotoWebEDCAN = { _: View ->
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://edcan.kr"))
        startActivity(intent)
    }
}