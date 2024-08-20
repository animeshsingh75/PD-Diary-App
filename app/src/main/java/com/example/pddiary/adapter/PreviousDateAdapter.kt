package com.example.pddiary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pddiary.databinding.ItemPreviousDateBinding

class PreviousDateAdapter(
    private val data: List<String>,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<PreviousDateAdapter.PreviousDateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousDateViewHolder {
        val binding =
            ItemPreviousDateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PreviousDateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreviousDateViewHolder, position: Int) {
        holder.bind(data[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener {
        fun onItemClicked(date: String)
    }

    class PreviousDateViewHolder(
        private val binding: ItemPreviousDateBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(date: String, clickListener: OnItemClickListener) {

            binding.previousDateTv.text = date
            itemView.setOnClickListener {
                clickListener.onItemClicked(date)
            }
        }
    }
}