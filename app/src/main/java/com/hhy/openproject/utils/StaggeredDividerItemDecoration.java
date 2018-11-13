package com.hhy.openproject.utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

public class StaggeredDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int space;
    private boolean includeEdge;


    public StaggeredDividerItemDecoration(int spanCount, int space) {
        this.spanCount = spanCount;
        this.space = space;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        outRect.left = space;
        outRect.right = space;
        if(position!=0 && position!=1){
            outRect.top = 2*space;
        }else{
            outRect.top = space;
        }


    }
}