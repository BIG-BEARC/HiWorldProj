package com.example.common.ui.tab

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cx_ui.bottom.HiTabBottomInfo

/**
 *@Description
 *@Author chuxiong
 *@Time 10/22/21 3:18 PM
 */
class HiTabViewAdapter(
    private var fragmentManager: FragmentManager,
   private var infoList: MutableList<HiTabBottomInfo<*>>
) {

    var currentFragment: Fragment? = null

    /**
     *  实例化fragment,fragmentTab 的辅助管理类
     */
    fun instantiateItem(container: View, position: Int) {
        val fb = fragmentManager.beginTransaction()
        if (currentFragment != null) {
            fb.hide(currentFragment!!)
        }

        val tag = "${container.id}:$position"
        var fragment: Fragment? = fragmentManager.findFragmentByTag(tag)

        if (fragment == null) {
            fragment = infoList[position].fragment?.newInstance()
            if (fragment != null && !fragment.isAdded) {
                fb.add(container.id, fragment, tag)
            }
        } else {
            fb.show(fragment)
        }

        currentFragment = fragment
        fb.commitNowAllowingStateLoss()
    }

    fun getCount():Int = infoList.size

}