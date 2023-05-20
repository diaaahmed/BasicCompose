package com.fcm.basiccompose

val listOfGyms = listOf<Gym>(
    Gym(1,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    Gym(2,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    Gym(3,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    Gym(4,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    Gym(5,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    )
data class Gym(val id:Int,val name:String, val place:String, var isFavourite:Boolean = false)