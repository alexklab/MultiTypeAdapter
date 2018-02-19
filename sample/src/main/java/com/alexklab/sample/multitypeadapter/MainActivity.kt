package com.alexklab.sample.multitypeadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alexklab.multitypeadapter.Loggable
import com.alexklab.multitypeadapter.MultiTypeAdapter
import com.alexklab.multitypeadapter.MultiTypeMarker
import com.alexklab.sample.multitypeadapter.ex.IconItem
import com.alexklab.sample.multitypeadapter.ex.SampleAdapter
import com.alexklab.sample.multitypeadapter.ex.TextItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MultiTypeAdapter.OnItemClickListener, Loggable {

    override fun onItemClicked(item: MultiTypeMarker) {
        Log.d(tag(), " on click item $item")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateUI()
    }

    private fun updateUI() {
        adapter = SampleAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setOnItemClickedListener(this)
        adapter.updateItems(listOf(
                TextItem("text one"),
                TextItem("Some awasome item two"),
                IconItem(android.R.color.holo_green_dark),
                TextItem("BLA BLA BLA"),
                TextItem("item item item item"),
                IconItem(android.R.color.holo_red_dark),
                TextItem("asfsF sfas Fasf dSFD sdf ds Fdsf sDFsdf"),
                TextItem("sadfsF   fff fFfs s s s s S SS ")
        ))
    }

    private lateinit var adapter: SampleAdapter
}
