package com.hhy.openproject.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hhy.openproject.R
import com.hhy.openproject.view.OtherView
import com.hhy.openproject.adapter.OtherAdapter
import com.hhy.openproject.bean.CategoryInfoItem
import com.hhy.openproject.presenter.OtherPresenter
import com.hhy.openproject.utils.Constant
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_other.*
import kotlinx.android.synthetic.main.layout_banner.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_top.*

/**
 * Created by huanghaiyuan on 2018/11/9.
 */
class OtherActivity : BaseActivity<OtherView, OtherPresenter>(), OtherView, View.OnClickListener {
    private var adapter: OtherAdapter? = null
    private var page = 1
    private var pageCount = 10
    private var title: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        top_back.visibility = View.VISIBLE
        title = intent.getStringExtra(Constant.CATEGORY)
        top_title.setText(title)
//        presenter!!.showOther(title!!, pageCount, page)
        top_back.setOnClickListener(this)
        error_layout.setOnClickListener(this)

        other_refresh.setRefreshHeader(
            BezierRadarHeader(this)
                .setEnableHorizontalDrag(true)
                .setPrimaryColorId(R.color.color_ffffff)
                .setAccentColorId(R.color.color_44008577)
        ).setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                presenter!!.showOther(title!!, pageCount, page)
            }
        }).setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                presenter!!.showOther(title!!, pageCount, page)
            }
        }).autoRefresh()
    }

    override fun getSubPresenter(): OtherPresenter? {
        return OtherPresenter(this)
    }


    override fun showOther(list: MutableList<CategoryInfoItem>) {
        error_layout.visibility = View.GONE
        initBannerAD()
        if (adapter == null) {
            adapter = OtherAdapter(windowManager, R.layout.adapter_other, list)
            other_recycler.layoutManager = LinearLayoutManager(this)
            other_recycler.adapter = adapter
            other_refresh.finishRefresh()
        } else {
            if (page > 1) {
                adapter!!.addData(list)
                other_refresh.finishLoadMore()
            } else {
                other_refresh.finishRefresh()
                adapter!!.setNewData(list)
            }
        }
        if (list.size < pageCount)
            adapter!!.loadMoreEnd()
        else
            adapter!!.loadMoreComplete()
        other_recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        adapter!!.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                var intent = Intent(this@OtherActivity, WebActivity::class.java)
                intent.putExtra(Constant.URL, (adapter!!.data.get(position) as CategoryInfoItem).url)
                intent.putExtra(Constant.TITLE, (adapter!!.data.get(position) as CategoryInfoItem).desc)
                this@OtherActivity.startActivity(intent)
            }
        })
    }

    override fun showFail(msg: String) {
        if (page > 1) {
            other_refresh.finishLoadMore()
        } else {
            other_refresh.finishRefresh()
        }

        if (adapter == null || adapter?.data == null || adapter!!.data.isEmpty()) {
            error_layout.visibility = View.VISIBLE
            error_msg.setText(msg)
        }
        ad_layout.visibility = View.GONE
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.top_back -> {
                finish()
            }
            R.id.error_layout -> {

            }
        }
    }
}