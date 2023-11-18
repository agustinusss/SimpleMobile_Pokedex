package com.example.if570_lab_uts_agustinus_00000053639.api

import com.example.if570_lab_uts_agustinus_00000053639.model.Pokestart
import retrofit2.Call
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokedex.json")
    fun getPokemon(
    ): Call<Pokestart>
}


