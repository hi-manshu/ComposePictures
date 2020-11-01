package com.himanshoe.landing.data.response


import com.google.gson.annotations.SerializedName

data class PhotoResponse(@SerializedName("author")
                         val author: String = "",
                         @SerializedName("width")
                         val width: Int = 0,
                         @SerializedName("download_url")
                         val downloadUrl: String = "",
                         @SerializedName("id")
                         val id: String = "",
                         @SerializedName("url")
                         val url: String = "",
                         @SerializedName("height")
                         val height: Int = 0)


