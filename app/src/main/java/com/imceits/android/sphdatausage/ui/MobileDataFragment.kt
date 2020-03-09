package com.imceits.android.sphdatausage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.imceits.android.sphdatausage.databinding.FragmentMobileDataBinding
import com.imceits.android.sphdatausage.di.AppViewModelFactory
import com.imceits.android.sphdatausage.di.Injectable
import javax.inject.Inject

class MobileDataFragment : Fragment(), Injectable {

    private lateinit var viewModel: MobileDataViewModel
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var adapter: DataAdapter
    private lateinit var binding: FragmentMobileDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentMobileDataBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        adapter = DataAdapter(requireContext())
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MobileDataViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.setParam(1)
        viewModel.mobileDataList.observe(viewLifecycleOwner, Observer {
            binding.resource = it
            it?.let {
                adapter.setData(it.data)
            }
        })
    }

}
