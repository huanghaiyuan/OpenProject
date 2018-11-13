package com.hhy.openproject

import android.app.Application
import com.lzy.okgo.OkGo

/**
 * Created by huanghaiyuan on 2018/10/31.
 */
class ProjectApplication : Application {
    companion object {// 伴生对象
    lateinit var instance: ProjectApplication
        private set
    }
    constructor() : super()
    override fun onCreate() {
        super.onCreate()
        instance = this
        OkGo.getInstance().init(this)
    }

}