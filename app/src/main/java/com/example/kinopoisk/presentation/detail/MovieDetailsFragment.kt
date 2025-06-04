package com.example.kinopoisk.presentation.detail

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentDetailMovieBinding
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.entities.StaffType
import com.example.kinopoisk.extentions.launchAndCollectIn
import com.example.kinopoisk.presentation.detail.adapter.StaffAdapter
import com.example.kinopoisk.presentation.home.HorizontalMovieListAdapter
import com.example.kinopoisk.utils.formatAgeLimit
import com.example.kinopoisk.utils.formatMinutesToHoursAndMinutes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private val viewModel: DetailMovieViewModel by viewModels()
    private lateinit var binding: FragmentDetailMovieBinding
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        viewModel.loadMovie(args.movieId)
        viewModel.loadActors(args.movieId)
        viewModel.loadSimilarMovies(args.movieId)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(layoutInflater, container, false)
        binding.cover.transitionName = args.transitionName

        with(binding) {
            actorsGrid.actorsRecycler.apply {
                layoutManager = GridLayoutManager(context, 4, RecyclerView.HORIZONTAL, false)
                adapter = StaffAdapter({ id -> Unit })
            }
            staffGrid.actorsRecycler.apply {
                layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL,false)
                adapter = StaffAdapter({id -> Unit})
            }
            similarMovieSection.sectionRecycler.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = HorizontalMovieListAdapter(
                    onMovieClick = {},
                    onShowAllClick = {}
                )
            }
        }
        viewModel.uiState.launchAndCollectIn(lifecycleScope) { state ->
            when (state) {
                is DetailsUiState.Loading -> showLoading()
                is DetailsUiState.Success -> setupMovie(state.movie)
                is DetailsUiState.Error -> showError(state.exception)
            }
        }
        viewModel.staffList.launchAndCollectIn(lifecycleScope) { actorsList ->

            val actors = actorsList.filter {staff -> staff.professionKey == StaffType.ACTOR}
            (binding.actorsGrid.actorsRecycler.adapter as StaffAdapter).submitList(actors)
            binding.actorsGrid.numOfActors.text = actors.size.toString()

            val staff = actorsList.filter { staff -> staff.professionKey != StaffType.ACTOR }
            (binding.staffGrid.actorsRecycler.adapter as StaffAdapter).submitList(staff)
            binding.staffGrid.numOfActors.text = staff.size.toString()
            Log.d("TAGRTRT", "list submited $actors")
        }
        viewModel.similarMovies.launchAndCollectIn(lifecycleScope) { movies ->
            if (movies.isNotEmpty()) {
                (binding.similarMovieSection.sectionRecycler.adapter as HorizontalMovieListAdapter)
                    .submitList(movies)
                binding.similarMovieSection.btnSeeAll.text = movies.size.toString()
            }
        }
        binding.cover.transitionName = args.transitionName
        return binding.root
    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun showError(e: Throwable) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(context, "Message: ${e.message}", Toast.LENGTH_SHORT).show()
        Log.d("TAGG", "message: ${e.message}")
    }

    fun setupMovie(movie: Movie) {
        binding.progressBar.visibility = View.GONE
        with(binding.coverInfo) {
            rating.text = movie.rating
            filmName.text = movie.name
            releaseDate.text = movie.year.toString()
            genre.text = movie.genres?.first()?.genre
            country.text = movie.countries?.first()?.country
            filmLength.text = movie.filmLength.toString()
            movie.filmLength
                .let {
                    if (it == 0) filmLength.visibility = View.GONE
                    else filmLength.text = formatMinutesToHoursAndMinutes(it)
                }
            movie.ratingAgeLimits
                .let {
                    if (it == "0") censor.visibility = View.GONE
                    else censor.text = formatAgeLimit(it?: "")
                }
        }
        with(binding) {
            description.text = movie.description
            shortDescription.text = movie.shortDescription

            Glide.with(cover)
                .load(movie.coverUrl)
                .dontAnimate()
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: com.bumptech.glide.request.target.Target<Drawable?>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(cover)
            Glide.with(coverInfo.logo)
                .load(movie.logoUrl)
                .into(coverInfo.logo)
        }
    }
}