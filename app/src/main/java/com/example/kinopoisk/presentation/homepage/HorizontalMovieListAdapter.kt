package com.example.kinopoisk.presentation.homepage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.databinding.ItemMovieBinding
import com.example.kinopoisk.databinding.ItemSectionShowAllBinding
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.presentation.homepage.HorizontalMovieListAdapter.HorizontalListItem.MovieItem
import com.example.kinopoisk.presentation.homepage.HorizontalMovieListAdapter.HorizontalListItem.ShowAll
class HorizontalMovieListAdapter(
    private val onShowAllClick: () -> Unit,
    private val onMovieClick:  (id: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    sealed class HorizontalListItem {
        data class MovieItem(val movie: Movie): HorizontalListItem()
        object ShowAll: HorizontalListItem()
    }

    private var items = listOf<HorizontalListItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(movies: List<Movie>) {
        val newItems: MutableList<HorizontalListItem> = movies.map { MovieItem(it) }.toMutableList()
        newItems.add(ShowAll)
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is MovieItem -> 0
            is ShowAll -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            0 -> {
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                MovieViewHolder(binding, onMovieClick)
            }
            1 -> {
                val binding = ItemSectionShowAllBinding.inflate(layoutInflater, parent, false)
                ShowAllViewHolder(binding, onShowAllClick)
            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is MovieItem -> (holder as MovieViewHolder).bind(item.movie)
            is ShowAll -> (holder as ShowAllViewHolder).bind()
        }
    }

    override fun getItemCount(): Int = items.size

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val onClick: (id: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.root.setOnClickListener {
                onClick(movie.id)
            }
            binding.filmName.text = movie.name
            Glide.with(binding.poster)
                .load(movie.posterUrlPreview)
                .into(binding.poster)
            if (movie.rating == null) {
                binding.ratingBadge.visibility = View.GONE
            } else {
                binding.ratingBadge.visibility = View.VISIBLE
                binding.ratingBadge.text = movie.rating
            }
            binding.genre.text = movie.genres.getOrNull(0)?.genre.orEmpty()
        }
    }

    inner class ShowAllViewHolder(
        private val binding: ItemSectionShowAllBinding,
        private val onClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.btnShowAll.setOnClickListener {
                onClick() }
        }
    }
}