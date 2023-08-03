package com.workshop.universityannouncementsboard.extra

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class NewsyNewsRepository(
    private val newsFactory: NewsFactory
) {
    private val newsyApi = Newsy()

    init {
        newsyApi.user = "your username"
        newsyApi.token = "your token"
        newsyApi.refreshTime = 1.seconds
    }

    suspend fun getNewsDetails(newsId: Int): NewsDetails? {
        val news = newsyApi.getNews(newsId) ?: return null
        return newsFactory.produceNews(news)
    }

    suspend fun updateNews(newsUpdate: NewsUpdate): NewsDetails? {
        val newsyUpdate = newsFactory.produceNewsyUpdate(newsUpdate)
        val id = newsyApi.update(newsyUpdate) ?: return null
        newsyApi.awaitAllUpdates()
        val news = newsyApi.getNews(id) ?: return null
        return newsFactory.produceNews(news)
    }
}

class NewsDetails
class NewsFactory {
    fun produceNews(news: Newsy.NewsyNews): NewsDetails = TODO()
    fun produceNewsyUpdate(news: NewsUpdate): Newsy.NewsyUpdate = TODO()
}

class NewsUpdate
class Newsy {
    var user: String = ""
    var token: String = ""
    var refreshTime: Duration? = null
    suspend fun getNews(newsId: Int): NewsyNews? = TODO()
    suspend fun update(newsUpdate: NewsyUpdate): Int? = TODO()
    suspend fun awaitAllUpdates() {}

    class NewsyNews
    class NewsyUpdate
}