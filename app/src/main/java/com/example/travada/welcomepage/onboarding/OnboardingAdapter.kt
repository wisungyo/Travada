package com.example.travada.welcomepage.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travada.R
import kotlinx.android.synthetic.main.activity_onboarding.view.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.android.synthetic.main.viewpager_onboarding.view.*

class OnboardingAdapter(val list: List<SlideOnboarding>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewpager_onboarding, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_title.text = list[position].title
        holder.itemView.tv_desc.text = list[position].desc
        Glide
            .with(holder.itemView.context)
            .load(list[position].image)
            .into(holder.itemView.iv_onBoarding)
    }
}