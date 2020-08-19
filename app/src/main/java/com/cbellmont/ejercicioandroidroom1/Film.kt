package com.cbellmont.ejercicioandroidroom1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Film (
     var name : String,
    var intro : String
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
