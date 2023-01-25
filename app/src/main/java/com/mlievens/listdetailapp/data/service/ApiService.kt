package com.mlievens.listdetailapp.data.service

import com.mlievens.listdetailapp.data.models.MagicItemsResult
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("magicitems/?limit=500")
    suspend fun getItems(): Response<MagicItemsResult>
}