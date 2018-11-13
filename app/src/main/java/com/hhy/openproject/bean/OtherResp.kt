package com.hhy.openproject.bean

import java.io.Serializable

data class OtherResp(
    var category: List<String>,
    var error: Boolean,
    var results: Results
)