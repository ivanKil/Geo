package com.kusch.geo.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kusch.geo.R
import com.kusch.geo.model.data.MarkerEntity

class MarkerAdapter(private val clickListener: (MarkerEntity) -> Unit) :
    RecyclerView.Adapter<MarkerAdapter.RecyclerItemViewHolder>() {

    private var data: List<MarkerEntity> = arrayListOf()

    fun setData(data: List<MarkerEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.marker_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: MarkerEntity) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.marker_item_name).text = data.name
                itemView.findViewById<TextView>(R.id.marker_item_annotation).text = data.annotation
                itemView.setOnClickListener { clickListener(data) }
            }
        }
    }
}
