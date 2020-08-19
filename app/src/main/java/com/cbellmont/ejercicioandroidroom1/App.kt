package com.cbellmont.ejercicioandroidroom1

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase




class App : Application() {


    companion object {
        lateinit var db : AppDatabase
    }
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
            .addCallback(getCallback())
            .build()
    }

    fun getCallback(): RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                val films : List<Film> = listOf(
                    Film("La Amenaza Fantasma", "aaaa"),
                    Film("El Ataque de los Clones", "aaaa"),
                    Film("La Venganza de los Sith", "aaaa"),
                    Film("Una Nueva Esperanza", "aaaa"),
                    Film("El Imperio Contraataca", "aaaa"),
                    Film("El Retorno del Jedi", "aaaa")
                )
                App.db.filmDao().insertAll(films)
                App.db.filmDao().insert(Film("Episodio VII", "bbbb"))
                App.db.filmDao().insert(Film("Episodio VIII", "bbbb"))
                App.db.filmDao().insert(Film("Episodio IX", "bbbb"))
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
            }
        }
    }
}