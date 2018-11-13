package com.hhy.openproject.View

import com.hhy.openproject.bean.OtherItem

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
interface GirlView : IView {
    fun showGirl(resp: MutableList<OtherItem>)
    fun showFail(msg:String)
}