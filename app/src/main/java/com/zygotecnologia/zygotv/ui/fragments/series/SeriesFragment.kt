package com.zygotecnologia.zygotv.ui.fragments.series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.zygotecnologia.zygotv.databinding.FragmentSeriesBinding
import com.zygotecnologia.zygotv.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : BaseFragment<FragmentSeriesBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeriesBinding
        get() = FragmentSeriesBinding::inflate

    private val viewModel: SeriesViewModel by viewModels()

    override fun setupFragment() {
        viewModel.getData()

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.showsResponse.observe(viewLifecycleOwner) { list ->

            val listPair = list.filter { it.second.isNotEmpty() }



        }
    }
}
