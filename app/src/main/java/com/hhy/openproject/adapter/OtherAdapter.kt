package com.hhy.openproject.adapter

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hhy.openproject.R
import com.hhy.openproject.bean.CategoryInfoItem
import com.hhy.openproject.ui.ImageActivity
import com.hhy.openproject.utils.Constant
import java.text.SimpleDateFormat

/**
 * Created by huanghaiyuan on 2018/11/8.
 */
class OtherAdapter : BaseQuickAdapter<CategoryInfoItem, BaseViewHolder> {
    private var maneger: WindowManager? = null
    var displayWidth: Int = 0

    constructor(maneger: WindowManager?, layoutResId: Int, data: MutableList<CategoryInfoItem>?) : super(layoutResId, data) {
        this.maneger = maneger
        displayWidth = maneger!!.defaultDisplay.width
    }


    override fun convert(helper: BaseViewHolder?, item: CategoryInfoItem?) {
        var img: ImageView = helper!!.getView(R.id.adapter_other_image)
        if (item!!.images == null || item!!.images.isEmpty() || TextUtils.isEmpty(item!!.images[0])) {
            img.visibility = View.GONE
        } else {
            img.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    var intent = Intent(mContext, ImageActivity::class.java)
                    intent.putExtra(Constant.URL, item!!.images[0])
                    mContext!!.startActivity(intent)
                }
            })
            img.visibility = View.VISIBLE

            Glide.with(mContext).load(item!!.images[0]).apply(
                RequestOptions()
                    .error(R.drawable.ic_error_m)
                    .placeholder(R.drawable.ic_error_m)
                    .centerCrop()
            ).into(img)
        }
        helper.setText(R.id.adapter_other_title, item.desc)
            .setText(R.id.adapter_other_type, item.type)
            .setText(
                R.id.adapter_other_time,
                getTime(item.createdAt)
            )
    }

    private fun getTime(date: String): String {
        var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z")//注意格式化的表达式
        var s = format.parse(date.replace("Z", " UTC"))
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s)
    }
}