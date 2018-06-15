package com.tanchuev.actionviews.viewmodel.example.data.repository.mock

import com.tanchuev.actionviews.viewmodel.example.domain.model.Gift
import com.tanchuev.actionviews.viewmodel.example.domain.repository.GiftRepository
import io.reactivex.Single

/**
 * Created by marat.taychinov
 */
class GiftMockRepository : GiftRepository {

    override fun getAll(): Single<List<Gift>> = Single.just(object : ArrayList<Gift>() {
        init {
            var i = 0
            while (i < 25) {
                add(
                    Gift(
                        "Название подарка",
                        "Супер подробное описание подарка, да такое, что лучше даже и не придумать.",
                        "http://s1.1zoom.me/big0/930/Coast_Sunrises_and_sunsets_Waves_USA_Ocean_Kaneohe_521540_1280x775.jpg",
                        "", 777, 1, 10
                    )
                )
                i++
            }
        }
    })
}