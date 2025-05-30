package com.example.kinopoisk.presentation.listpage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.databinding.ItemMovieBinding
import com.example.kinopoisk.domain.entities.Movie

class MovieGridAdapter(
    private val onItemClick: (id: Int)-> Unit
): PagingDataAdapter<Movie, MovieGridAdapter.MovieViewHolder>(COMPARATOR) {

    companion object{
        val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
    inner class MovieViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.root.setOnClickListener {
                onItemClick(movie.id)
            }
            binding.filmName.text = movie.name
            binding.ratingBadge.text = movie.rating
            binding.genre.text = movie.genres[0].genre

            Glide.with(binding.poster)
                .load(movie.posterUrlPreview)
                .into(binding.poster)
        }
    }
}