package com.hhy.openproject.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hhy.openproject.R
import com.hhy.openproject.view.GirlView
import com.hhy.openproject.adapter.GirlAdapter
import com.hhy.openproject.bean.CategoryInfoItem
import com.hhy.openproject.presenter.GirlPresenter
import com.hhy.openproject.utils.Constant
import com.hhy.openproject.utils.StaggeredDividerItemDecoration
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_girl.*
import kotlinx.android.synthetic.main.layout_banner.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_top.*

/**
 * Created by huanghaiyuan on 2018/11/9.
 */
class GirlActivity : BaseActivity<GirlView, GirlPresenter>(), GirlView, View.OnClickListener {
    var page = 1
    var pageCount = 10
    var girlAdapter: GirlAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_girl)
        top_back.visibility = View.VISIBLE
        top_back.setOnClickListener(this)
        top_title.setText("福利")
        error_layout.setOnClickListener(this)

        girl_refresh.setRefreshHeader(
            BezierRadarHeader(this)
                .setEnableHorizontalDrag(true)
                .setPrimaryColorId(R.color.color_ffffff)
                .setAccentColorId(R.color.color_44008577)
        ).setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                presenter!!.showGirl(pageCount, page)
            }
        }).setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                presenter!!.showGirl(pageCount, page)
            }
        }).autoRefresh()
    }

    override fun getSubPresenter(): GirlPresenter? {
        return GirlPresenter(this)
    }

    override fun showGirl(list: MutableList<CategoryInfoItem>) {
        error_layout.visibility = View.GONE
        initBannerAD()
        if (girlAdapter == null) {
            girlAdapter = GirlAdapter(this, R.layout.adapter_girl, list)
            girl_recyclerview.adapter = girlAdapter
            var manager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            girl_recyclerview.layoutManager = manager
            manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE)
            girl_recyclerview.addItemDecoration(StaggeredDividerItemDecoration(3, 5))
            girl_refresh.finishRefresh()
            girlAdapter!!.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                    var intent = Intent(this@GirlActivity, ImageActivity::class.java)
                    intent.putExtra(Constant.URL, (adapter!!.data.get(position) as CategoryInfoItem).url)
                    startActivity(intent)
                }
            })
        } else {
            if (page > 1) {
                girl_refresh.finishLoadMore()
                girlAdapter!!.addData(list)
            } else {
                girl_refresh.finishRefresh()
                girlAdapter!!.setNewData(list)
            }
        }
    }

    override fun showFail(msg: String) {
        if (page > 1) {
            girl_refresh.finishLoadMore()
        } else {
            girl_refresh.finishRefresh()
        }
        if (girlAdapter == null || girlAdapter?.data == null || girlAdapter!!.data.isEmpty()) {
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