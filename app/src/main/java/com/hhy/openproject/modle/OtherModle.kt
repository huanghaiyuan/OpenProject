package com.hhy.openproject.modle

import com.hhy.openproject.bean.GirlResp
import com.hhy.openproject.utils.Constant
import com.hhy.openproject.utils.JsonCallback
import com.lzy.okgo.OkGo

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class OtherModle {
    fun getOther(callBack: JsonCallback<GirlResp>, category: String, pageCount: Int, page: Int) {
        OkGo.post<GirlResp>(Constant.CATEGORY_URL +"/"+ category + "/" + pageCount + "/" + page)
            .tag(this)
            .execute(callBack)
    }
}