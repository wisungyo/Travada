package com.example.travada.welcomepage.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    val onBoarding = arrayListOf(
        SlideOnboarding(
            "TRANSAKSI MUDAH",
            "Segala transaksi perbankan jadi lebih \n" +
                    "mudah tanpa ribet",
            R.drawable.image_onboarding1
        ),
        SlideOnboarding(
            "TABUNGAN LIBURAN",
            "Wujudkan dan percepat liburanmu \n" +
                    "dengan menabung. Liburan bareng \n" +
                    " teman kini lebih mudah dengan \n" +
                    " menabung bersama",
            R.drawable.image_onboarding2
        ),
        SlideOnboarding(
            "RENCANA LIBURAN",
            "Mudah rencanakan liburanmu.\n" +
                    "Pilih trip impianmu, dan cicil sekarang",
            R.drawable.image_onboarding3
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        vp_onboarding.adapter = OnboardingAdapter(onBoarding)

        vp_onboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                if (position == 2) {
                    btn_next.text = "Mulai"
                } else {
                    btn_next.text = "Lanjut"
                }
            }
        })

        btn_next.setOnClickListener {
            if (vp_onboarding.currentItem + 1 < OnboardingAdapter(onBoarding).itemCount) {
                vp_onboarding.currentItem +=1
            }else {
                val intent = Intent(this, OnboardingEndActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    fun setCurrentIndicator(index: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.ic_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.ic_indicator_inactive
                    )
                )
            }
        }
    }
}