package com.hhy.openproject.modle

import com.hhy.openproject.bean.CategoryInfoResp
import com.hhy.openproject.utils.Category
import com.hhy.openproject.utils.Constant
import com.hhy.openproject.utils.JsonCallback
import com.lzy.okgo.OkGo

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class GirlModle {
    fun getGirl(callBack: JsonCallback<CategoryInfoResp>, pageCount: Int, page: Int) {
        OkGo.post<CategoryInfoResp>(Constant.CATEGORY_URL +"/"+ Category.GIRL.value + "/" + pageCount + "/" + page)
            .tag(this)
            .execute(callBack)
    }
}