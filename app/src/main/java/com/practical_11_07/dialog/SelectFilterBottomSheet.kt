package com.practical_11_07.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.epr.adapter.SelectFilterAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.practical_11_07.R
import com.practical_11_07.databinding.BottomSheetSelectFilterBinding
import com.practical_11_07.listeners.RecyclerRowClick
import com.practical_11_07.model.SelectFilterModel

class SelectFilterBottomSheet(
    listener: CardDialogListener,
    val arrAccount: ArrayList<SelectFilterModel>,
    val title: String,
    val type: Int,
) : BottomSheetDialogFragment(), RecyclerRowClick {

    lateinit var mBinder: BottomSheetSelectFilterBinding
    private var mBottomSheetListener: CardDialogListener? = null

    var arrFilter: ArrayList<SelectFilterModel> = ArrayList()

    lateinit var adapter: SelectFilterAdapter

    init {
        this.mBottomSheetListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinder = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bottom_sheet_select_filter,
            null,
            false
        )
        initView()
        return mBinder.root
    }

    private fun initView() {
        mBinder.title = title
        arrFilter.addAll(arrAccount)
        adapter = SelectFilterAdapter(requireContext(), arrFilter, this)
        //mBinder.adapter = adapter

        mBinder.rvFilter.adapter = adapter

        mBinder.txtAddToFilter.setOnClickListener(clickListener)
        mBinder.imgClose.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.imgClose, R.id.txtAddToFilter -> {
                mBottomSheetListener!!.chooseCardClick(type, arrFilter)
                dismiss()
            }
        }
    }

    override fun rowClick(pos: Int, flag: Int) {
        arrFilter[pos].isSelected = !arrFilter[pos].isSelected
        adapter.notifyItemChanged(pos)
    }

    interface CardDialogListener {
        fun chooseCardClick(type: Int, arrAccount: ArrayList<SelectFilterModel>)
    }
}