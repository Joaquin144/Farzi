package com.vibhu.nitjsr.farzi.codeforces

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vibhu.nitjsr.farzi.databinding.ItemContestBinding

class ContestAdapter : RecyclerView.Adapter<ContestAdapter.ContestViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<ItemContest>(){
        override fun areContentsTheSame(oldItem: ItemContest, newItem: ItemContest): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ItemContest, newItem: ItemContest): Boolean {
            return oldItem.id == newItem.id
        }
    }
    private val differ = AsyncListDiffer(this,diffCallBack)
    var contests :List<ItemContest>
    get() =differ.currentList
    set(value) { differ.submitList(value) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        return ContestViewHolder(ItemContestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
false
        ))
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        holder.binding.apply {
            val currContest = contests[position]
            tvContestDescription.text = currContest.name
            tvContestId.text = (currContest.id).toString()
            tvContestDuration.text = currContest.durationSeconds.toString()
            tvContestTime.text = currContest.relativeTimeSeconds.toString()
        }
    }

    override fun getItemCount(): Int = contests.size

    inner class ContestViewHolder(val binding: ItemContestBinding) :RecyclerView.ViewHolder(binding.root){

    }
}