package com.hhy.openproject.View

import com.hhy.openproject.bean.OtherItem
import com.hhy.openproject.utils.Category

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
interface MainView : IView {
    fun showViewPager(girl: MutableList<OtherItem>)
    fun showCategory(categroyList: MutableList<Category>)
    fun showToday(resp: MutableList<OtherItem>)
    fun showFail(msg:String)
}