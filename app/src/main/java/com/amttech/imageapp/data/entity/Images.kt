package com.amttech.imageapp.data.entity

data class Images (var comments: Int,
                   var downloads: Int,
                   var id: Int,
                   var previewURL: String,
                   var likes: Int,
                   var tags: String,
                   var type: String,
                   var user: String,
                   var views: Int,
                   var imageWidth: Int,
                   var imageHeight: Int
)