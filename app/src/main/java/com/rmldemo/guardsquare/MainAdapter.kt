package com.rmldemo.guardsquare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rmldemo.guardsquare.databinding.AdapterMainBinding

class MainAdapter(val results: ArrayList<MainModel.Result>, val listener: OnAdapterListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.binding.textView.text = result.title

        Glide.with( holder.itemView )
            .load(result.image)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .centerCrop()
            .into(holder.itemView.findViewById(R.id.imageView))

        holder.itemView.setOnClickListener {
            listener.onClick(result)
        }
    }

    class ViewHolder (val binding: AdapterMainBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(data: List<MainModel.Result>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: MainModel.Result)
    }
}