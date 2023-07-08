package com.example.RaffleApp.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.models.Cekilis

class DetailViewModel : ViewModel() {

    fun getByTitle(context: Context, cekilis: Cekilis) {
        val dbCekilis = DbController(context).db
        val getByTitleCekilis = dbCekilis.cekilisDao().getByTitle(cekilis.title!!)

        for (item in getByTitleCekilis) {
            item.following = cekilis.following
            dbCekilis.cekilisDao().update(item)
        }

        dbCekilis.close()
    }

}