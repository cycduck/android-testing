package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewTask_setsNewTaskEvent() {

        // GIVEN a fresh TasksViewModel
        val tasksViewModel =  TasksViewModel(ApplicationProvider.getApplicationContext())

        // WHEN adding a new task
        tasksViewModel.addNewTask()

        // THEN the new task event is triggered
        // Get the LiveData value for newTaskEvent using getOrAwaitValue.
        // The fun is defined as LiveData.getOrAwaitValue
        // newTaskEvent is basically LiveData from MutableLiveData
        // and it's from the tasksViewModel
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        // Assert that the value is not null.
        assertThat(value.getContentIfNotHandled(), not(nullValue()))

    }
}