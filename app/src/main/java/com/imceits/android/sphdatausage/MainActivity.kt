package com.imceits.android.sphdatausage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.imceits.android.sphdatausage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
       // setContentView(R.layout.activity_main)
        dataBinding.lifecycleOwner = this
        dataBinding.recyclerView.setHasFixedSize(true)
        dataBinding.recyclerView.isNestedScrollingEnabled = false
        dataBinding.recyclerView.itemAnimator = DefaultItemAnimator()
        adapter = DataAdapter(this)
        dataBinding.recyclerView.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        loadData()
        dataBinding.btnRefresh.setOnClickListener{
            viewModel.loadData()
            loadData()
        }
    }

    private fun loadData() {
        viewModel.dataList.observe(this, Observer {
            dataBinding.resource = it
            it?.let {
                adapter.setData(it.data)
            }
            //   adapter.setData(it?.data)
        })
    }
}
