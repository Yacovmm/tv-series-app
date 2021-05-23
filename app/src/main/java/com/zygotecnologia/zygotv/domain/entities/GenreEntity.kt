package com.zygotecnologia.zygotv.domain.entities

import android.os.Parcelable
import com.zygotecnologia.zygotv.data.network.model.Genre
import com.zygotecnologia.zygotv.data.network.model.GenreResponse
import kotlinx.parcelize.Parcelize

@Parcelize

data class GenreEntity(
    val id: Int,
    val name: String
) : Parcelable {
    companion object {
        fun mapper(genresResponse: GenreResponse): List<GenreEntity> {
            return genresResponse.genres?.let { genres ->
                genres.map {
                    GenreEntity(
                        id = it.id ?: 0,
                        name = it.name ?: "Generico"
                    )
                }
            } ?: emptyList()
        }

        fun mapper(genres: List<Genre>?): List<GenreEntity> {
            return genres?.let { genres ->
                genres.map {
                    GenreEntity(
                        id = it.id ?: 0,
                        name = it.name ?: "Generico"
                    )
                }
            } ?: emptyList()
        }

        fun mapper(genresIds: List<Int>, genres: List<Genre>?): List<GenreEntity> {
            return genres?.let { genres ->
                genres.map {
                    GenreEntity(
                        id = it.id ?: 0,
                        name = it.name ?: "Generico"
                    )
                }
            } ?: emptyList()
        }
    }
}
