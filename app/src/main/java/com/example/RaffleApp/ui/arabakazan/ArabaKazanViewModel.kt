package com.example.RaffleApp.ui.arabakazan

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.models.Cekilis

class ArabaKazanViewModel : ViewModel() {

    val arabaKazanCekilis: MutableLiveData<List<Cekilis>> = MutableLiveData()

    fun getArabaKazanCekilis(context: Context) {
        val dbCekilis = DbController(context).db
        arabaKazanCekilis.value = dbCekilis.cekilisDao().getByPage("ArabaKazan")
        dbCekilis.close()
    }
}