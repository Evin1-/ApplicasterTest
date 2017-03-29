package com.example.applicastertest.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 3/29/17.
 */

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public SimpleItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;

        if (parent.getChildAdapterPosition(view) < 1)
            outRect.top = mSpace;
    }
}