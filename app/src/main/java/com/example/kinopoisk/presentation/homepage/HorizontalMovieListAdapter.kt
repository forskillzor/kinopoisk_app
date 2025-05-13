package com.example.kinopoisk.presentation.homepage

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.data.model.Movie
import com.example.kinopoisk.databinding.ItemMovieBinding

class HorizontalMovieListAdapter : RecyclerView.Adapter<HorizontalMovieListAdapter.MovieViewHolder>() {
    private var movies = listOf<Movie>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Movie>) {
        movies = list
        notifyDataSetChanged()
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
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.filmName.text = movie.nameRu
            Glide.with(binding.poster)
                .load(movie.posterUrlPreview)
                .into(binding.poster)
            Log.d("HorizontalMovieListAdapter", "$movie")
            if (movie.rating == null) {
                binding.ratingBadge.visibility = View.GONE
            } else {
                binding.ratingBadge.text = movie.rating
            }
            binding.genre.text = movie.genres[0].genre
        }
    }
}