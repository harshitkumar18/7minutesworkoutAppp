package com.example.a7minutesworkoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkoutapp.databinding.ItemHistroyRowBinding

class HistoryAdapter(val items: ArrayList<String>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    class ViewHolder(binding:ItemHistroyRowBinding):RecyclerView.ViewHolder(binding.root){
        val llHistoryitemmain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistroyRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date : String = items.get(position)
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date
        if(position%2==0){
            holder.llHistoryitemmain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.lightgrey))
        }else{
            holder.llHistoryitemmain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
        }
    }
}