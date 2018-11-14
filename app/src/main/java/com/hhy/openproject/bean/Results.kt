package com.hhy.openproject.bean

data class Results(
    var Android: MutableList<CategoryInfoItem>,
    var App: MutableList<CategoryInfoItem>,
    var iOS: MutableList<CategoryInfoItem>,
    var 休息视频: MutableList<CategoryInfoItem>,
    var 前端: MutableList<CategoryInfoItem>,
    var 拓展资源: MutableList<CategoryInfoItem>,
    var 瞎推荐: MutableList<CategoryInfoItem>,
    var 福利: MutableList<CategoryInfoItem>
)