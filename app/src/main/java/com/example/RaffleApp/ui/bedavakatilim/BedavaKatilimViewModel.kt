package com.example.RaffleApp.ui.bedavakatilim

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.models.Cekilis

class BedavaKatilimViewModel : ViewModel() {

    val bedavaKatilimCekilis: MutableLiveData<List<Cekilis>> = MutableLiveData()

    fun getbedavaKatilimCekilis(context: Context) {
        val dbCekilis = DbController(context).db
        bedavaKatilimCekilis.value = dbCekilis.cekilisDao().getByPage("BedavaKatilim")
        dbCekilis.close()
    }

}