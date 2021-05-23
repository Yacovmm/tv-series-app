package com.zygotecnologia.zygotv.ui.fragments.viewPager

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.zygotecnologia.zygotv.databinding.FragmentViewPagerBinding
import com.zygotecnologia.zygotv.ui.adapters.FragmentTabPageAdapter
import com.zygotecnologia.zygotv.ui.base.BaseFragment
import com.zygotecnologia.zygotv.ui.fragments.series.SeriesFragment

class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentViewPagerBinding
        get() = FragmentViewPagerBinding::inflate

    override fun setupFragment() {
        setupViewPager()
    }

    private lateinit var tabAdapter: FragmentTabPageAdapter

    private fun setupViewPager() {
        tabAdapter = FragmentTabPageAdapter(childFragmentManager, lifecycle)
        binding.viewpager.adapter = tabAdapter
        binding.viewpager.isUserInputEnabled = false
        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = tabAdapter.getPageTitle(position)
            binding.viewpager.setCurrentItem(tab.position, false)
        }.attach()

        tabAdapter.updateData(
            arrayListOf(
                FragmentTabPageAdapter.PageModel(
                    "transmissoes", "SERIES",
                    SeriesFragment()
                ),
                FragmentTabPageAdapter.PageModel(
                    "filme", "Filmes",
                    SeriesFragment()
                ),
                FragmentTabPageAdapter.PageModel(
                    "favoritos", "FAVORITOS",
                    SeriesFragment()
                )
            )
        )
    }
}
