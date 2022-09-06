package com.tm00nlight.chitasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ZooViewModel : ViewModel() {
    val animalLiveData: LiveData<List<Animal>>

    init {
        animalLiveData = ZooFetchr().fetchContents()
    }
}