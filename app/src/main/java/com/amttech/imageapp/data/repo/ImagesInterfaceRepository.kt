package com.amttech.imageapp.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.amttech.imageapp.data.entity.Images
import com.amttech.imageapp.data.entity.ImagesResponse
import com.amttech.imageapp.retrofit.ImagesInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesInterfaceRepository (private var iDao: ImagesInterface){
    var imagesList = MutableLiveData<List<Images>?>()
    var imageAllDetails = MutableLiveData<List<Images>?>()
    var responseError = MutableLiveData<String>()

    fun searchResult(searchTerm: String) {
        iDao.searchResult(searchTerm).enqueue(object : Callback<ImagesResponse> {
            override fun onResponse(
                call: Call<ImagesResponse>?,
                response: Response<ImagesResponse>?
            ) {
                val answer = response?.body()?.hits
                if (answer == null) {
                    imagesList.value = null
                } else {
                    imagesList.value = answer
                }
            }

            override fun onFailure(call: Call<ImagesResponse>?, t: Throwable?) {
                responseError.value = t.toString()
            }
        })
    }

    fun showImageDetail(imageId: String) {
        iDao.searchResultById(imageId).enqueue(object : Callback<ImagesResponse> {
            override fun onResponse(call: Call<ImagesResponse>?, response: Response<ImagesResponse>?) {
                imageAllDetails.value = response?.body()?.hits
                Log.e("id",imageId)
                Log.e("id",response?.body().toString())
            }

            override fun onFailure(call: Call<ImagesResponse>?, t: Throwable?) {
                responseError.value = t.toString()
            }
        })
    }

}