package com.example.dentalize.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dentalize.data.response.ItemHistoryResponse
import com.example.dentalize.data.response.ItemHistoryResponseItem
import com.example.dentalize.databinding.HistoryCardViewBinding

class HistoryAdapter : ListAdapter<ItemHistoryResponseItem, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HistoryCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val history = getItem(position)
        holder.bind(history)
    }

    class MyViewHolder(val binding: HistoryCardViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(history: ItemHistoryResponseItem) {
            binding.nameItemHistory.text = history.result
            binding.descriptionItemHistory.text = history.explanation
            Glide.with(binding.root)
                .load(history.imageUrl)
                .into(binding.imageItemHistory)
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemHistoryResponseItem>(){
            override fun areItemsTheSame(
                oldItem: ItemHistoryResponseItem,
                newItem: ItemHistoryResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ItemHistoryResponseItem,
                newItem: ItemHistoryResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: ItemHistoryResponseItem)
    }
}