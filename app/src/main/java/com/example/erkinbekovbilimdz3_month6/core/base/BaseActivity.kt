package com.example.erkinbekovbilimdz3_month6.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM


    protected abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        setupLiveData()
        checkInternet()
        setData()
        initClickListener()
    }

    open fun setupLiveData() {

    }

    open fun checkInternet() {

    }

    open fun setData() {

    }

    open fun initClickListener() {

    }
}