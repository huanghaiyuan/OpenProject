package com.hhy.openproject.view

import com.hhy.openproject.bean.CategoryInfoItem

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
interface GirlView : IView {
    fun showGirl(resp: MutableList<CategoryInfoItem>)
    fun showFail(msg:String)
}