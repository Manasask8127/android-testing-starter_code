package com.example.android.architecture.blueprints.todoapp.tasks

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
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

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{

    @get:Rule
    val instantTaskExecutable=InstantTaskExecutorRule()

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
        val taskViewModel=TasksViewModel(ApplicationProvider.getApplicationContext())

        // When adding a new task
        taskViewModel.addNewTask()

        // Then the new task event is triggered
        val value = taskViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), (not(nullValue())))

    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        // Given a fresh ViewModel
        val taskViewModel=TasksViewModel(ApplicationProvider.getApplicationContext())

        // When the filter type is ALL_TASKS
        taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        val value=taskViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value,(not(false)))
       //or  assertThat(value, CoreMatchers.`is`(true))

    }
}