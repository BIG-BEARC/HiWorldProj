package com.example.hiworldproj

import android.os.Bundle
import com.example.common.ui.BaseActivity
import com.example.cx_ui.bottom.HiTabBottomInfo
import com.example.hiworldproj.logic.ActivityProvider
import com.example.hiworldproj.logic.MainAtyLogic

class MainActivity : BaseActivity(),ActivityProvider {
    lateinit var activityLogic: MainAtyLogic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityLogic = MainAtyLogic(this,savedInstanceState)
    }



}