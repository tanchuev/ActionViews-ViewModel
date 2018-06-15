package com.tanchuev.actionviews.viewmodel.example.domain.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author tanchuev
 */
abstract class Model : Comparable<Model>,
    Cloneable {
    @SerializedName("ID")
    @Expose
    var id: String? = null

    constructor()

    constructor(id: String) {
        this.id = id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as Model?
        return if (id != null) id == that!!.id else that!!.id == null
    }

    override fun hashCode(): Int {
        var result = if (id != null) id!!.hashCode() else 0
        result = 31 * result + if (id != null) id!!.hashCode() else 0
        return result
    }

    override fun compareTo(other: Model): Int {
        val lastObject = this.id!!.toUpperCase()
            .compareTo(other.id!!.toUpperCase())
        return if (lastObject == 0) this.id!!.toUpperCase().compareTo(other.id!!.toUpperCase()) else lastObject
    }
}
