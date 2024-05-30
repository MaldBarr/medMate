package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.medstep.MedStep1Fragment
import com.example.myapplication.ui.medstep.MedStep2Fragment
import com.example.myapplication.ui.medstep.MedStep3Fragment

class AddMedPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3 // Número de pasos

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MedStep1Fragment()
            1 -> MedStep2Fragment()
            2 -> MedStep3Fragment()
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}