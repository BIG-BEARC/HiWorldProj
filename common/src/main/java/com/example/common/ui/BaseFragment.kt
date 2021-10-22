package com.example.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 *@Description
 *@Author chuxiong
 *@Time 10/22/21 2:24 PM
 */
abstract class BaseFragment:Fragment(),BaseActionInterface {

    @LayoutRes
    abstract fun getLayoutId():Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }
}