package com.rex.project.helep.view.activities.viewProgressFind

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityViewProgressBinding
import com.rex.project.helep.databinding.ActivityViewProgressFindBinding
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.model.Helper
import com.rex.project.helep.model.HelperTask
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.utils.CurrencyFormat
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.home.HomeActivity
import com.rex.project.helep.view.activities.taskDone.TaskDoneActivity
import com.rex.project.helep.view.activities.viewProgress.ViewProgressViewModel
import kotlinx.coroutines.flow.*
import java.util.*

class ViewProgressFindActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityViewProgressFindBinding
    private lateinit var viewModel: ViewProgressFindViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewProgressFindBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val factory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[ViewProgressFindViewModel::class.java]

        setupObserver()
        initUi()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val yourLocation = LatLng(-6.9024759, 107.6166213)
        val helperLocation = LatLng(-6.8998231, 107.6165014)

        mMap = googleMap
        mMap.addMarker(MarkerOptions().position(yourLocation).title("You"))
        mMap.addMarker(MarkerOptions().position(helperLocation).title("helper"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(yourLocation, 16F))

        viewModel.getRoute(
            mapOf("start" to "107.6166213,-6.9024759"),
            mapOf("end" to "107.6165014,-6.8998231")
        )
    }

    override fun onBackPressed() { moveToHome() }

    private fun processRoute(result: List<LatLng>) {
        val lineoption = PolylineOptions()
        lineoption.addAll(result)
        lineoption.width(12f)
        lineoption.color(Color.RED)
        lineoption.geodesic(true)

        mMap.addPolyline(lineoption)
    }

    private fun setupObserver() {
        viewModel.getRoutes().observe(this) {
            if (!it.isNullOrEmpty()) processRoute(it)
        }
        viewModel.getLoading().observe(this) {
            if (it) binding.progress.visibility = View.VISIBLE
            else binding.progress.visibility = View.GONE
        }
        viewModel.getError().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUi() {
        val helperTask = intent.getParcelableExtra<HelperTask>(Constants.HELPER_ID)

        var second = 0
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val duration = formatDuration(second)
                binding.bottomSheetLayout.tvTimeValue.text = duration

                second+=1
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(runnable)

        binding.bottomSheetLayout.apply {
            ivBack.setOnClickListener { finish() }

            helperTask?.avatar?.let { civAvatar.setImageResource(it) }
            tvName.text = helperTask?.username
            tvTaskOne.text = helperTask?.taskOne
            tvTaskOneCount.text = "${helperTask?.taskOneCount}x"
            tvTaskOneDesc.text = getString(R.string.done)

            if (!helperTask?.taskTwo.isNullOrEmpty()) {
                vCircleIndicator2.visibility = View.VISIBLE
                tvTaskTwo.apply {
                    text = "${helperTask?.taskTwo}x"
                    visibility = View.VISIBLE
                }
                tvTaskTwoCount.apply {
                    text = "${helperTask?.taskTwoCount}"
                    visibility = View.VISIBLE
                }
            }

            val distanceInKm = 10000
            tvDistanceValue.text = "$distanceInKm Km"
            btnDone.setOnClickListener {
                handler.removeCallbacks(runnable)
                val price = intent.getLongExtra(Constants.DATA, -1)

                tvTaskOneDesc.visibility = View.VISIBLE
                tvTaskTwoDesc.visibility = View.VISIBLE
                binding.clRoot.visibility = View.GONE
                binding.partialTaskReceived.tvBalanceSubmitted.text = getString(R.string.telah_masuk_ke_saldo_wallet, CurrencyFormat.formatRupiah(price))
                binding.partialTaskReceived.partialRoot.visibility = View.VISIBLE
                binding.partialTaskReceived.ivBack.setOnClickListener { moveToHome() }

                viewModel.addToWallet(price)
            }
        }
    }

    private fun moveToHome() {
        val i = Intent(this, HomeActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }
    private fun formatDuration(seconds: Int): String {
        val second = seconds % 60
        var minute = seconds / 60
        var hour = 0

        if(minute >= 60) {
            hour = minute / 60
            minute %= 60
        }

        return "${formatTwoDigitString(hour)}:${formatTwoDigitString(minute)}:${formatTwoDigitString(second)}"
    }

    private fun formatTwoDigitString(number: Int): String {
        return "%02d".format(number)
    }
}