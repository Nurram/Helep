package com.rex.project.helep.view.activities.viewProgress

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
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.model.Helper
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.taskDone.TaskDoneActivity
import kotlinx.coroutines.flow.*
import java.util.*

class ViewProgressActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityViewProgressBinding
    private lateinit var viewModel: ViewProgressViewModel

    private var price = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        price = intent.getLongExtra(Constants.DATA, -1)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val factory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[ViewProgressViewModel::class.java]

        setupObserver()
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

    private fun processRoute(result: List<LatLng>) {
        val lineoption = PolylineOptions()
        lineoption.addAll(result)
        lineoption.width(12f)
        lineoption.color(Color.RED)
        lineoption.geodesic(true)

        mMap.addPolyline(lineoption)
    }

    private fun setupObserver() {
        val taskId = intent.getLongExtra(Constants.TASK_ID, -1)

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
        viewModel.getTaskById(taskId)?.observe(this) {
            initUi(it)
        }
    }

    private fun initUi(task: Task) {
        var second = 0
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val duration = formatDuration(second)
                binding.bottomSheetLayout.tvTimeValue.text = duration

                second += 1
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(runnable)

        binding.bottomSheetLayout.apply {
            val user = Helper.getHelperById(task.winnerId)
            ivBack.setOnClickListener { finish() }

//            val bitmap = BitmapFactory.decodeByteArray(user.image, 0 , user.image.size)
            civAvatar.setImageResource(user.avatar)
            tvName.text = user.name
            tvTaskOne.text = task.taskOne
            tvTaskOneCount.text = "${task.taskOneCount}x"
            tvTaskOneDesc.text = getString(R.string.done)

            if (task.taskTwo.isNotEmpty()) {
                vCircleIndicator2.visibility = View.VISIBLE
                tvTaskTwo.apply {
                    text = "${task.taskTwo}x"
                    visibility = View.VISIBLE
                }
                tvTaskTwoCount.apply {
                    text = "${task.taskTwoCount}"
                    visibility = View.VISIBLE
                }
            }

            val distanceInKm = 10000
            tvDistanceValue.text = "$distanceInKm Km"
            btnDone.setOnClickListener {
                handler.removeCallbacks(runnable)
                viewModel.updateTaskStatus(task.id, task.winnerId, Constants.DONE)

                tvTaskOneDesc.visibility = View.VISIBLE
                tvTaskTwoDesc.visibility = View.VISIBLE

                val i = Intent(this@ViewProgressActivity, TaskDoneActivity::class.java)
                i.putExtra(Constants.HELPER_ID, task.winnerId)
                i.putExtra(Constants.DATA, price)
                startActivity(i)
            }
        }
    }

    private fun formatDuration(seconds: Int): String {
        val second = seconds % 60
        var minute = seconds / 60
        var hour = 0

        if (minute >= 60) {
            hour = minute / 60
            minute %= 60
        }

        return "${formatTwoDigitString(hour)}:${formatTwoDigitString(minute)}:${
            formatTwoDigitString(
                second
            )
        }"
    }

    private fun formatTwoDigitString(number: Int): String {
        return "%02d".format(number)
    }
}