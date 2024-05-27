package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivityAddMedBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddMed : AppCompatActivity() {

    private lateinit var binding: ActivityAddMedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        // Set up the ViewPager with an adapter
        val adapter = AddMedPagerAdapter(this)
        viewPager.adapter = adapter

        // Attach the ViewPager to the TabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Paso ${position + 1}"
        }.attach()
    }
}
