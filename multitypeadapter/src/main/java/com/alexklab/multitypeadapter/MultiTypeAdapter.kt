package com.alexklab.multitypeadapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by alexk on 2/18/18.
 * Project MultiTypeAdapter
 */
abstract class MultiTypeAdapter(private val context: Context,
                                private var items: List<MultiTypeMarker>,
                                private var onItemClickedListener: OnItemClickListener? = null) : RecyclerView.Adapter<MultiTypeAdapter.ViewHolder>(), Loggable {

    interface OnItemClickListener {
        fun onItemClicked(item: MultiTypeMarker)
    }

    /** Declaration for holders **/
    abstract val buildersMap: Map<Int, List<ViewHolderBuilder>>

    fun setOnItemClickedListener(listener: OnItemClickListener) {
        onItemClickedListener = listener
    }

    fun removeOnItemClickedListener() {
        onItemClickedListener = null
    }

    fun updateItems(items: List<MultiTypeMarker>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].javaClass.getItemType()

    override fun onCreateViewHolder(parent: ViewGroup?, type: Int): ViewHolder =
            getBuilder(type).build(context, parent)

    private fun getBuilder(type: Int, item: MultiTypeMarker? = null): ViewHolderBuilder =
            buildersMap[type]?.firstOrNull() ?: defaultBuilder.apply {
                if (item != null) error("Undefined holder layout. Item=$item")
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val holderLayout = getBuilder(holder.itemViewType, item)
        holder.itemView?.setOnClickListener(item, holderLayout.isClickable)
        holder.onBindItem(item, holderLayout.layoutParams)
    }

    private fun View.setOnClickListener(item: MultiTypeMarker, isClickable: Boolean) =
            if (isClickable) {
                setOnClickListener { onItemClickedListener?.onItemClicked(item) }
            } else {
                setOnClickListener(null)
            }

    inline fun <reified T : Class<out MultiTypeMarker>> T.getItemType(): Int = simpleName.hashCode()

    private val defaultBuilder = ViewHolderBuilder(
            itemClass = MultiTypeMarker::class.java,
            itemLayoutRes = R.layout.simple_text_item,
            viewHolder = { UndefinedMultiTypeViewHolder(it) })

    /** View Holder. {@link RecyclerView.ViewHolder} **/
    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view), Loggable {

        interface LayoutParams

        open fun <ItemType : MultiTypeMarker> onBindItem(item: ItemType, layoutParams: LayoutParams) {
            error("onBindItem: undefined item type. position=$adapterPosition, item=$item")
        }
    }

    /** View Holder Builder **/
    class ViewHolderBuilder(
            val itemClass: Class<out MultiTypeMarker>,
            @LayoutRes private val itemLayoutRes: Int,
            private val viewHolder: (View) -> ViewHolder,
            val isClickable: Boolean = false,
            val layoutParams: ViewHolder.LayoutParams = object : ViewHolder.LayoutParams {}) {

        fun build(context: Context, parent: ViewGroup?): ViewHolder =
                viewHolder(buildView(context, parent, itemLayoutRes))

        private fun buildView(context: Context, parent: ViewGroup?, @LayoutRes layoutRes: Int): View =
                LayoutInflater.from(context).inflate(layoutRes, parent, false)
    }
}