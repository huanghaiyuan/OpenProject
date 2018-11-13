package com.hhy.openproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hhy.openproject.R
import com.hhy.openproject.bean.OtherItem
import com.hhy.openproject.ui.ImageActivity
import com.hhy.openproject.utils.Constant

/**
 * Created by huanghaiyuan on 2018/11/1.
 */
class ViewPagerAdapter : PagerAdapter, ViewPager.OnPageChangeListener {
    private val datas: MutableList<OtherItem>
    private var mViews: MutableList<View> = mutableListOf()
    private var context: Context
    private var viewPager: ViewPager
    private var layoutInflater: LayoutInflater
    private var indicatorLayout: LinearLayout
    private var currentPosition: Int = 0
    private var time: Long = 5
    private var handler: Handler = Handler()
    private var isTouch: Boolean = false
    private var tmpX: Float = 0f

    @SuppressLint("ClickableViewAccessibility")
    constructor(context: Context, pager: ViewPager, indicatorLayout: LinearLayout, datas: MutableList<OtherItem>) {
        this.datas = datas
        this.context = context
        this.viewPager = pager
        layoutInflater = LayoutInflater.from(context)
        this.indicatorLayout = indicatorLayout
        viewPager.removeAllViews()
        indicatorLayout.removeAllViews()
        if (!datas.isEmpty()) {
            //先购造好数据
            if (datas.size > 1) {
                datas.add(0, datas.get(datas.size - 1))//增加尾部view到首位
                datas.add(datas.get(1))//增加首部view到末位
            }
            //根据构造数据添加pager
            for (i in 0 until datas.size) {
                var view: View = layoutInflater.inflate(R.layout.adapter_viewpager_item, viewPager, false)
                Glide.with(context)
                    .load(datas.get(i).url)
                    .into(view.findViewById<ImageView>(R.id.adapter_viewpager_image))
                view.findViewById<TextView>(R.id.adapter_viewpager_title).setText("这是第"+i+"张")
                mViews.add(view)
            }
            //添加真实的索引和定时器
            if (datas.size > 1) {
                for (i in 0 until datas.size - 2) {
                    var indicator: View =
                        layoutInflater.inflate(R.layout.adapter_notice_indicator, indicatorLayout, false)
                    indicatorLayout.addView(indicator)
                }
                initTime()
            }
            viewPager.addOnPageChangeListener(this)
            viewPager.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                    when (event!!.action) {
                        MotionEvent.ACTION_DOWN -> {
                            tmpX = event.x
                            isTouch = true
                            handler.removeCallbacksAndMessages(null)
                        }
                        MotionEvent.ACTION_MOVE -> {

                        }
                        MotionEvent.ACTION_UP -> {
                            isTouch = false
                            val dis = event.x - tmpX
                            if (Math.abs(dis) < 5 && !TextUtils.isEmpty(datas.get(viewPager.getCurrentItem()).url)) {
                                var intent = Intent(context, ImageActivity::class.java)
                                intent.putExtra(Constant.URL, datas.get(viewPager.getCurrentItem()).url)
                                context.startActivity(intent)
                            }
                        }
                    }
                    return false
                }
            })
        }
    }

    private fun initTime() {
        handler.post(object :Runnable{
            override fun run() {
                viewPager.currentItem = 1
            }
        })

        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isTouch) {
                    return
                }
                currentPosition++
                viewPager.setCurrentItem(currentPosition)
                handler.postDelayed(this, time * 1000)
            }
        }, time * 1000)
    }

    fun destoryHandler() {
        handler!!.removeCallbacksAndMessages(null)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = mViews.get(position)
        if (container == view.parent) {
            container.removeView(view)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }

    override fun getCount(): Int {
        return datas!!.size
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }


    override fun onPageSelected(position: Int) {
        currentPosition = position
        if (position == 0) {
            viewPager.setCurrentItem(mViews.size - 2, false)
        } else if (position >= mViews.size - 1) {
            viewPager.setCurrentItem(1, false)
            setCurrIndicator(0)
        }
        setCurrIndicator(currentPosition - 1)
    }

    private fun setCurrIndicator(position: Int) {
        for (i in 0 until indicatorLayout.childCount) {
            val view = indicatorLayout.getChildAt(i)
            view.setBackgroundResource(R.drawable.bg_notice_shape_circle_gray)
            if (position == i) {
                view.setBackgroundResource(R.drawable.bg_notice_shape_circle_orange)
            }
        }
    }
}