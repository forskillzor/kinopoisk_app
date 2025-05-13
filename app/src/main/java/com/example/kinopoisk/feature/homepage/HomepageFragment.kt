package com.example.kinopoisk.feature.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.databinding.FragmentHomepageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomepageFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomepageBinding.inflate(inflater, container, false)

        homeAdapter = HomeAdapter()
        binding.sectionRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }


        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when(state) {
                    is HomeUiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is HomeUiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        homeAdapter.submitList(state.sections)
                    }
                    is HomeUiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "mesage", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return binding.root
    }
}