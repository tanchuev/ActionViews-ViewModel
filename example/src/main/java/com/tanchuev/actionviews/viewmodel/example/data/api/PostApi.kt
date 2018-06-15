package com.tanchuev.actionviews.viewmodel.example.data.api

import io.reactivex.Completable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by marat.taychinov
 */
interface PostApi {

    @POST("?method=gift&device_type=1")
    fun enableNotifications(@Query("device_id") deviceId: String, @Query("categories") categoryIds: String): Completable
}