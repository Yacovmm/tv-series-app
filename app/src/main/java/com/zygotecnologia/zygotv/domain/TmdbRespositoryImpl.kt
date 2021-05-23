package com.zygotecnologia.zygotv.domain

import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.data.network.model.Show
import com.zygotecnologia.zygotv.domain.entities.GenreEntity
import com.zygotecnologia.zygotv.domain.entities.ShowEntity
import com.zygotecnologia.zygotv.utils.ResponseWrapper
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class TmdbRespositoryImpl @Inject constructor(
    private val service: TmdbApi,
    private val dispatcher: CoroutineDispatcher
) : ITmdbRepository {

    override suspend fun fetchGenres(): ResponseWrapper<List<GenreEntity>> {
        val response = service.fetchGenresAsync()

        response?.let {
            val entities = GenreEntity.mapper(it)
            return ResponseWrapper.Success(entities)
        } ?: return ResponseWrapper.Error("Unexpected Error")
    }

    override suspend fun getPopularShows(): ResponseWrapper<List<Pair<String, List<ShowEntity>>>> {
        val genresResponses = service.fetchGenresAsync()
        val shows = service.fetchPopularShowsAsync()

        genresResponses?.let { genres ->
            val genresEntities = GenreEntity.mapper(genres)
            shows?.let {
                val showsEntities = ShowEntity.mapper(genresEntities, it)
                val map: MutableList<Pair<String, List<ShowEntity>>> = genresEntities.map { genreEntity ->
                    genreEntity.name to showsEntities.filter { it.genres.contains(genreEntity) }
                }.toMutableList()
                map.add(0, "popular" to listOf(showsEntities[0]))
                return ResponseWrapper.Success(map)
            } ?: return ResponseWrapper.Error("Unexpected Error")
        } ?: return ResponseWrapper.Error("Unexpected Error")
    }

    override suspend fun getShowById(id: Int): ResponseWrapper<ShowEntity> {
        val response = service.fetchShowAsync(id)

        response?.let {
            val entity = ShowEntity.mapper(null, it)
            return ResponseWrapper.Success(entity)
        } ?: return ResponseWrapper.Error("Unexpected Error")
    }
}
