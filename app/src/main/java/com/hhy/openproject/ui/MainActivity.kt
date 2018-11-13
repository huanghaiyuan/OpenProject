package com.hhy.openproject.ui

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import com.hhy.openproject.R
import com.hhy.openproject.View.MainView
import com.hhy.openproject.adapter.OtherAdapter
import com.hhy.openproject.adapter.CategoryAdapter
import com.hhy.openproject.adapter.ViewPagerAdapter
import com.hhy.openproject.bean.OtherItem
import com.hhy.openproject.presenter.MainPresenter
import com.hhy.openproject.utils.Category
import com.hhy.openproject.utils.Constant
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.layout_top.*


class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView,View.OnClickListener{
    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var otherAdapter: OtherAdapter? = null
    private var categoryAdapter: CategoryAdapter? = null
    private var layoutManager: GridLayoutManager = GridLayoutManager(this, 5)
    private var otherString: String by com.hhy.openproject.utils.Preference(this, "aaa", "")
    private var viewPagerString: String by com.hhy.openproject.utils.Preference(this, "bbb", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        top_more.visibility = View.VISIBLE
        top_more.setOnClickListener(this)
        main_drawlayout_left.setOnClickListener(this)

        presenter!!.showCategroy()
        main_refresh.setRefreshHeader(BezierRadarHeader(this)
            .setEnableHorizontalDrag(true)
            .setPrimaryColorId(R.color.color_ffffff)
            .setAccentColorId(R.color.color_44008577)
        ).setOnRefreshListener(object: OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                presenter!!.getToday()
            }
        }).autoRefresh()

    }

    override fun getSubPresenter(): MainPresenter? {
        return MainPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPagerAdapter!!.destoryHandler()
    }

    override fun showViewPager(girl: MutableList<OtherItem>) {
        viewPagerString = Gson().toJson(girl)
        viewPagerAdapter = ViewPagerAdapter(
            this@MainActivity,
            main_viewPager,
            main_indicator_layout,
            girl
        )
        main_viewPager.adapter = viewPagerAdapter
    }

    override fun showCategory(categroyList: MutableList<Category>) {
        categoryAdapter = CategoryAdapter(windowManager, R.layout.adapter_categroy, categroyList)
        main_category.adapter = categoryAdapter
        main_category.layoutManager = layoutManager
        categoryAdapter!!.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                var intent = Intent()
                if (categroyList[position] == Category.GIRL) {
                    intent.setClass(this@MainActivity, GirlActivity::class.java)
                } else {
                    intent.setClass(this@MainActivity, OtherActivity::class.java)
                    intent.putExtra(Constant.CATEGORY, categroyList[position].value)
                }
                this@MainActivity.startActivity(intent)
            }
        })
    }

    override fun showToday(resp: MutableList<OtherItem>) {
        otherString = Gson().toJson(resp)
        main_refresh.finishRefresh()
        otherAdapter = OtherAdapter(windowManager, R.layout.adapter_other, resp)
        main_other.layoutManager = LinearLayoutManager(this)
        main_other.adapter = otherAdapter
        main_other.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        otherAdapter!!.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                var intent = Intent(this@MainActivity, WebActivity::class.java)
                intent.putExtra(Constant.URL, resp.get(position).url)
                intent.putExtra(Constant.TITLE, resp.get(position).desc)
                this@MainActivity.startActivity(intent)
            }
        })
    }


    override fun showFail(msg:String) {
        main_refresh.finishRefresh()
        showToday(Gson().fromJson(otherString, object : TypeToken<List<OtherItem>>() {}.type))
        showViewPager(Gson().fromJson(viewPagerString, object : TypeToken<List<OtherItem>>() {}.type))
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.top_more->{
                main_drawlayout.openDrawer(main_drawlayout_left)
            }
            R.id.main_drawlayout_left->{
                main_drawlayout.closeDrawer(main_drawlayout_left)
            }
        }
    }
}
