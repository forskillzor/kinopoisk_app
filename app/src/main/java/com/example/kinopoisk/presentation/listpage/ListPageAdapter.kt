package com.example.kinopoisk.presentation.listpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.data.model.Movie
import com.example.kinopoisk.databinding.ItemListpageBinding

class ListPageAdapter(): PagingDataAdapter<Movie, ListPageAdapter.MovieViewHolder>(COMPARATOR) {

    companion object{
        val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.filmId == newItem.filmId
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
        val binding = ItemListpageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
    class MovieViewHolder(val binding: ItemListpageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.filmName.text = movie.nameRu
            binding.rating.text = movie.rating

            Glide.with(binding.poster)
                .load(movie.posterUrlPreview)
                .into(binding.poster)
        }
    }
}