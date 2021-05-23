package com.zygotecnologia.zygotv.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentTabPageAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    class PageModel(var id: String, var title: String, var fragment: Fragment)

    var pages: ArrayList<PageModel>? = null

    fun updateData(pages: ArrayList<PageModel>) {
        this.pages = pages
        notifyDataSetChanged()
    }

    override fun createFragment(position: Int): Fragment {
        return pages!![position].fragment
    }

    override fun getItemCount(): Int {
        return pages?.size ?: 0
    }

    override fun getItemId(position: Int): Long {
        return pages?.get(position)?.id?.hashCode()?.toLong() ?: position.toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        pages?.let { pages ->
            return pages.any {
                it.id.hashCode().toLong() == itemId
            }
        }
        return super.containsItem(itemId)
    }

    fun getPageTitle(position: Int): CharSequence? {
        return pages?.get(position)?.title
    }
}
