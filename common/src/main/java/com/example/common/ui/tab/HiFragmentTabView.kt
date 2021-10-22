package com.example.common.ui.tab

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

/**
 *@Description 1.将Fragment的操作内聚
 *             2.提供通用的一些API
 *@Author chuxiong
 *@Time 10/22/21 3:18 PM
 */
class HiFragmentTabView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = 0
) : FrameLayout(context, attrs, def) {

    private var adapter: HiTabViewAdapter? = null
    private var currentPosition = -1

    fun setAdapter(adapter: HiTabViewAdapter) {
        if (this.adapter != null) return
        this.adapter = adapter
        currentPosition = -1
    }

    fun setCurrentItem(position: Int) {
        if (checkAdapterValid() || position < 0 || position > adapter!!.getCount()) return

        if (currentPosition != position) {
            currentPosition = position
            adapter!!.instantiateItem(this, position)
        }
    }

    private fun checkAdapterValid(): Boolean {
        return adapter == null
    }

    fun getCurrentItem() = currentPosition

    fun getCurrentFragment(): Fragment {
        requireNotNull(this.adapter) { "please call setAdapter first." }
        return adapter?.currentFragment!!
    }
}