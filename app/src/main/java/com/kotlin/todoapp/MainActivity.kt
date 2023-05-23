package com.kotlin.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemClickListener  {
    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels {
        TaskItemModelFactory((application as TodoApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.newTaskButton.setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager,"newTaskTag")

        }
        setRecyclerView()
//        taskViewModel.name.observe(this){
//            binding.taskName.text = String.format("Task Name: %s",it)
//        }
//        taskViewModel.desc.observe(this){
//            binding.taskDesc.text = String.format("Task Desc: %s",it)
//        }
    }
    private fun setRecyclerView()
    {
//improve        https://stackoverflow.com/questions/67024618/observer-on-livedata-in-recyclerview-kolin/67024958#67024958
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it,mainActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }
}