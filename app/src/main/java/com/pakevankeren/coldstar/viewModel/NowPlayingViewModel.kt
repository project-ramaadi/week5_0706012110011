package com.pakevankeren.coldstar.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pakevankeren.coldstar.config.RetrofitConfig
import com.pakevankeren.coldstar.model.NowPlayingResult
import com.pakevankeren.coldstar.repository.NowPlayingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(private val repository: NowPlayingRepository) :
    ViewModel() {

    /**
     * The list of now playing movie
     * @internal Only for access inside nowPlayingViewModel
     * */
    private val _nowPlaying: MutableLiveData<ArrayList<NowPlayingResult>> by lazy { MutableLiveData<ArrayList<NowPlayingResult>>() }
    val nowPlaying: LiveData<ArrayList<NowPlayingResult>>
        get() = _nowPlaying


    fun getNowPlaying(
        language: String = "en-US",
        page: Int = 1,
        region: String = RetrofitConfig.DEFAULT_REGION
    ) =
        viewModelScope.launch {
            repository.getNowPlayingData(language, page, region).let { response ->
                if (response.isSuccessful) {
                    _nowPlaying.postValue(response.body()?.results as ArrayList<NowPlayingResult>)
                } else {
                    Log.e("Get Data", "Failed to get Now Playing Data")
                }
            }
        }

}