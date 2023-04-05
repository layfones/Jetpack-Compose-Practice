package com.layfones.composewanandroid.repository

import com.layfones.composewanandroid.data.repository.HomeRepository
import com.layfones.composewanandroid.services.impl.HomeServiceImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class HomeRepositoryTest {


    private val repository: HomeRepository = HomeRepository(HomeServiceImpl())


    @Test
    fun getArticlePageList() {
        runBlocking {
            val pageList = repository.getArticlePageList(3)
            val toList = pageList.toList(arrayListOf())
            println("Paging1::::$pageList")
            println("Paging2::::$toList")
        }
    }

    @Test
    fun getSquarePageList() {
        println("FFFF")
    }

    @Test
    fun getAnswerPageList() {
    }
}