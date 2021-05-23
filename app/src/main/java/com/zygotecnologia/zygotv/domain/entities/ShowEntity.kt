package com.zygotecnologia.zygotv.domain.entities

import android.os.Parcelable
import com.zygotecnologia.zygotv.data.network.model.Show
import com.zygotecnologia.zygotv.data.network.model.ShowResponse
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowEntity(
    val id: Int?,
    val genres: List<GenreEntity>,
    val originalName: String,
    val name: String = "",
    val voteCount: Int,
    val backdropImgUrl: String?,
    val originalLanguage: String,
    val overview: String,
    val posterImgUrl: String
) : Parcelable {

    companion object {

        fun mapper(genres: List<GenreEntity>?, showResponse: ShowResponse): List<ShowEntity> {
            return showResponse.results?.map {
                mapper(genres, it)
            } ?: emptyList()
        }

        fun mapper(genres: List<GenreEntity>?, show: Show): ShowEntity {
            val genresEntities = genres?.filter { genre ->
                show.genreIds?.contains(genre.id) == true
            } ?: emptyList()

            return ShowEntity(
                id = show.id ?: 0,
                genres = genresEntities,
                originalLanguage = show.originalName ?: "",
                name = show.name ?: "",
                voteCount = show.voteCount ?: 0,
                backdropImgUrl = ImageUrlBuilder.buildBackdropUrl(show.backdropPath ?: ""),
                originalName = show.originalLanguage ?: "",
                overview = show.overview ?: "",
                posterImgUrl = ImageUrlBuilder.buildPosterUrl(show.posterPath ?: "")
            )
        }
    }
}
