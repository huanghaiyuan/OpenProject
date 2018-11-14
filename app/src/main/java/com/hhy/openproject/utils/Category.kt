package com.hhy.openproject.utils

import com.hhy.openproject.R

/**
 * Created by huanghaiyuan on 2018/11/3.
 */
enum class Category(var imgId: Int, var value: String) {

    //    all | CategoryInfoItem | iOS | Video | Girl | Expand | 前端 | Recomend | App
    ALL(R.drawable.img_all, "all"),
    ANDROID(R.drawable.img_android, "Android"),
    iOS(R.drawable.img_ios, "iOS"),
    VIDEO(R.drawable.img_video, "Video"),
    GIRL(R.drawable.img_girl, "福利"),
    EXPAND(R.drawable.img_expand, "Expand"),
    WEB(R.drawable.img_web, "前端"),
    RECOMEND(R.drawable.img_recomed, "Recomend"),
    APP(R.drawable.img_app, "App");
}