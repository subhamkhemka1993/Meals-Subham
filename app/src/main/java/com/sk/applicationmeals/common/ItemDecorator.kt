package com.sk.applicationmeals.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sk.applicationmeals.ui.category.CategoryAdapter
import com.sk.applicationmeals.ui.meals.MealsAdapter

class ItemDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val adapter = parent.adapter
        val position = parent.layoutManager?.getPosition(view) ?: 0
        val itemCount = adapter?.itemCount ?: 0
        when (adapter) {
            is CategoryAdapter -> {
                if (position != itemCount.minus(1)) {
                    outRect.bottom = spacing
                }
                outRect.left = spacing / 2
                outRect.right = spacing / 2
            }

            is MealsAdapter -> {
                if (position % 2 == 0) {
                    outRect.right = spacing / 2
                    outRect.left = spacing
                } else {
                    outRect.left = spacing / 2
                    outRect.right = spacing
                }
                outRect.bottom = spacing
            }
        }
    }
}