package com.example.nestedrecyclerview1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChildRowAdapter(val topics: List<String>, val mContext: Context): RecyclerView.Adapter<ChildRowAdapter.childItemViewHolder>() {

    class childItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var textView: TextView

        fun bind (topic: String){
            textView = itemView.findViewById(R.id.topics_child_view)
            textView.text = topic
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): childItemViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.child_rowview, parent, false)
        return childItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun onBindViewHolder(holder: childItemViewHolder, position: Int) {
//        holder.textView.text = topics[position]
        holder.bind(topics[position])

    }

}