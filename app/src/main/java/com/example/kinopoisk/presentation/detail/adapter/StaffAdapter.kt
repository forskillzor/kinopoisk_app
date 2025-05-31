package com.example.kinopoisk.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinopoisk.databinding.StuffItemBinding
import com.example.kinopoisk.domain.entities.Staff

class StaffAdapter (
    private val onItemClick: (id: Int)-> Unit
): ListAdapter<Staff, StaffAdapter.ActorViewHolder>(DiffCallback()){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActorViewHolder {
        val viewBinding = StuffItemBinding.inflate(LayoutInflater.from(parent.context), parent,  false)
        return ActorViewHolder(viewBinding, onItemClick)
    }

    override fun onBindViewHolder(
        holder: ActorViewHolder,
        position: Int
    ) {
        val staff = getItem(position)
        holder.bind(staff)
    }

    inner class ActorViewHolder(
        private val binding: StuffItemBinding,
        private val onClick: (id: Int) -> Unit
    ):  RecyclerView.ViewHolder(binding.root) {
        fun bind(staff: Staff)  {
            with(binding) {
                actorName.text = staff.name
                roleName.text = staff.description
                root.setOnClickListener { onClick(staff.id) }
            }
            Glide.with(binding.actorPhoto)
                .load(staff.posterUrl)
                .into(binding.actorPhoto)

        }
    }
    class DiffCallback : DiffUtil.ItemCallback<Staff>() {
        override fun areItemsTheSame(oldItem: Staff, newItem: Staff): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Staff, newItem: Staff): Boolean =
            oldItem == newItem
    }
}