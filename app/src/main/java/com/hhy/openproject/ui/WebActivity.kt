package com.hhy.openproject.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.hhy.openproject.R
import com.hhy.openproject.View.IView
import com.hhy.openproject.presenter.BasePresenter
import com.hhy.openproject.utils.Constant
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.layout_top.*

/**
 * Created by huanghaiyuan on 2018/11/8.
 */
class WebActivity : BaseActivity<IView, BasePresenter<IView>>(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        top_back.visibility = View.VISIBLE
        top_back.setOnClickListener(this)
        top_title.visibility = View.GONE
        top_sub_title.visibility = View.VISIBLE
        top_sub_title.setText(intent.getStringExtra(Constant.TITLE))
        webview.loadUrl(intent.getStringExtra(Constant.URL))

        webview.setWebChromeClient(MyWebChromeClient())
        webview.setWebViewClient(MyWebViewClient())

        webview.getSettings().setJavaScriptEnabled(true)//允许使用js
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE)

    }

    override fun getSubPresenter(): BasePresenter<IView>? {
        return BasePresenter()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (webview.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {//点击返回按钮的时候判断有没有上一页
            webview.goBack() // goBack()表示返回webView的上一页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        webview.destroy()
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.top_back -> {
                this.finish()
            }
        }
    }

    inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            web_progress.progress = newProgress
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            web_progress.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            web_progress.visibility = View.GONE
        }
    }
}