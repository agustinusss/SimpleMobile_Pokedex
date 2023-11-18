package com.example.if570_lab_uts_agustinus_00000053639

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.if570_lab_uts_agustinus_00000053639.adapter.HomePagerAdapter
import com.example.if570_lab_uts_agustinus_00000053639.adapter.MyFragmentController
import com.example.if570_lab_uts_agustinus_00000053639.fragments.DetailFragment
import com.example.if570_lab_uts_agustinus_00000053639.fragments.ListFragment
import com.example.if570_lab_uts_agustinus_00000053639.fragments.ProfileFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private val fragmentController = MyFragmentController(this, R.id.fragment_detail_xml)

    private lateinit var userName: String
    private lateinit var viewPager: ViewPager2
    private lateinit var listFragment: ListFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var detailFragmentContainer: FrameLayout // Tambahkan ini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userName = intent.getStringExtra("user_name") ?: "User"
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        detailFragmentContainer = findViewById(R.id.fragment_detail_xml) // Inisialisasi detailFragmentContainer
        setSupportActionBar(toolbar)
        supportActionBar?.title = null

        viewPager = findViewById(R.id.viewPager2)

        val adapter = HomePagerAdapter(this)
        viewPager.adapter = adapter

        // Tabs berfungsi dan menggunakan Fragment
        val tabLayout = findViewById<TabLayout>(R.id.TabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.CE_title)
                1 -> tab.text = getString(R.string.Profile_title)
            }
        }.attach()

        // Nama diambil dari Landing Page (bukan hardcode)
        profileFragment = ProfileFragment()
        val bundle = Bundle()
        bundle.putString("user_name", userName)
        profileFragment.arguments = bundle

        adapter.addFragment(profileFragment)

        val fragment = DetailFragment() // fragment yang ingin di tampilkan
        fragmentController.replaceFragment(fragment)

        // listener  tab
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val selectedTabPosition = tab?.position
                if (selectedTabPosition == 1) { // Tab Profile
                    detailFragmentContainer.visibility = View.GONE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Tidak perlu melakukan apa pun saat tab tidak terpilih
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Tidak perlu melakukan apa pun saat tab terpilih kembali
            }
        })
    }

    // Options Menu berfungsi
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        // Kirim data nama pengguna ke option menu
        // Nama pada Option Menu diambil dari Landing Page (bukan hardcode)
        val userProfileMenuItem = menu.findItem(R.id.action_user_profile)
        userProfileMenuItem.title = "Hi, $userName"
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_user_profile -> {
                return true
            }
            R.id.action_logout -> {
                // Tombol Logout ditekan, pindah ke LandingActivity
                val intent = Intent(this, LandingActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        // Halaman tidak berpindah ke Landing Page ketika tombol Back HP ditekan
    }
}