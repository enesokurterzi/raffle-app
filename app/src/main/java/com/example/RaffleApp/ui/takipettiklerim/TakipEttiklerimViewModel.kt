package com.example.RaffleApp.ui.takipettiklerim

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.models.Cekilis

class TakipEttiklerimViewModel : ViewModel() {

    val takipEttiklerimCekilis: MutableLiveData<List<Cekilis>> = MutableLiveData()

    fun getTakipEttiklerimCekilis(context: Context) {
        val dbCekilis = DbController(context).db
        takipEttiklerimCekilis.value = dbCekilis.cekilisDao().getByFollowingGroupByPage(true)
        dbCekilis.close()
    }

}