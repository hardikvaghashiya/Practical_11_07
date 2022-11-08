package com.practical_11_07.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.epr.adapter.FilterAdapter
import com.app.epr.adapter.FilterCountAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.practical_11_07.R
import com.practical_11_07.databinding.BottomSheetDialogFilterBinding
import com.practical_11_07.listeners.RecyclerRowClick
import com.practical_11_07.model.PharmaResponse
import com.practical_11_07.model.SelectFilterModel
import com.practical_11_07.utils.Utility

class FilterBottomSheet : BottomSheetDialogFragment(), RecyclerRowClick,
    SelectFilterBottomSheet.CardDialogListener {

    lateinit var mBinder: BottomSheetDialogFilterBinding
    lateinit var responseModel: PharmaResponse
    lateinit var adapter: FilterAdapter
    lateinit var adapterCount: FilterCountAdapter

    var arrFilter: ArrayList<SelectFilterModel> = ArrayList()
    var arrCount: ArrayList<String> = ArrayList()

    var arrAccount: ArrayList<SelectFilterModel> = ArrayList()
    var arrBrandName: ArrayList<SelectFilterModel> = ArrayList()
    var arrLocation: ArrayList<SelectFilterModel> = ArrayList()

    lateinit var listener: SelectFilterBottomSheet.CardDialogListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinder = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bottom_sheet_dialog_filter,
            null,
            false
        )
        initView()
        return mBinder.root
    }

    private fun initView() {
        mBinder.imgClose.setOnClickListener(clickListener)

        mBinder.txtAccountNumber.setOnClickListener(clickListener)
        mBinder.txtBrand.setOnClickListener(clickListener)
        mBinder.txtLocation.setOnClickListener(clickListener)
        mBinder.txtClear.setOnClickListener(clickListener)

        responseModel = Utility.getResponseFromAssets(requireContext())

        val hierarchyModel = responseModel.filterData.first().hierarchy
        for (hierarchy in hierarchyModel) {
            arrAccount.add(SelectFilterModel(hierarchy.accountNumber, false))

            var brandList = hierarchy.brandNameList
            for (brand in brandList) {
                arrBrandName.add(SelectFilterModel(brand.brandName, false))

                var locationList = brand.locationNameList
                for (location in locationList) {
                    arrLocation.add(SelectFilterModel(location.locationName, false))
                }
            }
        }

        var selectedBrand = arrBrandName.filter { s -> s.isSelected }
        arrFilter.addAll(selectedBrand)

        adapter = FilterAdapter(requireContext(), arrFilter, this)
        mBinder.adapter = adapter

        arrCount.add("Acc No : 0")
        arrCount.add("Brand : 0")
        arrCount.add("Location : 0")

        adapterCount = FilterCountAdapter(requireContext(), arrCount, this)
        mBinder.adapterCount = adapterCount
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.imgClose -> {
                dismiss()
            }
            R.id.txtClear ->{
                mBinder.txtAccountNumberCount.text = "0"
                arrCount[0] = "Acc No : 0"
                mBinder.txtBrandCount.text = "0"
                arrCount[1] = "Brand : 0"
                mBinder.txtLocationCount.text = "0"
                arrCount[2] = "Location : : 0"
                adapterCount.notifyDataSetChanged()
            }
            R.id.txtAccountNumber -> {
                var dialog = SelectFilterBottomSheet(listener, arrAccount, "Select Account", 1)
                dialog.show(childFragmentManager, "tag")
            }
            R.id.txtBrand -> {
                var dialog = SelectFilterBottomSheet(listener, arrBrandName, "Select Brand", 2)
                dialog.show(childFragmentManager, "tag")
            }
            R.id.txtLocation -> {
                var dialog = SelectFilterBottomSheet(listener, arrLocation, "Select Location", 3)
                dialog.show(childFragmentManager, "tag")
            }
        }
    }

    override fun rowClick(pos: Int, flag: Int) {

    }

    override fun chooseCardClick(type: Int, arrResult1: ArrayList<SelectFilterModel>) {
        when (type) {
            1 -> {
                arrAccount.clear()
                arrAccount.addAll(arrResult1)
                val predicate: (SelectFilterModel) -> Boolean = { it.isSelected }
                mBinder.txtAccountNumberCount.text = (arrAccount.count(predicate) ?: 0).toString()
                arrCount[0] = "Acc No : ${(arrAccount.count(predicate) ?: 0)}"
            }
            2 -> {
                arrBrandName.clear()
                arrBrandName.addAll(arrResult1)
                val predicate: (SelectFilterModel) -> Boolean = { it.isSelected }
                mBinder.txtBrandCount.text = (arrBrandName.count(predicate) ?: 0).toString()
                arrCount[1] = "Brand : ${(arrBrandName.count(predicate) ?: 0)}"

                arrFilter.clear()
                var selectedBrand = arrBrandName.filter { s -> s.isSelected }
                arrFilter.addAll(selectedBrand)
                adapter.notifyDataSetChanged()
            }
            3 -> {
                arrLocation.clear()
                arrLocation.addAll(arrResult1)
                val predicate: (SelectFilterModel) -> Boolean = { it.isSelected }
                mBinder.txtLocationCount.text = (arrLocation.count(predicate) ?: 0).toString()
                arrCount[2] = "Location : ${(arrLocation.count(predicate) ?: 0)}"
            }
        }
        adapterCount.notifyDataSetChanged()
    }
}