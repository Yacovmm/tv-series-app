package com.zygotecnologia.zygotv.domain

import com.zygotecnologia.zygotv.domain.entities.GenreEntity
import com.zygotecnologia.zygotv.domain.entities.ShowEntity
import com.zygotecnologia.zygotv.utils.ResponseWrapper

interface ITmdbRepository {

    suspend fun fetchGenres(): ResponseWrapper<List<GenreEntity>>

    suspend fun getPopularShows(): ResponseWrapper<List<Pair<String, List<ShowEntity>>>>

    suspend fun getShowById(id: Int): ResponseWrapper<ShowEntity>
}
