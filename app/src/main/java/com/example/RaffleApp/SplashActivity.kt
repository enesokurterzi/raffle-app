package com.example.RaffleApp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.RaffleApp.configs.DbController
import com.example.RaffleApp.databinding.ActivitySplashBinding
import com.example.RaffleApp.models.Cekilis
import com.example.RaffleApp.services.WhoWonService

class SplashActivity : AppCompatActivity() {

    val sharedPrefs by lazy {
        getSharedPreferences(
            "${R.string.app_name}_sharedPreferences",
            Context.MODE_PRIVATE)
    }

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dataAction = checkTime()
        if (dataAction) {
            view.postDelayed({

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },10000)

        } else {
            view.postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },3000)
        }

    }

    private fun checkTime(): Boolean {
        val threeHours = (3 * 60 * 60 * 1000).toLong()
        val currentTime = System.currentTimeMillis()
        val lastFetchDate = sharedPrefs.getLong("lastFetchDate", currentTime - threeHours)
        val elapsedTime = currentTime - lastFetchDate

        return if (elapsedTime >= threeHours) {
            val previousFollowing = getPreviousFollowing()
            deleteDB()

            Thread{
                WhoWonService(this, previousFollowing).getAndSetAll()
            }.start()

            sharedPrefs.edit().putLong("lastFetchDate", currentTime).apply()
            true
        } else {
            sharedPrefs.edit().putLong("lastFetchDate", lastFetchDate).apply()
            false
        }
    }

    private fun getPreviousFollowing(): List<Cekilis> {
        val dbCekilis = DbController(this).db
        val previousFollowing = dbCekilis.cekilisDao().getByFollowing(true)
        dbCekilis.close()
        return previousFollowing
    }

    private fun deleteDB() {
        val dbCekilis = DbController(this).db
        dbCekilis.cekilisDao().deleteAllData()
        dbCekilis.close()
    }


}