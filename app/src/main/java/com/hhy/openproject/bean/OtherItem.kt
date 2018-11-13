package com.hhy.openproject.bean

data class OtherItem(
    var _id: String,
    var createdAt: String,
    var desc: String,
    var images: MutableList<String>,
    var publishedAt: String,
    var source: String,
    var type: String,
    var url: String,
    var used: Boolean,
    var who: String
)