package com.example.kinopoisk.presentation.listpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.databinding.ItemMovieBinding

class MovieGridAdapter(): PagingDataAdapter<MovieDto, MovieGridAdapter.MovieViewHolder>(COMPARATOR) {

    companion object{
        val COMPARATOR = object : DiffUtil.ItemCallback<MovieDto>() {
            override fun areItemsTheSame(
                oldItem: MovieDto,
                newItem: MovieDto
            ): Boolean {
                return oldItem.filmId == newItem.filmId
            }

            override fun areContentsTheSame(
                oldItem: MovieDto,
                newItem: MovieDto
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
    class MovieViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieDto: MovieDto) {
            binding.filmName.text = movieDto.nameRu
            binding.ratingBadge.text = movieDto.rating
            binding.genre.text = movieDto.genres[0].genre

            Glide.with(binding.poster)
                .load(movieDto.posterUrlPreview)
                .into(binding.poster)
        }
    }
}