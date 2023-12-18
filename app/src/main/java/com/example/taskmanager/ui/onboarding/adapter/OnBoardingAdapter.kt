package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoarding
import com.example.taskmanager.utils.loadImage

class OnBoardingAdapter(private val onClick:()->Unit): Adapter<OnBoardingAdapter.OnboardingViewHolder>() {
    private val list = arrayListOf(
        OnBoarding("https://www.computerhope.com/jargon/t/task.png", "Keep", "Accept cryptocurrencies and digitals assets, keep them here",),
        OnBoarding("https://cdn.workona.com/assets/images/workona-tasks.png", "Buy & Sell", "Buy and sell Bitcoin abd other cryptocurrencies with VISA and MasterCard ",),
        OnBoarding("https://static.vecteezy.com/system/resources/previews/008/879/446/non_2x/white-clipboard-task-management-todo-check-list-efficient-work-on-project-plan-fast-progress-level-up-concept-assignment-and-exam-productivity-solution-icon-3d-render-on-white-background-free-png.png", "Exchange", "Change your crypto to other digital assets or flat money ",)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context)
            ,parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
       holder.bind(list.get(position))
    }

    inner class OnboardingViewHolder(private val binding: ItemOnboardingBinding) : ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.desc
            ivBoard.loadImage(onBoarding.image)

            skip.setOnClickListener { onClick() }
            btnStart.setOnClickListener { onClick() }
            skip.isInvisible = adapterPosition == list.lastIndex
            btnStart.isVisible = adapterPosition == list.lastIndex
        }

    }
}