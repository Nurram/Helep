package com.rex.project.helep.view.activities.addTask

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityAddTaskBinding
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.view.ViewModelFactory

class AddTask : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[AddTaskViewModel::class.java]

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)

            ivBack.setOnClickListener { finish() }
            ArrayAdapter.createFromResource(
                this@AddTask,
                R.array.categories,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spCategories.adapter = adapter
            }
            ArrayAdapter.createFromResource(
                this@AddTask,
                R.array.task_count,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spTaskOne.adapter = adapter
            }
            ArrayAdapter.createFromResource(
                this@AddTask,
                R.array.task_count,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spTaskTwo.adapter = adapter
            }

            btnPost.setOnClickListener {
                val desc = etDesc.text.toString()
                val taskOne = etTaskOne.text.toString()
                val taskOneCount = spTaskOne.selectedItem.toString().toInt()
                val taskTwo = etTaskTwo.text.toString()
                val taskTwoCount = spTaskTwo.selectedItem.toString().toInt()
                val payValue = etPayValue.text.toString()

                if (
                    desc.isEmpty() || taskOne.isEmpty() || taskOneCount == 0 || payValue.isEmpty()
                ) {
                    Toast.makeText(
                        this@AddTask,
                        R.string.isi_semua_form,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val task = Task(
                        0,
                        desc,
                        spCategories.selectedItem.toString(),
                        taskOne,
                        taskOneCount,
                        taskTwo,
                        taskTwoCount,
                        payValue.toLong(),
                        viewModel.getLoggedIn()
                    )

                    postTask(task)
                    finish()
                }
            }
        }
    }

    private fun postTask(task: Task) {
        viewModel.postTask(task)
        Toast.makeText(this, R.string.task_disimpan, Toast.LENGTH_SHORT).show()
    }
}