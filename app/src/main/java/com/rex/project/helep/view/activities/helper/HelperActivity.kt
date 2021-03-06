package com.rex.project.helep.view.activities.helper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rex.project.helep.databinding.ActivityHelperBinding
import com.rex.project.helep.model.Helper
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.view.activities.payment.PaymentActivity

class HelperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskId = intent.getLongExtra(Constants.TASK_ID, -1)
        val adapter = HelperAdapter(Helper.getHelpers()) {
            val i = Intent(this, PaymentActivity::class.java)
            i.putExtra(Constants.TASK_ID, taskId)
            i.putExtra(Constants.HELPER, it)
            startActivity(i)
        }

        binding = ActivityHelperBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)

            rvHelper.adapter = adapter
            rvHelper.layoutManager = LinearLayoutManager(this@HelperActivity)
            ivBack.setOnClickListener { finish() }
        }
    }
}