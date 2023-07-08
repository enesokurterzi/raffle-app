package com.example.RaffleApp.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.models.Cekilis

class HomeViewModel : ViewModel() {

    val homeCekilis: MutableLiveData<List<Cekilis>> = MutableLiveData()

    fun getHomeCekilis(context: Context) {
        val dbCekilis = DbController(context).db
        homeCekilis.value = dbCekilis.cekilisDao().getByPage("Cekilisler")
        dbCekilis.close()
    }
}