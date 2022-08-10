package com.example.android.databinding.basicsample.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _name = MutableLiveData("Ada")
    private val _lastName = MutableLiveData("Lovelace")
    private val _likes =  MutableLiveData(0)

    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastName
    val likes: LiveData<Int> = _likes

    val popularity: LiveData<Popularity> = Transformations.map(_likes) {
        when {
            it > Popularity.STAR.minLikes -> Popularity.STAR
            it > Popularity.POPULAR.minLikes -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }

    fun onLike() {
        incrementLikeNumber()
    }

    private fun incrementLikeNumber(){
        _likes.value = (_likes.value ?: 0) + 1
    }
}

enum class Popularity(val minLikes: Int) {
    NORMAL(0),
    POPULAR(4),
    STAR(9)
}
