package com.example.android.architecture.blueprints.todoapp.tasks

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import getOrAwaitValue
import junit.framework.TestCase

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class) no longer using AndroidX

class TasksViewModelTest{

    @get:Rule
    val instantTaskExecutable=InstantTaskExecutorRule()

    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    private lateinit var tasksViewModel:TasksViewModel

    // Rest of class
    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        tasksViewModel = TasksViewModel(tasksRepository)

    }



//used to avoid repetative initialization
//    // Subject under test
//    private lateinit var tasksViewModel: TasksViewModel
//
//    @Before
//    fun setupViewModel() {
//        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
//    }

    @Test
    fun addNewTask_setsNewTaskEvent(){
        // Given a fresh TasksViewModel
        val taskViewModel=TasksViewModel(tasksRepository)

        // When adding a new task
        taskViewModel.addNewTask()

        // Then the new task event is triggered
        val value = taskViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))

    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        // Given a fresh ViewModel
        val taskViewModel=TasksViewModel(tasksRepository )

        // When the filter type is ALL_TASKS
        taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        val value=taskViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value,(not(false)))
       //or  assertThat(value, CoreMatchers.`is`(true))

    }
}