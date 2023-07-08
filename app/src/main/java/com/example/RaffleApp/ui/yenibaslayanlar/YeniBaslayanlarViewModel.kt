package com.example.RaffleApp.ui.yenibaslayanlar

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.models.Cekilis

class YeniBaslayanlarViewModel : ViewModel() {

    val yeniBaslayanlarCekilis: MutableLiveData<List<Cekilis>> = MutableLiveData()

    fun getYeniBaslayanlarCekilis(context: Context) {
        val dbCekilis = DbController(context).db
        yeniBaslayanlarCekilis.value = dbCekilis.cekilisDao().getByPage("YeniBaslayanlar")
        dbCekilis.close()
    }

}