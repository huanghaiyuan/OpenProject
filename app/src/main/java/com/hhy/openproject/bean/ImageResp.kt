package com.hhy.openproject.bean

/**
 * Created by huanghaiyuan on 2018/11/1.
 */

data class ImageRspe(
    var error: Boolean,
    var results: MutableList<ResultItem>
)

data class ResultItem(
    var _id: String,
    var createdAt: String,
    var desc: String,
    var publishedAt: String,
    var source: String,
    var type: String,
    var url: String,
    var used: Boolean,
    var who: String
)