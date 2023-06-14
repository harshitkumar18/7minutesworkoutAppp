package com.example.a7minutesworkoutapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkoutapp.databinding.ItemErerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>):RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
       class ViewHolder(binding: ItemErerciseStatusBinding): RecyclerView.ViewHolder(binding.root){
              val tvItem124 = binding.tvitem

       }

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemErerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
       }

       override fun getItemCount(): Int {
            return items.size
       }

       override fun onBindViewHolder(holder: ViewHolder, position: Int) {
             val model : ExerciseModel = items[position]
              holder.tvItem124.text = model.getid().toString()
           when{
               model.getiselected() ->{
                   holder.tvItem124.background = ContextCompat.getDrawable(holder.itemView.context,
                   R.drawable.item_circular_thin_colured_accent_border)

               }
               model.getiscompleted() ->{
                   holder.tvItem124.background = ContextCompat.getDrawable(holder.itemView.context,
                       R.drawable.item_circular_accent_background)
                   holder.tvItem124.setTextColor(Color.parseColor("#FFFFFF"))
               }
               else ->{
                   holder.tvItem124.background = ContextCompat.getDrawable(holder.itemView.context,
                       R.drawable.item_circular_colour_gray_background)
                   holder.tvItem124.setTextColor(Color.parseColor("#212121"))
               }

               }
       }


}