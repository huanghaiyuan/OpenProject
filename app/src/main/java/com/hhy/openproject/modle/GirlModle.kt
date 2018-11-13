package com.hhy.openproject.modle

import com.hhy.openproject.bean.GirlResp
import com.hhy.openproject.utils.Category
import com.hhy.openproject.utils.Constant
import com.hhy.openproject.utils.JsonCallback
import com.lzy.okgo.OkGo

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class GirlModle {
    fun getGirl(callBack: JsonCallback<GirlResp>, pageCount: Int, page: Int) {
        OkGo.post<GirlResp>(Constant.CATEGORY_URL +"/"+ Category.GIRL.value + "/" + pageCount + "/" + page)
            .tag(this)
            .execute(callBack)
    }
}