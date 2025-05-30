package com.example.kinopoisk.presentation.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.domain.entities.MovieSection
import com.example.kinopoisk.databinding.FragmentHomepageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomepageFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var sectionAdapter: SectionAdapter
    private lateinit var binding: FragmentHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepageBinding.inflate(inflater, container, false)

        sectionAdapter = SectionAdapter()
        binding.sectionRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sectionAdapter
        }


        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when(state) {
                    is HomeUiState.Loading -> showLoading()
                    is HomeUiState.Success -> setupSections(state.sections)
                    is HomeUiState.Error -> showError(state.exception)
                }
            }
        }

        return binding.root
    }
    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }
    fun setupSections(listSections: List<MovieSection>) {
        binding.progressBar.visibility = View.GONE
        sectionAdapter.submitList(listSections)

    }
    fun showError(e: Throwable) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(context, "Message: ${e.message}", Toast.LENGTH_SHORT).show()
        Log.d("TAGRTRT", "message: ${e.message}")
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshCountryGenre()
    }
}