package com.example.travada.mainpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.travada.R
import com.example.travada.fragmentnav.beranda.BerandaFragment
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPageActivity : AppCompatActivity(), MainPageActivityPresenter.Listener {
    private lateinit var presenter: MainPageActivityPresenter
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        presenter = MainPageActivityPresenter(this)

        // set default fragment for the first time opening app
        showFragment(BerandaFragment())

        bn_main.setOnNavigationItemSelectedListener { item ->
            presenter.setFragment(item)
        }
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_homepage_fragment_container, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 1000)
    }
}