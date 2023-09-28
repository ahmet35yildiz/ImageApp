package com.amttech.imageapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amttech.imageapp.data.entity.Images
import com.amttech.imageapp.data.repo.ImagesInterfaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListingFragmentViewModel @Inject constructor(private var iRepo: ImagesInterfaceRepository) : ViewModel(){
    var imagesList: LiveData<List<Images>?> = iRepo.imagesList

    fun showImages(searchTerm: String) {
        iRepo.searchResult(searchTerm)
    }

}