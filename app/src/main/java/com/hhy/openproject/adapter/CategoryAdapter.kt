package com.hhy.openproject.adapter

import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hhy.openproject.R
import com.hhy.openproject.utils.Category

/**
 * Created by huanghaiyuan on 2018/11/10.
 */
class CategoryAdapter : BaseQuickAdapter<Category, BaseViewHolder> {
    private var maneger: WindowManager? = null
    private var displayWidth = 0

    constructor(maneger: WindowManager?, layoutResId: Int, data: MutableList<Category>?) : super(layoutResId, data) {
        this.maneger = maneger
        displayWidth = maneger!!.defaultDisplay.width
    }

    override fun convert(helper: BaseViewHolder?, item: Category?) {
        Glide.with(mContext).load(item!!.imgId).apply(
            RequestOptions()
                .error(R.drawable.ic_error_m)
                .placeholder(R.drawable.ic_error_m)
                .centerCrop()
                .override(displayWidth / 11, displayWidth / 11)
        ).into(helper!!.getView(R.id.adapter_categroy_image))
        helper.setText(R.id.adapter_categroy_title, item.value)
    }
}