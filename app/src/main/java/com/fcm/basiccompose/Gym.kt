package com.fcm.basiccompose

import com.google.gson.annotations.SerializedName

val listOfGyms = listOf<Gym>(
    Gym(1,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    Gym(2,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    Gym(3,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    Gym(4,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    Gym(5,"UpTown Gym","20 El_Gihad, Mit akaba agouza , Egypt"),
    )
data class Gym(
    val id:Int,
    @SerializedName("gym_name")
    val name:String,
    @SerializedName("gym_location")
    val place:String,
    @SerializedName("is_open")
    val isOPen:Boolean = false,
    var isFavourite:Boolean = false)