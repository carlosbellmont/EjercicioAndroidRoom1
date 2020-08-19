package com.cbellmont.ejercicioandroidroom1

import androidx.room.*

@Dao
interface FilmDao {
    @Query("SELECT * FROM Film")
    fun getAll(): List<Film>

    @Query("SELECT * FROM film WHERE id IN (:filmsId)")
    fun loadAllByIds(filmsId: IntArray): List<Film>

    @Query("SELECT * FROM film WHERE name LIKE (:filmTitle)")
    fun loadAllByTitle(filmTitle: String): List<Film>

    @Insert
    fun insert(film: Film)

    @Update
    fun update(film: Film)

    @Insert
    fun insertAll(films: List<Film>)

    @Delete
    fun delete(film: Film)
}
