package com.rex.project.helep.view.activities.payment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityPaymentBinding
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.model.Helper
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.utils.CurrencyFormat
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.home.HomeActivity

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var viewModel: PaymentViewModel

    private var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskId = intent.getLongExtra(Constants.TASK_ID, -1)
        val helper = intent.getParcelableExtra<Helper>(Constants.HELPER)

        val factory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[PaymentViewModel::class.java]
        viewModel.getTaskById(taskId)?.observe(this) {
            task = it
            helper?.let { it1 -> initUi(it, it1) }
        }

        binding = ActivityPaymentBinding.inflate(layoutInflater)
    }

    private fun initUi(task: Task, helper: Helper) {
        binding.apply {
            setContentView(root)
            tvOrderIdValue.text = getString(R.string.task_cap, taskId)
            tvHelperByValue.text = helper.name
            tvCategory.text = task.category
            tvTaskDescription.text = task.shortDesc
            tvTaskOne.text = task.taskOne
            tvTaskOneCount.text = "${task.taskOneCount}x"

            if (task.taskTwo.isNotEmpty()) {
                tvTaskTwo.visibility = View.VISIBLE
                tvTaskTwoCount.visibility = View.VISIBLE
                vCircleIndicator2.visibility = View.VISIBLE

                tvTaskTwo.text = task.taskTwo
                tvTaskTwoCount.text = "${task.taskTwoCount}x"
            }

            tvPrice.text = task.price.let { CurrencyFormat.formatRupiah(it) }

            val taskTotal = (task.taskOneCount.plus(task.taskTwoCount))
            tvTaskCount.text = taskTotal.toString()
            tvPriceTotal.text = CurrencyFormat.formatRupiah(task.price.times(taskTotal))

            rbWallet.isSelected = true
            btnPay.setOnClickListener {
                viewModel.updateTaskStatus(task.id, helper.id, Constants.ON_PROGRESS)

                Toast.makeText(
                    this@PaymentActivity,
                    R.string.berhasil,
                    Toast.LENGTH_SHORT
                ).show()

                val i = Intent(this@PaymentActivity, HomeActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i)
            }
        }
    }
}