package com.hhy.openproject.presenter

import android.content.Context
import android.text.TextUtils
import com.hhy.openproject.view.MainView
import com.hhy.openproject.bean.CategoryInfoItem
import com.hhy.openproject.bean.CategoryInfoResp
import com.hhy.openproject.bean.TodayResp
import com.hhy.openproject.modle.MainModle
import com.hhy.openproject.utils.*
import com.lzy.okgo.model.Response

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
class MainPresenter : BasePresenter<MainView> {
    private var modle: MainModle? = null
    private var context: Context? = null
    private var girls: MutableList<CategoryInfoItem> = mutableListOf()
    private var others: MutableList<CategoryInfoItem> = mutableListOf()

    private var categroyList: MutableList<Category> = mutableListOf(
        Category.ALL, Category.ANDROID,
        Category.iOS, Category.VIDEO, Category.GIRL, Category.EXPAND,
        Category.WEB, Category.RECOMEND, Category.APP
    )
    private var todayList: MutableList<CategoryInfoItem> = mutableListOf()

    constructor(context: Context?) : super() {
        this.modle = MainModle()
        this.context = context


    }

    fun getToday() {
        modle!!.getToday(object : JsonCallback<TodayResp>() {
            override fun onSuccess(response: Response<TodayResp>?) {
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
                getView()!!.showMainList(todayList)
            }

            override fun onError(response: Response<TodayResp>?) {
                super.onError(response)
                getView()!!.showFail("网络异常，请稍后重试")
            }
        })
    }


    fun getRandom() {
        modle!!.getRandom(object : JsonCallback<CategoryInfoResp>() {
            override fun onSuccess(response: Response<CategoryInfoResp>?) {
                girls.clear()
                others.clear()
                for (i in 0 until response!!.body().results.size - 2) {
                    if (TextUtils.equals(response!!.body().results[i].type, Category.GIRL.value)) {
                        girls.add(response!!.body().results[i])
                    } else {
                        others.add(response!!.body().results[i])
                    }
                }
                getView()!!.showViewPager(girls)
                getView()!!.showMainList(others)
            }
            override fun onError(response: Response<CategoryInfoResp>?) {
                super.onError(response)
                getView()!!.showFail("网络异常，请稍后重试")
            }
        })

    }

    fun showCategroy() {
        getView()!!.showCategory(categroyList)
    }


}