package com.hhy.openproject.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import com.hhy.openproject.view.IView
import com.hhy.openproject.presenter.BasePresenter
import com.hhy.openproject.utils.Constant
import com.hhy.openproject.utils.StatusBarCompat
import com.qq.e.ads.banner.ADSize
import com.qq.e.ads.banner.AbstractBannerADListener
import com.qq.e.ads.banner.BannerView
import com.qq.e.comm.util.AdError
import kotlinx.android.synthetic.main.layout_banner.*

/**
 * Created by huanghaiyuan on 2018/11/5.
 */
abstract class BaseActivity<V : IView, P : BasePresenter<V>> : AppCompatActivity(), IView {
    var displayWidth: Int = 0
    var displayHeight: Int = 0
    var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        StatusBarCompat.compat(this)
        displayWidth = windowManager.defaultDisplay.width
        displayHeight = windowManager.defaultDisplay.height
        presenter = getSubPresenter()
        presenter!!.attachView(this as V)
    }

    abstract fun getSubPresenter(): P?


    override fun onDestroy() {
        super.onDestroy()
        presenter!!.dettachView()
    }

    fun initBannerAD() {
        if (ad_info == null || !Constant.SHOW_AD) return
        var banner = BannerView(this, ADSize.BANNER, Constant.APPID, Constant.BannerPosID)
        //设置广告轮播时间，为0或30~120之间的数字，单位为s,0标识不自动轮播
        banner.setRefresh(1)
        banner.setADListener(object : AbstractBannerADListener() {
            override fun onNoAD(p0: AdError?) {
            }

            override fun onADReceiv() {
            }
        })
        banner.loadAD()
        if (ad_info.childCount < 1)
            ad_info.addView(banner)
        ad_layout.visibility = View.VISIBLE
        ad_close.setOnClickListener(View.OnClickListener {
            ad_layout.visibility = View.GONE
        })
    }
}
