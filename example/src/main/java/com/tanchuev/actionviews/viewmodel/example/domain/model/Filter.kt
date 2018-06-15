package com.tanchuev.actionviews.viewmodel.example.domain.model

import com.tanchuev.actionviews.viewmodel.example.domain.model.base.Model

/**
 * @param work - род деятельности - работа
 * @param gender - пол
 * @param age - возраст
 * @param height - рост
 * @param weight - вес
 * @param relationship - какие отношения с человеком
 * @param priceFrom - стоимость от
 * @param priceTo - стоимость до
 * @param hobby - хобби
 * @param event - праздник/событие
 */
data class Filter(
    var work: String, //
    var gender: Gender,
    var age: Int,
    var height: Int,
    var weight: Int,
    var relationship: String,
    var priceFrom: Int,
    var priceTo: Int,
    var hobby: String,
    var event: String
) : Model()