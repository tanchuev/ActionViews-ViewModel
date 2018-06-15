package com.tanchuev.actionviews.viewmodel.example.domain.model

import com.tanchuev.actionviews.viewmodel.example.domain.model.base.Model

/**
 * Created by marat.taychinov
 */
data class Gift(
    val name: String,
    val description: String,
    val imageUrl: String,
    val outLink: String,
    val rating: Int,
    val priceFrom: Int,
    val priceTo: Int
//todo нужно ли добавлять поля, которые есть в фильтре?
) : Model()