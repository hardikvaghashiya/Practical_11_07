package com.app.epr.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.practical_11_07.R
import com.practical_11_07.databinding.RowFilterCountBinding
import com.practical_11_07.listeners.RecyclerRowClick

class FilterCountAdapter(
    private val mContext: Context,
    private val arrData: ArrayList<String>,
    private val recyclerRowClick: RecyclerRowClick
) : RecyclerView.Adapter<FilterCountAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinder: RowFilterCountBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(mContext),
            R.layout.row_filter_count,
            parent,
            false
        ) as RowFilterCountBinding
        return ViewHolder(mBinder)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = arrData[position]
        holder.bind(model)

        holder.itemView.setOnClickListener {
            recyclerRowClick.rowClick(position, 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

    inner class ViewHolder(var mBinder: RowFilterCountBinding) :
        RecyclerView.ViewHolder(mBinder.root) {
        fun bind(title: String) {
            mBinder.title = title
        }
    }
}