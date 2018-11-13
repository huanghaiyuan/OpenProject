package com.hhy.openproject.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hhy.openproject.R
import com.hhy.openproject.bean.OtherItem
import com.hhy.openproject.ui.GirlActivity

/**
 * Created by huanghaiyuan on 2018/11/3.
 */
class GirlAdapter : BaseQuickAdapter<OtherItem, BaseViewHolder> {
    private var activiry: GirlActivity? = null

    constructor(activiry: GirlActivity?, layoutResId: Int, data: MutableList<OtherItem>?) : super(layoutResId, data) {
        this.activiry = activiry
    }


    override fun convert(helper: BaseViewHolder, item: OtherItem) {
        Glide.with(mContext)
            .load(item.url)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_error_m)
                    .placeholder(R.drawable.ic_error_m)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .override(
                        (activiry!!.displayWidth - 30) * 1 / 3,
                        activiry!!.displayHeight * 1 / 5 + helper.adapterPosition % 5 * 10
                    )
            )
            .into(
                helper!!.itemView.findViewById(R.id.adapter_girl)
            )
    }
}