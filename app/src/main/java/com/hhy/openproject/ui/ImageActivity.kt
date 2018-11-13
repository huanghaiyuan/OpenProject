package com.hhy.openproject.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hhy.openproject.R
import com.hhy.openproject.R.id.image_view
import com.hhy.openproject.View.IView
import com.hhy.openproject.presenter.BasePresenter
import com.hhy.openproject.utils.Constant
import kotlinx.android.synthetic.main.activity_girl.*
import kotlinx.android.synthetic.main.activity_image.*

/**
 * Created by huanghaiyuan on 2018/11/9.
 */
class ImageActivity : BaseActivity<IView, BasePresenter<IView>>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        image_view.enable()
        Glide.with(this@ImageActivity)
            .load(intent.getStringExtra(Constant.URL))
            .apply(
                RequestOptions()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .error(R.drawable.ic_error_m)
                    .placeholder(R.drawable.ic_error_m)
            )
            .into(image_view)
    }

    override fun getSubPresenter(): BasePresenter<IView>? {
        return BasePresenter()
    }
}