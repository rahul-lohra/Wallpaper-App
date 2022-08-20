package com.rahul.wallpaper.feature.search.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.wallpaper.App
import com.rahul.wallpaper.R
import com.rahul.wallpaper.feature.search.di.components.DaggerSearchComponent
import com.rahul.wallpaper.feature.search.ui.adapters.SearchAdapter
import com.rahul.wallpaper.feature.search.ui.viewmodels.SearchViewModel
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText

    private val srcList = listOf(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    )
    private lateinit var adapter: SearchAdapter

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerSearchComponent.factory()
            .create((context.applicationContext as App).appComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = LayoutInflater.from(context).inflate(R.layout.fragment_search, container, false)
        recyclerView = v.findViewById(R.id.recycler_view)
        searchBar = v.findViewById(R.id.search_bar)

        val textList = arrayListOf<String>()
        textList.addAll(srcList)
        adapter = SearchAdapter(textList)

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        handleSearch()
        return v
    }

    private fun updateAdapterList(dstList:List<String>){
        adapter.textList.clear()
        adapter.textList.addAll(dstList)
        adapter.notifyDataSetChanged()
    }

    fun handleSearch() {
        searchBar.doAfterTextChanged { editable ->
            if (editable != null) {
                val text = editable.toString()
                if (text.isEmpty()) {

                    val dstList = arrayListOf<String>()
                    dstList.addAll(srcList)
                    updateAdapterList(dstList)

                } else {
                    val dstList = arrayListOf<String>()
                    dstList.addAll(srcList.filter {
                        it.startsWith(text)
                    })

                    updateAdapterList(dstList)
                }

            }
        }
    }
}