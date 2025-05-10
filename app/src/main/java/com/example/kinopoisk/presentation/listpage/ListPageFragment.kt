package com.example.kinopoisk.presentation.listpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.MainViewModel
import com.example.kinopoisk.MainViewModelFactory
import com.example.kinopoisk.R
import com.example.kinopoisk.data.Repository
import com.example.kinopoisk.databinding.FragmentListPageBinding
import kotlinx.coroutines.launch

class ListPageFragment : Fragment() {
    private lateinit var adapter: ListPageAdapter
    private val repository = Repository.newInstance()
    private val factory = MainViewModelFactory(repository)
    private val viewModel: MainViewModel by viewModels {factory}

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
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ListPageAdapter()
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