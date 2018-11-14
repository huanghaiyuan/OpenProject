package com.hhy.openproject.view

import com.hhy.openproject.bean.CategoryInfoItem
import com.hhy.openproject.utils.Category

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
interface MainView : IView {
    fun showViewPager(girl: MutableList<CategoryInfoItem>)
    fun showCategory(categroyList: MutableList<Category>)
    fun showMainList(resp: MutableList<CategoryInfoItem>)
    fun showFail(msg:String)
}