package com.zygotecnologia.zygotv.ui.fragments.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.domain.ITmdbRepository
import com.zygotecnologia.zygotv.domain.entities.ShowEntity
import com.zygotecnologia.zygotv.utils.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val repository: ITmdbRepository
) : ViewModel() {

//    init {
//        getGenres()
//    }

    private val _showsResponse = MutableLiveData<List<Pair<String, List<ShowEntity>>>>()
    val showsResponse: LiveData<List<Pair<String, List<ShowEntity>>>> get() = _showsResponse

    private val _popularShow = MutableLiveData<ShowEntity>()
    val popularShow: LiveData<ShowEntity> get() = _popularShow

    fun getData() = viewModelScope.launch {
        val popularShows = async { repository.getPopularShows() }
        handleResponse(popularShows.await())
    }

    private fun handleResponse(
        showsResponse: ResponseWrapper<List<Pair<String, List<ShowEntity>>>>
    ) {
        when (showsResponse) {
            is ResponseWrapper.Success -> {
                showsResponse.result.let {
                    _showsResponse.postValue(it)
                }
            }
            is ResponseWrapper.Error -> TODO()
        }
    }
}
