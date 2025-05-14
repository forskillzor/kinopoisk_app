package com.example.kinopoisk.presentation.homepage

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.databinding.ItemMovieBinding

class HorizontalMovieListAdapter : RecyclerView.Adapter<HorizontalMovieListAdapter.MovieViewHolder>() {
    private var movieDtos = listOf<MovieDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<MovieDto>) {
        movieDtos = list
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
        holder.bind(movieDtos[position])
    }

    override fun getItemCount(): Int = movieDtos.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieDto: MovieDto) {
            binding.filmName.text = movieDto.nameRu
            Glide.with(binding.poster)
                .load(movieDto.posterUrlPreview)
                .into(binding.poster)
            Log.d("HorizontalMovieListAdapter", "$movieDto")
            if (movieDto.rating == null) {
                binding.ratingBadge.visibility = View.GONE
            } else {
                binding.ratingBadge.text = movieDto.rating
            }
            binding.genre.text = movieDto.genres[0].genre
        }
    }
}