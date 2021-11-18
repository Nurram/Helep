package com.rex.project.helep.view.activities.detailTask

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityDetailTaskBinding
import com.rex.project.helep.local.entities.Bidding
import com.rex.project.helep.local.entities.TaskAndUser
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.utils.CurrencyFormat
import com.rex.project.helep.view.ViewModelFactory

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTaskBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskAndUser = intent.getParcelableExtra<TaskAndUser>(Constants.DATA)
        val factory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.apply {
            if (taskAndUser != null) {
                val task = taskAndUser.task
                val imageByte = taskAndUser.user.image
                val bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)

                civAvatar.setImageBitmap(bitmap)

                var taskTotal = task.taskOneCount
                taskTotal += task.taskTwoCount

                val price = task.price
                val priceTotal = price * taskTotal

                tvName.text = taskAndUser.user.username
                tvDesc.text = task.shortDesc
                tvTaskOne.text = task.taskOne
                tvTaskOneCount.text = getString(R.string.task_count, task.taskOneCount)
                tvTaskTwo.text = task.taskTwo
                tvTaskTwoCount.text = getString(R.string.task_count, task.taskTwoCount)
                tvPricePerTask.text = task.price.let { CurrencyFormat.formatRupiah(it) }
                tvPriceTotal.text = CurrencyFormat.formatRupiah(priceTotal)

                rbAgree.isSelected = true
                rgPayment.setOnCheckedChangeListener { _, i ->
                    when(i) {
                        R.id.rb_agree -> etPayValue.isEnabled = false
                        else -> etPayValue.isEnabled = true
                    }
                }

                btnAccept.setOnClickListener {
                    val bidPrice = if(rbAgree.isSelected) priceTotal
                    else etPayValue.text.toString().toLong()

                    val currentId = viewModel.getLoggedIn()
                    val bidding = Bidding(0, currentId, task.id, bidPrice)
                    viewModel.insertBidding(bidding)
                }
            }
        }
    }
}