package com.kotlin.todoapp

import androidx.annotation.Nullable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
class TaskViewModel: ViewModel() {
    var taskItems = MutableLiveData<MutableList<TaskItem>>()
    init{
        taskItems.value = mutableListOf()
    }
    fun addTaskItem(newTask: TaskItem){
        val list = taskItems.value
        list!!.add(newTask)
        taskItems.postValue(list)
    }
    fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?){
        val list = taskItems.value
        val task = list!!.find { it.id == id }!!
//        val task? = list?.let{ find(  it.id == id )} ?: null





//        val task? = list?.find { it.id == id }?:null
//        val task = list?.let{
//            find(it.id == id) } ?: Null
//        val task = list?.let{ find{it.id==id}}
//        val task? = list?.let{ find(  it.id == id )} ?: Null
        task.name = name
        task.desc = desc
        task.dueTime = dueTime
        taskItems.postValue(list)

    }
    fun setCompleted(taskItem: TaskItem){
        val list = taskItems.value
        val task = list!!.find { it.id == taskItem.id }!!
        if(task.completedDate == null){
            task.completedDate = LocalDate.now()
        }
        taskItems.postValue(list)
    }

//    var name = MutableLiveData<String>()
//    var desc = MutableLiveData<String>()
}