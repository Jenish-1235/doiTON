package com.loopsquad.doiton

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayout
import com.loopsquad.doiton.fragments.home_fragment
import com.loopsquad.doiton.fragments.nft_fragment
import com.loopsquad.doiton.fragments.profile_fragments
import com.loopsquad.doiton.fragments.tasks_fragment
import com.loopsquad.doiton.fragments.teams_fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bottomTabLayoutFormation()
    }

    fun bottomTabLayoutFormation(){

        val tabLayout = findViewById<TabLayout>(R.id.tablayout)
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE)
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE)
        tabLayout.setSelectedTabIndicatorHeight(0)

        val mainFragmentLayout = findViewById<FrameLayout>(R.id.fragmentContainer)

        var homeTab = tabLayout.newTab()
        val homeTabView = layoutInflater.inflate(R.layout.main_activity_tablayout_item, null)
        val homeTabText = homeTabView.findViewById<TextView>(R.id.tab_text)
        homeTabText.text = "Home"
        val homeTabIcon = homeTabView.findViewById<ImageView>(R.id.tab_icon)
        homeTabIcon.setImageResource(R.drawable.ic_home_white)
        homeTab.setCustomView(homeTabView)
        tabLayout.addTab(homeTab)

        var nftTab = tabLayout.newTab()
        val nftTabView = layoutInflater.inflate(R.layout.main_activity_tablayout_item, null)
        val nftTabText = nftTabView.findViewById<TextView>(R.id.tab_text)
        nftTabText.text = "NFT"
        val nftTabIcon = nftTabView.findViewById<ImageView>(R.id.tab_icon)
        nftTabIcon.setImageResource(R.drawable.ic_nft_white)
        nftTab.setCustomView(nftTabView)
        tabLayout.addTab(nftTab)

        var tasKTab = tabLayout.newTab()
        val tasKTabView = layoutInflater.inflate(R.layout.main_activity_tablayout_item, null)
        val tasKTabText = tasKTabView.findViewById<TextView>(R.id.tab_text)
        tasKTabText.text = "Task"
        val tasKTabIcon = tasKTabView.findViewById<ImageView>(R.id.tab_icon)
        tasKTabIcon.setImageResource(R.drawable.ic_tasks_white)
        tasKTab.setCustomView(tasKTabView)
        tabLayout.addTab(tasKTab)

        var teamTab = tabLayout.newTab()
        val teamTabView = layoutInflater.inflate(R.layout.main_activity_tablayout_item, null)
        val teamTabText = teamTabView.findViewById<TextView>(R.id.tab_text)
        teamTabText.text = "Team"
        val teamTabIcon = teamTabView.findViewById<ImageView>(R.id.tab_icon)
        teamTabIcon.setImageResource(R.drawable.ic_teams_white)
        teamTab.setCustomView(teamTabView)
        tabLayout.addTab(teamTab)

        var profileTab = tabLayout.newTab()
        val profileTabView = layoutInflater.inflate(R.layout.main_activity_tablayout_item, null)
        val profileTabText = profileTabView.findViewById<TextView>(R.id.tab_text)
        profileTabText.text = "Profile"
        val profileTabIcon = profileTabView.findViewById<ImageView>(R.id.tab_icon)
        profileTabIcon.setImageResource(R.drawable.ic_profile_white)
        profileTab.setCustomView(profileTabView)
        tabLayout.addTab(profileTab)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                when(position){
                    0 -> {
                        val fragmentManager = supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentContainer, home_fragment())
                        fragmentTransaction.commit()
                    }
                    1 -> {
                        val fragmentManager = supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentContainer, nft_fragment())
                        fragmentTransaction.commit()
                    }
                    2 -> {
                        val fragmentManager = supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentContainer, tasks_fragment())
                        fragmentTransaction.commit()
                    }
                    3 -> {
                        val fragmentManager = supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentContainer, teams_fragment())
                        fragmentTransaction.commit()
                    }4 ->{
                        val fragmentManager = supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentContainer, profile_fragments())
                        fragmentTransaction.commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        tabLayout.selectTab(null)
        tabLayout.getTabAt(0)!!.select()

    }
}