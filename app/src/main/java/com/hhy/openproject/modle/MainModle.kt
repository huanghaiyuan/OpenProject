package com.hhy.openproject.modle

import com.hhy.openproject.bean.CategoryInfoItem
import com.hhy.openproject.bean.CategoryInfoResp
import com.hhy.openproject.bean.TodayResp
import com.hhy.openproject.utils.Constant
import com.hhy.openproject.utils.JsonCallback
import com.lzy.okgo.OkGo

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class MainModle {
    fun getToday(callBack:JsonCallback<TodayResp>){
        OkGo.get<TodayResp>(Constant.TODAY_URL)
            .tag(this)
            .execute(callBack)
    }

    fun getRandom(callBack:JsonCallback<CategoryInfoResp>){
        OkGo.get<CategoryInfoResp>(Constant.RANDOM_URL)
            .tag(this)
            .execute(callBack)
    }
}