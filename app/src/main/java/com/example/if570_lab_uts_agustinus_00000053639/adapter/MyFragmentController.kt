package com.example.if570_lab_uts_agustinus_00000053639.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class MyFragmentController(private val activity: FragmentActivity, private val containerId: Int) {

    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(containerId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun popBackStack() {
        fragmentManager.popBackStack()
    }
}