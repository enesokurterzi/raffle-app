package com.example.RaffleApp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.RaffleApp.models.Cekilis

@Dao
interface CekilisDao {

    @Insert
    fun insert(cekilis: Cekilis): Long

    @Query("SELECT * FROM cekilis WHERE page = :page")
    fun getByPage(page: String): List<Cekilis>

    @Update
    fun update(cekilis: Cekilis)

    @Query("DELETE FROM cekilis")
    fun deleteAllData()

    @Query("SELECT * FROM cekilis WHERE following = :following")
    fun getByFollowing(following: Boolean): List<Cekilis>

    @Query("SELECT * FROM cekilis WHERE following = :following GROUP BY title ORDER BY id ASC")
    fun getByFollowingGroupByPage(following: Boolean): List<Cekilis>

    @Query("SELECT * FROM cekilis WHERE title = :title")
    fun getByTitle(title: String): List<Cekilis>

}