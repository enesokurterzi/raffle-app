package com.example.RaffleApp.ui.telefontabletkazan

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.models.Cekilis

class TelefonTabletKazanViewModel : ViewModel() {

    val telefonTabletKazanCekilis: MutableLiveData<List<Cekilis>> = MutableLiveData()

    fun getTelefonTabletKazanCekilis(context: Context) {
        val dbCekilis = DbController(context).db
        telefonTabletKazanCekilis.value = dbCekilis.cekilisDao().getByPage("TelefonTabletKazan")
        dbCekilis.close()
    }

}