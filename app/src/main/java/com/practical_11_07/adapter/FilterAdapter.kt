package com.app.epr.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.practical_11_07.R
import com.practical_11_07.databinding.RowFilterBinding
import com.practical_11_07.databinding.RowFilterCountBinding
import com.practical_11_07.listeners.RecyclerRowClick
import com.practical_11_07.model.SelectFilterModel

class FilterAdapter(
    private val mContext: Context,
    private val arrData: ArrayList<SelectFilterModel>,
    private val recyclerRowClick: RecyclerRowClick
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinder: RowFilterBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(mContext),
            R.layout.row_filter,
            parent,
            false
        ) as RowFilterBinding
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

    inner class ViewHolder(var mBinder: RowFilterBinding) :
        RecyclerView.ViewHolder(mBinder.root) {
        fun bind(model: SelectFilterModel) {
            mBinder.model = model
        }
    }
}