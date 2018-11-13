package com.hhy.openproject.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.hhy.openproject.R
import com.hhy.openproject.R.id.splash_root
import com.hhy.openproject.utils.Constant
import com.qq.e.ads.splash.SplashAD
import com.qq.e.ads.splash.SplashADListener
import com.qq.e.comm.util.AdError
import com.tbruyelle.rxpermissions2.Permission

import com.tbruyelle.rxpermissions2.RxPermissions
import com.tencent.smtt.utils.t
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_splash.*
import android.view.KeyEvent.KEYCODE_HOME
import android.view.KeyEvent.KEYCODE_BACK
import com.hhy.openproject.ProjectApplication


/**
 * Created by huanghaiyuan on 2018/11/12.
 */
class SplashActivity : FragmentActivity(), SplashADListener {
    private var ad: SplashAD? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(Constant.SHOW_AD){
            splash_img.visibility = View.GONE
            initPermissions()
        }else{
           splash_img.visibility = View.VISIBLE
            splash_img.postDelayed(object: Runnable {
                override fun run() {
                    startMain()
                }
            }, 3*1000)
        }
    }

    private fun initPermissions() {
        RxPermissions(this)
            .requestEach(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe(object : Consumer<Permission?> {
                override fun accept(t: Permission?) {
                    if (t!!.granted) {
                        Log.e("hhy", "授权成功")
                        if (ad == null) {
                            ad = SplashAD(
                                this@SplashActivity,
                                splash_root,
                                null,
                                Constant.APPID,
                                Constant.SplashPosID,
                                this@SplashActivity,
                                3000
                            )
                        }
                    } else if (t!!.shouldShowRequestPermissionRationale) {
                        Toast.makeText(this@SplashActivity, "授权失败", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@SplashActivity, "您已拒绝授权，请重新授权", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    override fun onADExposure() {
        splash_img.visibility = View.GONE
    }

    override fun onADDismissed() {
        startMain()
    }

    override fun onADPresent() {
    }

    override fun onNoAD(p0: AdError?) {
        startMain()
    }

    private fun startMain() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    override fun onADClicked() {
    }

    override fun onADTick(p0: Long) {
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            true
        } else super.onKeyDown(keyCode, event)
    }
}