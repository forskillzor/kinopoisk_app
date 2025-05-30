package com.example.kinopoisk.presentation.listpage

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.presentation.listpage.ListPageViewModel
import com.example.kinopoisk.databinding.FragmentListPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListPageFragment : Fragment() {
    private lateinit var adapter: MovieGridAdapter
    private val viewModel: ListPageViewModel by viewModels()
    private val args: ListPageFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadMovies(args.collectionType)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentListPageBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left = 16
                outRect.right = 16
                outRect.top = 16
                outRect.bottom = 16
            }
        })
        adapter = MovieGridAdapter { id ->
            ListPageFragmentDirections.actionListPageFragmentToMovieDetailFragment(id).let {
                binding.root.findNavController().navigate(it)
            }
        }
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