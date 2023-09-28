package com.amttech.imageapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amttech.imageapp.data.entity.Images
import com.amttech.imageapp.data.repo.ImagesInterfaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(private var iRepo: ImagesInterfaceRepository) : ViewModel() {

    val imageAllDetails: LiveData<List<Images>?> = iRepo.imageAllDetails

    fun showImageDetail(imageId: String) {
        iRepo.showImageDetail(imageId)
    }
}