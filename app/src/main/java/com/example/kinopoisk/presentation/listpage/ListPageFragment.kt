package com.example.kinopoisk.presentation.listpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kinopoisk.MainViewModel
import com.example.kinopoisk.databinding.FragmentListPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListPageFragment : Fragment() {
    private lateinit var adapter: MovieGridAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentListPageBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter = MovieGridAdapter()
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.pagingData.collect { data ->
                adapter.submitData(data)
            }
        }

        return binding.root
    }

    companion object {
    }
}