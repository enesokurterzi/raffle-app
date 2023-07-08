package com.example.RaffleApp.ui.tatilkazan

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.models.Cekilis

class TatilKazanViewModel : ViewModel() {

    val tatilKazanCekilis: MutableLiveData<List<Cekilis>> = MutableLiveData()

    fun getTatilKazanCekilis(context: Context) {
        val dbCekilis = DbController(context).db
        tatilKazanCekilis.value = dbCekilis.cekilisDao().getByPage("TatilKazan")
        dbCekilis.close()
    }

}