package com.imceits.android.sphdatausage.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.imceits.android.sphdatausage.R
import com.imceits.android.sphdatausage.data.UsageData
import com.imceits.android.sphdatausage.databinding.DataItemBinding
import com.imceits.android.sphdatausage.databinding.DialogLayoutBinding

class DataAdapter(private val mContext: Context): RecyclerView.Adapter<DataAdapter.DataViewHolder> (){
private var dataList: MutableList<UsageData> = mutableListOf()

    fun setData(data: List<UsageData>?) {
        data?.let {
            dataList = it as MutableList<UsageData>
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding = DataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(
            itemBinding
        )
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.onBind(dataList[position])
        holder.itemBinding.imgChart.setOnClickListener {
            if (dataList[position].data.image) {
                createDialog(dataList[position])
            }
        }
    }

    private fun createDialog(data: UsageData) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
        val inflater: LayoutInflater = LayoutInflater.from(mContext)
        val adder = UsageAdapter()
        val dialogLayoutBinding = DialogLayoutBinding.inflate(inflater, null, false)
        dialogLayoutBinding.listView.setHasFixedSize(true)
        dialogLayoutBinding.listView.isNestedScrollingEnabled = false
        dialogLayoutBinding.listView.itemAnimator = DefaultItemAnimator()
        dialogLayoutBinding.listView.adapter = adder
        adder.setData(data.dataList)
        builder.setView(dialogLayoutBinding.root)
        builder.setTitle("Volume of Mobile Data (Petabytes)")
        builder.setMessage(mContext.getString(R.string.label_year, data.data.quarter))
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    class DataViewHolder(val itemBinding: DataItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(data: UsageData) {
            itemBinding.data = data
            itemBinding.executePendingBindings()
        }

    }

}