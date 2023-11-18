package com.example.if570_lab_uts_agustinus_00000053639.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.if570_lab_uts_agustinus_00000053639.fragments.ListFragment

class HomePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val fragments = ArrayList<Fragment>()

    init {
        fragments.add(ListFragment())
    }

    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }
}


