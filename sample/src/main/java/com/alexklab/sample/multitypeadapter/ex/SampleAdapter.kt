package com.alexklab.sample.multitypeadapter.ex

import android.content.Context
import com.alexklab.multitypeadapter.MultiTypeAdapter
import com.alexklab.multitypeadapter.MultiTypeMarker
import com.alexklab.sample.multitypeadapter.R

/**
 * Created by alexk on 2/19/18.
 * Project MultiTypeAdapter
 */
class SampleAdapter(context: Context, items: List<MultiTypeMarker> = emptyList()) : MultiTypeAdapter(context, items) {

    override val buildersMap: Map<Int, List<ViewHolderBuilder>> = listOf(
            ViewHolderBuilder(TextItem::class.java, R.layout.text_item_layout, { TextViewHolder(it) }, isClickable = true)
    ).groupBy { it.itemClass.getItemType() }
}