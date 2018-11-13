package com.hhy.openproject.presenter

import android.content.Context
import com.hhy.openproject.View.GirlView
import com.hhy.openproject.bean.GirlResp
import com.hhy.openproject.bean.OtherItem
import com.hhy.openproject.modle.GirlModle
import com.hhy.openproject.utils.JsonCallback
import com.lzy.okgo.model.Response

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class GirlPresenter : BasePresenter<GirlView> {
    private var modle: GirlModle? = null
    private var context: Context? = null
    private var categoryItems: MutableList<OtherItem> = mutableListOf()

    constructor(context: Context?) : super() {
        this.modle = GirlModle()
        this.context = context
    }

    fun showGirl(pageCount: Int, page: Int) {
        modle!!.getGirl(object : JsonCallback<GirlResp>() {
            override fun onSuccess(response: Response<GirlResp>?) {
                if (response!!.body() == null || response!!.body().results == null || response!!.body().results.isEmpty())
                    getView()!!.showFail("没有更多数据了")
                else
                    getView()!!.showGirl(response!!.body()!!.results!!)
            }

            override fun onError(response: Response<GirlResp>?) {
                super.onError(response)
                getView()!!.showFail("网络异常，请稍后重试")
            }
        }, pageCount, page)
    }

}