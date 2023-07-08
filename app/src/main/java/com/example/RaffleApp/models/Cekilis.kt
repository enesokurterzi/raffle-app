package com.example.RaffleApp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cekilis")
data class Cekilis(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    var page: String?,
    var link: String?,
    var img: String?,
    var title: String?,
    var timeLeft: String?,
    var giftValue: String?,
    var price: String?,
    var following: Boolean?,
    var detail: String?,
    var startingDate: String?,
    var endDate: String?,
    var drawDate: String?,
    var listingDate: String?,
    var minCost: String?,
    var detailGiftValue: String?,
    var giftCount: String?
)
