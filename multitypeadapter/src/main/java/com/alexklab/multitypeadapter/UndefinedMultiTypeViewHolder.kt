package com.alexklab.multitypeadapter

import android.view.View
import com.alexklab.multitypeadapter.R
import kotlinx.android.synthetic.main.simple_text_item.view.*

/**
 * Created by alexk on 2/18/18.
 * Project MultiTypeAdapter
 */
class UndefinedMultiTypeViewHolder(view: View) : MultiTypeAdapter.ViewHolder(view) {

    override fun <ItemType : MultiTypeMarker> onBindItem(item: ItemType, layoutParams: LayoutParams) = with(itemView) {
        textView.text = context.getString(R.string.undefined_item_title, item.javaClass.simpleName)
    }
}