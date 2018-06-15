package com.tanchuev.actionviews.viewmodel.example.domain.repository

import com.tanchuev.actionviews.viewmodel.example.domain.model.Gift
import io.reactivex.Single

/**
 * Created by marat.taychinov
 */
interface GiftRepository {

    fun getAll(): Single<List<Gift>>
}