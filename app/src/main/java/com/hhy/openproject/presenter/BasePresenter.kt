package com.hhy.openproject.presenter

import com.hhy.openproject.View.IView
import java.lang.ref.WeakReference

/**
 * Created by huanghaiyuan on 2018/11/6.
 */
open class BasePresenter<V : IView> {
    private var weakView: WeakReference<V>? = null

    fun getView(): V? {
        return weakView?.get()
    }

    private fun isAttachView(): Boolean {
        return this.weakView != null && weakView?.get() != null
    }

    /**
     *关联
     */
    fun attachView(view: V) {
        this.weakView = WeakReference(view)
    }

    /**
     * 判断view是否关联
     */
    fun isViewAttached(): Boolean {
        return weakView != null && weakView?.get() != null
    }

    /**
     * 释放
     */
    fun dettachView() {
        this.weakView!!.clear()
        this.weakView = null
    }
}