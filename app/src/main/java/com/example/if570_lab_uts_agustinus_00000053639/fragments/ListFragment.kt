package com.example.if570_lab_uts_agustinus_00000053639.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.if570_lab_uts_agustinus_00000053639.R
import com.example.if570_lab_uts_agustinus_00000053639.adapter.PokemonListAdapter
import com.example.if570_lab_uts_agustinus_00000053639.model.Pokemon
import com.example.if570_lab_uts_agustinus_00000053639.model.Pokestart
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ListFragment : Fragment() {
    // Data list diambil dari API menggunakan Retrofit dan Moshi (bukan hardcode)
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val moshiConverter = MoshiConverterFactory.create(moshi)

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
            .addConverterFactory(moshiConverter)
            .build()
    }

    private val PokemonApiService by lazy{
        retrofit.create(com.example.if570_lab_uts_agustinus_00000053639.api.PokemonApiService::class.java)
    }

    private val pokemonList = mutableListOf<Pokemon>()
    private lateinit var recyclerView: RecyclerView

    private fun getNamepokemon() {
        Log.i("ListFragment", "Fetching Pokemon data...")
        val call = PokemonApiService.getPokemon()
        call.enqueue(object : Callback<Pokestart> {
            override fun onFailure(call: Call<Pokestart>, t: Throwable) {
                Log.e("HomeActivity", "Failed to get response", t)
            }

            override fun onResponse(call: Call<Pokestart>, response: Response<Pokestart>) {
                if (response.isSuccessful) {
                    Log.i("ListFragment", "Response successful")
                    val responseBody = response.body()
                    responseBody?.pokemon?.forEach { pokemon ->
                        Log.i("ListFragment", pokemon.toString())
                        // Tambahkan data dari respon API ke dalam daftar Pokemon
                        pokemonList.addAll(responseBody?.pokemon!!)

                        // Setel daftar Pokemon ke adapter RecyclerView setelah mendapatkan respon API
                        val adapter = PokemonListAdapter(requireContext(), pokemonList)
                        recyclerView.adapter = adapter
                    }
                } else {
                    Log.e("HomeActivity", "Failed to get response\n" +
                            response.errorBody()?.string().orEmpty())
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        getNamepokemon()

        return view
    }
}