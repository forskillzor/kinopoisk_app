package com.example.kinopoisk.presentation.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.R
import com.example.kinopoisk.data.model.MovieSection
import kotlinx.coroutines.flow.take

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.SectionViewHolder>() {

    private var sections = listOf<MovieSection>()

    fun submitList(list: List<MovieSection>) {
        sections = list
        notifyDataSetChanged()
    }

    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.section_title)
        private val recyclerView = itemView.findViewById<RecyclerView>(R.id.section_recycler)
        private val btnSeeAll = itemView.findViewById<TextView>(R.id.btn_see_all)

        fun bind(section: MovieSection) {
            title.text = section.title
            val adapter = HorizontalMovieListAdapter().apply {
                submitList(section.movies.take(20))
            }
            recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter

            btnSeeAll.setOnClickListener {
                // Перейти на экран со всеми фильмами этой категории
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_section, parent, false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(sections[position])
    }

    override fun getItemCount(): Int = sections.size
}