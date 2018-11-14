package com.hhy.openproject.bean
data class TodayResp(
    var category: MutableList<String>,
    var error: Boolean,
    var results: Results
)