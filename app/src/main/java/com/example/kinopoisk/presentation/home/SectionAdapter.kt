package com.example.kinopoisk.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.R
import com.example.kinopoisk.domain.entities.MovieSection

class SectionAdapter(
) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    private var sections = listOf<MovieSection>()

    @SuppressLint("NotifyDataSetChanged")
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
            val adapter = HorizontalMovieListAdapter(
                {
                    HomeFragmentDirections.actionHomepageFragmentToListPageFragment(section.type)
                        .let {
                            itemView.findNavController().navigate(it)
                        }
                },
                { movieId ->
                    val transitionName = "poster_$movieId"
                    val position = section.movies.indexOfFirst { it.id == movieId }
                    if (position != -1) {
                        val posterView = recyclerView.layoutManager?.findViewByPosition(position)
                            ?.findViewById<ImageView>(R.id.poster)

                        val extras = if (posterView != null) {
                            FragmentNavigator.Extras.Builder()
                                .addSharedElement(posterView, transitionName)
                                .build()
                        } else {
                            null
                        }

                        HomeFragmentDirections.actionHomepageFragmentToMovieDetailFragment(
                            movieId,
                            transitionName
                        ).let {
                            if (extras != null) {
                                itemView.findNavController().navigate(it, extras)
                            } else {
                                itemView.findNavController().navigate(it)
                            }
                        }
                    }
                }
            ).apply {
                submitList(section.movies.take(20))
            }
            recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter

            btnSeeAll.setOnClickListener {
                val section = sections[position]
                HomeFragmentDirections.actionHomepageFragmentToListPageFragment(section.type)
                    .let {
                        itemView.findNavController().navigate(it)
                    }
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