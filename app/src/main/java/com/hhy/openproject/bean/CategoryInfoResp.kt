package com.hhy.openproject.bean

data class CategoryInfoResp(
    var error: Boolean,
    var results: MutableList<CategoryInfoItem>
)