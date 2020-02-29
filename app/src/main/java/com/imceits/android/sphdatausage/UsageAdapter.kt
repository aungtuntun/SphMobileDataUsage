package com.imceits.android.sphdatausage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imceits.android.sphdatausage.data.QuarterData
import com.imceits.android.sphdatausage.data.UsageData
import com.imceits.android.sphdatausage.databinding.DialogContentBinding

class UsageAdapter: RecyclerView.Adapter<UsageAdapter.ListViewHolder>() {
    private var dataList: MutableList<QuarterData> = mutableListOf()

    fun setData(data: List<QuarterData>?) {
        dataList = data as MutableList<QuarterData>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = DialogContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    class ListViewHolder(private val itemBinding: DialogContentBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(data: QuarterData) {
            itemBinding.data = data
            itemBinding.executePendingBindings()
        }
    }
}