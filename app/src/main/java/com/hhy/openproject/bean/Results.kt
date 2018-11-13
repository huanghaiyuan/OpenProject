package com.hhy.openproject.bean

data class Results(
    var Android: MutableList<OtherItem>,
    var App: MutableList<OtherItem>,
    var iOS: MutableList<OtherItem>,
    var 休息视频: MutableList<OtherItem>,
    var 前端: MutableList<OtherItem>,
    var 拓展资源: MutableList<OtherItem>,
    var 瞎推荐: MutableList<OtherItem>,
    var 福利: MutableList<OtherItem>
)