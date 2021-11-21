package com.rex.project.helep.view.activities.detailTask

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityDetailTaskBinding
import com.rex.project.helep.model.HelperTask
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.utils.CurrencyFormat
import com.rex.project.helep.view.activities.viewProgressFind.ViewProgressFindActivity

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTaskBinding
    private var helperTask: HelperTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTaskBinding.inflate(layoutInflater)
        helperTask = intent.getParcelableExtra(Constants.DATA)
        binding.apply {
            setContentView(root)

            if (helperTask != null) {
                ivBack.setOnClickListener { finish() }
                civAvatar.setImageResource(helperTask!!.avatar)

                var taskTotal = helperTask!!.taskOneCount
                taskTotal += helperTask!!.taskTwoCount

                val price = helperTask!!.price
                val priceTotal = price * taskTotal

                tvName.text = helperTask!!.username
                tvDesc.text = helperTask!!.taskDesc
                tvTaskOne.text = helperTask!!.taskOne
                tvTaskOneCount.text = getString(R.string.task_count, helperTask!!.taskOneCount)
                tvTaskTwo.text = helperTask!!.taskTwo
                tvTaskTwoCount.text = getString(R.string.task_count, helperTask!!.taskTwoCount)
                tvPricePerTask.text = helperTask!!.price.let { CurrencyFormat.formatRupiah(it) }
                tvPriceTotal.text = CurrencyFormat.formatRupiah(priceTotal)

                rbAgree.isSelected = true
                rgPayment.setOnCheckedChangeListener { _, i ->
                    when (i) {
                        R.id.rb_agree -> etPayValue.isEnabled = false
                        else -> etPayValue.isEnabled = true
                    }
                }

                btnAccept.setOnClickListener {
                    partial.partialRoot.visibility = View.VISIBLE
                    clRoot.visibility = View.GONE

                    val priceToPay =
                        if (rbAgree.isSelected) CurrencyFormat.removeFormat(tvPriceTotal.text.toString())
                        else CurrencyFormat.removeFormat(etPayValue.text.toString())

                    Handler(Looper.getMainLooper())
                        .postDelayed({
                            partial.partialRoot.visibility = View.GONE
                            clRoot.visibility = View.VISIBLE

                            val i = Intent(
                                this@DetailTaskActivity,
                                ViewProgressFindActivity::class.java
                            )
                            i.putExtra(Constants.HELPER_ID, helperTask)
                            i.putExtra(Constants.DATA, priceToPay)
                            startActivity(i)
                        }, 2000)
                }
            }
        }
    }
}