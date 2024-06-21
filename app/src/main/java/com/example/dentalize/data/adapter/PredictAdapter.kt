package com.example.dentalize.data.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dentalize.data.response.ListHistory
import com.example.dentalize.databinding.HistoryCardViewBinding

class PredictAdapter: PagingDataAdapter<ListHistory, PredictAdapter.ListViewHolder>(DIFF_CALLBACK) {


    override fun onBindViewHolder(holder: PredictAdapter.ListViewHolder, position: Int) {
        val review = getItem(position)
        if (review != null){
            holder.bind(review)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,): PredictAdapter.ListViewHolder {
        val binding = HistoryCardViewBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return ListViewHolder(binding)
    }

    class ListViewHolder(private val binding: HistoryCardViewBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ListHistory) {
            binding.nameItemHistory.text = "${user.result}"
            binding.descriptionItemHistory.text = "${user.explanation}"
            Glide.with(binding.imageItemHistory.context).load(user.imageUrl)
                .into(binding.imageItemHistory)

            binding.root.setOnClickListener {
//                val intent = Intent(binding.root.context, DetailActivity::class.java)
//                intent.putExtra("ID", user.predictionId)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        binding.root.context as Activity,

                    )
//                binding.root.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListHistory>() {
            override fun areItemsTheSame(oldItem: ListHistory, newItem: ListHistory): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ListHistory, newItem: ListHistory): Boolean {
                return oldItem == newItem
            }
        }
    }
}