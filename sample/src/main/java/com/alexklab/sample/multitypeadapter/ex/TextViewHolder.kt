package com.alexklab.sample.multitypeadapter.ex

import android.view.View
import com.alexklab.sample.multitypeadapter.lib.MultiTypeAdapter
import com.alexklab.sample.multitypeadapter.lib.MultiTypeMarker
import kotlinx.android.synthetic.main.text_item_layout.view.*

/**
 * Created by alexk on 2/19/18.
 * Project MultiTypeAdapter
 */
class TextViewHolder(view: View) : MultiTypeAdapter.ViewHolder(view) {

    override fun <ItemType : MultiTypeMarker> onBindItem(item: ItemType, layoutParams: LayoutParams) = when (item) {
        is TextItem -> updateUI(item)
        else -> super.onBindItem(item, layoutParams)
    }

    private fun updateUI(item: TextItem) = with(itemView) {
        itemTextView.text = item.text
    }
}