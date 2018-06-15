package com.tanchuev.actionviews.viewmodel.example.data.repository.base

import retrofit2.Retrofit
import kotlin.reflect.KClass

/**
 * Created by marat.taychinov
 */
abstract class ServerRepository<TApi : Any>(retrofit: Retrofit) {

    protected val api: TApi

    protected abstract val apiClass: KClass<TApi>

    init {
        api = retrofit.create(apiClass.java)
    }
}