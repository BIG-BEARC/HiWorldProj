package com.example.hiworldproj.logic

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.example.common.ui.tab.HiFragmentTabView
import com.example.common.ui.tab.HiTabViewAdapter
import com.example.cx_ui.bottom.HiTabBottomInfo
import com.example.cx_ui.bottom.HiTabBottomLayout
import com.example.cx_ui.common.IHiTabLayout
import com.example.hiworldproj.R
import com.example.hiworldproj.fragment.*

/**
 *@Description 将MainActivity的一些逻辑内聚在这，让MainActivity更加清爽
 *@Author chuxiong
 *@Time 10/22/21 4:37 PM
 */
class MainAtyLogic(var activityProvider: ActivityProvider, var savedInstanceState: Bundle?) {
    private var currentIndex = 0
    private var infoList = mutableListOf<HiTabBottomInfo<*>>()
    private var hiBottomLayout: HiTabBottomLayout= activityProvider.findViewById(R.id.hiBottomLayout)
    private var hiFragTabView: HiFragmentTabView= activityProvider.findViewById(R.id.hiFragTabView)

    init {
        initBottom()
    }

    private fun initBottom() {

        val tintColor = activityProvider.getResources().getColor(R.color.hi_tabtop_selected_color)
        val defaultColor = activityProvider.getResources().getColor(R.color.hi_tabtop_normal_color)
        val homeInfo = HiTabBottomInfo(
            "首页",
            "fonts/iconfont.ttf",
            activityProvider.getString(R.string.if_home),
            "",
            defaultColor,
            tintColor
        )
        homeInfo.fragment = HomePageFragment::class.java
        val infoFavorite = HiTabBottomInfo(
            "收藏",
            "fonts/iconfont.ttf",
            activityProvider.getString(R.string.if_favorite),
            "",
            defaultColor,
            tintColor
        )
        infoFavorite.fragment = FavoriteFragment::class.java
        val infoCategory = HiTabBottomInfo(
            "分类",
            "fonts/iconfont.ttf",
            activityProvider.getString(R.string.if_category),
            "",
            defaultColor,
            tintColor
        )
        infoCategory.fragment = CategoryFragment::class.java
        val infoRecommend = HiTabBottomInfo(
            "推荐",
            "fonts/iconfont.ttf",
            activityProvider.getString(R.string.if_recommend),
            "",
            defaultColor,
            tintColor
        )
        infoRecommend.fragment = RecommendFragment::class.java
        val infoProfile = HiTabBottomInfo(
            "我的",
            "fonts/iconfont.ttf",
            activityProvider.getString(R.string.if_profile),
            "",
            defaultColor,
            tintColor
        )
        infoProfile.fragment = ProfileFragment::class.java
        infoList.apply {
            add(homeInfo)
            add(infoFavorite)
            add(infoCategory)
            add(infoRecommend)
            add(infoProfile)
        }
        hiBottomLayout.apply {
            setTabAlpha(0.8f)
            inflateInfo(infoList)
            initTabFragment()
            addTabSelectedChangeListener(object :
                IHiTabLayout.OnTabSelectedListener<HiTabBottomInfo<*>> {
                override fun onTabSelectedChange(
                    index: Int,
                    prevInfo: HiTabBottomInfo<*>?,
                    nextInfo: HiTabBottomInfo<*>
                ) {
                    hiFragTabView.setCurrentItem(index)
                    currentIndex =index
                }

            })

            defaultSelected(infoList[currentIndex])
        }
    }

    /**
     * 初始化 tabFrag
     */
    private fun initTabFragment() {
        hiFragTabView.setAdapter(
            HiTabViewAdapter(
                activityProvider.getSupportFragmentManager(),
                infoList
            )
        )
        hiFragTabView.setCurrentItem(currentIndex)
    }
}

interface ActivityProvider {
    fun <T : View> findViewById(@IdRes id: Int): T

    fun getResources(): Resources

    fun getSupportFragmentManager(): FragmentManager

    fun getString(@StringRes resId: Int): String
}