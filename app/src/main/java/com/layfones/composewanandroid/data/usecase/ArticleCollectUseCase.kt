package com.layfones.composewanandroid.data.usecase

import com.layfones.composewanandroid.data.repository.CollectRepository
import com.layfones.composewanandroid.data.services.model.CollectEvent
import javax.inject.Inject

class ArticleCollectUseCase @Inject constructor(private val collectRepository: CollectRepository) {
    suspend fun articleCollectAction(event: CollectEvent) =
        collectRepository.articleCollectAction(event)
}