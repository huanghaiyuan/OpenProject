package com.hhy.openproject.presenter

import android.content.Context
import com.google.gson.Gson
import com.hhy.openproject.View.MainView
import com.hhy.openproject.bean.OtherItem
import com.hhy.openproject.bean.OtherResp
import com.hhy.openproject.modle.MainModle
import com.hhy.openproject.utils.*
import com.lzy.okgo.model.Response

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class MainPresenter : BasePresenter<MainView> {
    private var modle: MainModle? = null
    private var context: Context? = null


    private var categroyList: MutableList<Category> = mutableListOf(
        Category.ALL, Category.ANDROID,
        Category.iOS, Category.VIDEO, Category.GIRL, Category.EXPAND,
        Category.WEB, Category.RECOMEND, Category.APP
    )
    private var todayList: MutableList<OtherItem> = mutableListOf()

    constructor(context: Context?) : super() {
        this.modle = MainModle()
        this.context = context


    }

    fun getToday() {
        modle!!.getToday(object : JsonCallback<OtherResp>() {
            override fun onSuccess(response: Response<OtherResp>?) {
                todayList.clear()
                getView()!!.showViewPager(response!!.body().results!!.福利!!)
                todayList.addAll(response!!.body().results!!.Android!!)
                todayList.addAll(response!!.body().results!!.App!!)
                todayList.addAll(response!!.body().results!!.iOS!!)
                todayList.addAll(response!!.body().results!!.休息视频!!)
                todayList.addAll(response!!.body().results!!.前端!!)
                todayList.addAll(response!!.body().results!!.拓展资源!!)
                todayList.addAll(response!!.body().results!!.瞎推荐!!)

                todayList.sortByDescending { it.createdAt }
                getView()!!.showToday(todayList)
            }

            override fun onError(response: Response<OtherResp>?) {
                super.onError(response)
                getView()!!.showFail("网络异常，请稍后重试")
            }
        })
    }


    fun showCategroy() {
        getView()!!.showCategory(categroyList)
    }


}