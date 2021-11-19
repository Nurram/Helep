package com.rex.project.helep.view.activities.taskDone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityTaskDoneBinding
import com.rex.project.helep.model.Helper
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.home.HomeActivity

class TaskDoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskDoneBinding
    private lateinit var taskDoneViewModel: TaskDoneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTaskDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helperId = intent.getLongExtra(Constants.HELPER_ID,-1)
        val price = intent.getLongExtra(Constants.DATA, -1)
        val helper = Helper.getHelperById(helperId)

        val factory = ViewModelFactory(application)
        taskDoneViewModel = ViewModelProvider(this, factory)[taskDoneViewModel::class.java]

        binding.apply {
            civAvatar.setImageResource(helper.avatar)
            tvName.text = helper.name

            ivStar.setOnClickListener {
                setStar(
                star = true,
                star1 = false,
                star2 = false,
                star3 = false,
                star4 = false
                )
            }

            ivStar1.setOnClickListener {
                setStar(
                    star = true,
                    star1 = true,
                    star2 = false,
                    star3 = false,
                    star4 = false
                )
            }
            ivStar2.setOnClickListener {
                setStar(
                    star = true,
                    star1 = true,
                    star2 = true,
                    star3 = false,
                    star4 = false
                )
            }
            ivStar3.setOnClickListener {
                setStar(
                    star = true,
                    star1 = true,
                    star2 = true,
                    star3 = true,
                    star4 = false
                )
            }
            ivStar4.setOnClickListener {
                setStar(
                    star = true,
                    star1 = true,
                    star2 = true,
                    star3 = true,
                    star4 = true
                )
            }

            btnBack.setOnClickListener {
                val i = Intent(this@TaskDoneActivity, HomeActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)
            }

            ivBack.setOnClickListener {
                taskDoneViewModel.spendWallet(price)
                moveToHome()
            }
        }
    }

    override fun onBackPressed() {
        moveToHome()
    }

    private fun setStar(star: Boolean, star1: Boolean, star2: Boolean, star3: Boolean, star4: Boolean) {
        binding.apply {
            if (star) ivStar.setImageResource(R.drawable.ic_baseline_star_24)
            else ivStar.setImageResource(R.drawable.ic_baseline_star_outline_24)

            if (star1) ivStar1.setImageResource(R.drawable.ic_baseline_star_24)
            else ivStar1.setImageResource(R.drawable.ic_baseline_star_outline_24)

            if (star2) ivStar2.setImageResource(R.drawable.ic_baseline_star_24)
            else ivStar2.setImageResource(R.drawable.ic_baseline_star_outline_24)

            if (star3) ivStar3.setImageResource(R.drawable.ic_baseline_star_24)
            else ivStar3.setImageResource(R.drawable.ic_baseline_star_outline_24)

            if (star4) ivStar4.setImageResource(R.drawable.ic_baseline_star_24)
            else ivStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
        }
    }

    private fun moveToHome() {
        val i = Intent(this, HomeActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }
}