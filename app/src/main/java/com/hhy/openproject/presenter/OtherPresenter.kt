package com.hhy.openproject.presenter

import android.content.Context
import android.widget.Toast
import com.hhy.openproject.View.OtherView
import com.hhy.openproject.bean.GirlResp
import com.hhy.openproject.modle.OtherModle
import com.hhy.openproject.utils.JsonCallback
import com.lzy.okgo.model.Response

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class OtherPresenter : BasePresenter<OtherView> {
    private var modle: OtherModle? = null
    private var context: Context? = null

    constructor(context: Context?) : super() {
        this.modle = OtherModle()
        this.context = context
    }

    fun showOther(category: String, pageCount: Int, page: Int) {
        modle!!.getOther(object : JsonCallback<GirlResp>() {
            override fun onSuccess(response: Response<GirlResp>?) {
                if (response!!.body() != null && response!!.body()!!.results != null && !response!!.body()!!.results.isEmpty()) {
                    getView()!!.showOther(response!!.body()!!.results)
                } else if (page < 2) {
                    getView()!!.showFail("没有更多数据了")
                }
            }

            override fun onError(response: Response<GirlResp>?) {
                super.onError(response)
                getView()!!.showFail("网络异常，请稍后重试")
            }
        }, category, pageCount, page)
    }

}