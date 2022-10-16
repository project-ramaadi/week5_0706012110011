package com.pakevankeren.coldstar.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pakevankeren.coldstar.model.MovieDetail
import com.pakevankeren.coldstar.repository.MovieInformationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: MovieInformationRepository) :
    ViewModel() {

    /**
     * The movie detail
     * @internal Only for access inside movieDetailViewModel
     * */
    private val _movieDetail: MutableLiveData<MovieDetail> by lazy { MutableLiveData<MovieDetail>() }
    val movieDetail: LiveData<MovieDetail>
        get() = _movieDetail


    fun getMovieDetail(
        id: Int
    ) = viewModelScope.launch {
        repository.getMovieDetailResult(id).let { response ->
            if (response.isSuccessful) {
                _movieDetail.postValue(response.body() as MovieDetail)
            } else {
                Log.e("Get Data", "Failed to get Movie detail")
            }
        }
    }
}