package com.example.a7minuteworkoutapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkoutapplication.databinding.ItemExerrciseStatusBinding

class exercisestatusAdapter(val items:ArrayList<exercisemodel>):
    RecyclerView.Adapter<exercisestatusAdapter.ViewHolder>() {
        class ViewHolder(binding:ItemExerrciseStatusBinding):
            RecyclerView.ViewHolder(binding.root){
                val tvitem=binding.tvitem
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerrciseStatusBinding.inflate
            (LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:exercisemodel=items[position]
        holder.tvitem.text=model.getid().toString()
        when
        {
            model.getisSelected()->{
                holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.completed_exercise_color)
            holder.tvitem.setTextColor(Color.parseColor("#212121"))
            }
            model.getisCompleted()->{
                holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.selected_exercise_color)
                holder.tvitem.setTextColor(Color.parseColor("#212121"))

            }
            else->{
                holder.tvitem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.iitem_circular_color_gray_background)
                holder.tvitem.setTextColor(Color.parseColor("#212121"))

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size

    }
}