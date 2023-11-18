package com.example.if570_lab_uts_agustinus_00000053639.model

import com.squareup.moshi.Json


data class Pokestart (

  @field:Json(name = "pokemon" ) var pokemon : List<Pokemon> = arrayListOf()

)