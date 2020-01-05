package com.example.myapplication.feature.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.core_ui.platform.BaseFragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.search_activity.*

class SearchFragment : BaseFragment() {
    override fun layoutId(): Int {
        return R.layout.search_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}
