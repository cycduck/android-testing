package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest {
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero () {
        // Create an active task
        // listOf is a kotlin method https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/list-of.html
        // Calling the task constructor (custom)
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = false)
        )

        // Call the function you want to test
        // here we are testing the active task we just created
        val result = getActiveAndCompletedStats(tasks)

        // Check the results
        print(result) // results sends back this: StatsResult(activeTasksPercent=100.0, completedTasksPercent=0.0)
        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 100f)
    }

    // If there is 1 completed task and 0 active tasks, the activeTasks percentage should be 0f, and the completed tasks percentage should be 100f
    @Test
    fun getActivityCompletedStates_allCompleted_returnsZeroHundred () {
        val tasks = listOf(
                Task("title", "desc", isCompleted = true)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(result.completedTasksPercent, 100f)
        assertEquals(result.activeTasksPercent, 0f)

    }

    //    If there are two completed tasks and three active tasks, the completed percentage should be 40f and the active percentage should be 60f.
    @Test
    fun getActivityCompletedStats_both_returnsFortySixty () {
        val tasks = listOf(
              Task("completed", "desc", isCompleted = true),
              Task("completed2", "desc", isCompleted = true),
              Task("completed3", "desc", isCompleted = true),
              Task("Not completed", "desc", isCompleted = false),
              Task("Not completed 2", "desc", isCompleted = false)
        )

        val result = getActiveAndCompletedStats(tasks)

        assertEquals(result.completedTasksPercent, 60f)
        assertEquals(result.activeTasksPercent, 40f)
    }

    // If there is an empty list (emptyList()), then both percentages should be 0f.
    @Test
    fun  getActiveAndCompletedStats_empty_returnsZeros () {

        val tasks : List<Task> = emptyList()
        // https://stackoverflow.com/questions/48741473/what-is-the-function-of-emptylist-in-kotlin

        val result = getActiveAndCompletedStats(tasks)

        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros () {

        val result = getActiveAndCompletedStats(null)

        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 0f)
    }
}