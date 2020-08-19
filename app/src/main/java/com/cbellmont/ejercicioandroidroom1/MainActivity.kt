package com.cbellmont.ejercicioandroidroom1

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPeliculas.movementMethod = ScrollingMovementMethod()

        CoroutineScope(Dispatchers.IO).launch{
            withContext(Dispatchers.IO) {
                val listaPeliculas = App.db.filmDao().getAll()
                mostrarPeliculas(listaPeliculas)
            }
        }
        bAddNewFilms.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                withContext(Dispatchers.IO) {
                    val episodioVII = App.db.filmDao().loadAllByTitle("Episodio VII")
                    episodioVII.forEach {
                        it.name = "El Despertar de la Fuerza"
                        App.db.filmDao().update(it)
                    }

                    val episodioVIII = App.db.filmDao().loadAllByTitle("Episodio VIII")
                    episodioVIII.forEach {
                        it.name = "Los Ultimos Jedi"
                        App.db.filmDao().update(it)
                    }

                    val episodioIX = App.db.filmDao().loadAllByTitle("Episodio IX")
                    episodioIX.forEach {
                        it.name = "El Ascenso de Skywalker"
                        App.db.filmDao().update(it)
                    }
                    mostrarPeliculas(App.db.filmDao().getAll())
                }
            }
        }
    }


    suspend fun mostrarPeliculas(listaPeliculas: List<Film>){
        withContext(Dispatchers.Main) {
            tvPeliculas.text = ""
            listaPeliculas.forEach {
                tvPeliculas.append("${it.name} tiene id = ${it.id}\n")
            }
        }
    }



}