package com.rex.project.helep.view.activities.topUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityTopUpBinding
import com.rex.project.helep.utils.CurrencyFormat
import com.rex.project.helep.view.ViewModelFactory

class TopUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopUpBinding
    private lateinit var viewModel: TopUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[TopUpViewModel::class.java]

        binding.apply {
            tv25000.text = CurrencyFormat.formatRupiah(tv25000.text.toString().toLong())
            tv50000.text = CurrencyFormat.formatRupiah(tv50000.text.toString().toLong())
            tv100000.text = CurrencyFormat.formatRupiah(tv100000.text.toString().toLong())
            tv250000.text = CurrencyFormat.formatRupiah(tv250000.text.toString().toLong())
            tv500000.text = CurrencyFormat.formatRupiah(tv500000.text.toString().toLong())
            tv1000000.text = CurrencyFormat.formatRupiah(tv1000000.text.toString().toLong())

            crd25000.setOnClickListener { addToBalanceEt(25000) }
            crd50000.setOnClickListener { addToBalanceEt(50000) }
            crd100000.setOnClickListener { addToBalanceEt(100000) }
            crd250000.setOnClickListener { addToBalanceEt(250000) }
            crd500000.setOnClickListener { addToBalanceEt(500000) }
            crd1000000.setOnClickListener { addToBalanceEt(1000000) }

            btnPay.setOnClickListener {
                val currentValue = binding.etPayValue.text.toString()
                viewModel.topupWallet(CurrencyFormat.removeFormat(currentValue))
                Toast.makeText(this@TopUpActivity, R.string.berhasil, Toast.LENGTH_SHORT).show()

                finish()
            }
        }
    }

    private fun addToBalanceEt(value: Long) {
        val currentValue = binding.etPayValue.text.toString()
        val summedValue = value.plus(CurrencyFormat.removeFormat(currentValue))

        binding.etPayValue.setText(CurrencyFormat.formatRupiah(summedValue))
    }
}