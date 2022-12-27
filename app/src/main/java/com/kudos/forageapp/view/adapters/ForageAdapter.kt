package com.kudos.forageapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kudos.forageapp.databinding.RowItemForageListBinding
import com.kudos.forageapp.db.entities.Forage

class ForageAdapter(private val onItemClick: (Forage) -> Unit) :
    ListAdapter<Forage, ForageAdapter.ViewHolder>(callback) {

    companion object {
        val callback = object : DiffUtil.ItemCallback<Forage>() {
            override fun areItemsTheSame(
                oldItem: Forage,
                newItem: Forage
            ) =
                oldItem.forageId == newItem.forageId

            override fun areContentsTheSame(
                oldItem: Forage,
                newItem: Forage
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val rowItemForageListBinding: RowItemForageListBinding) :
        RecyclerView.ViewHolder(rowItemForageListBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemForageListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            rowItemForageListBinding.apply {
                val item = getItem(position)

                forageNameTextView.text = item.forageName
                forageLocationTextView.text = item.forageLocation

                itemView.setOnClickListener {
                    onItemClick(item)
                }

            }
        }
    }

}