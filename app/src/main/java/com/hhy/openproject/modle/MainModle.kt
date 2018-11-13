package com.hhy.openproject.modle

import com.hhy.openproject.bean.OtherResp
import com.hhy.openproject.utils.Constant
import com.hhy.openproject.utils.JsonCallback
import com.lzy.okgo.OkGo

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class MainModle {
    fun getToday(callBack:JsonCallback<OtherResp>){
        OkGo.get<OtherResp>(Constant.TODAY_URL)
            .tag(this)
            .execute(callBack)
    }
}