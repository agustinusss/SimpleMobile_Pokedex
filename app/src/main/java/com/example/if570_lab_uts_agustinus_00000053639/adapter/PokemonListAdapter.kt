package com.example.if570_lab_uts_agustinus_00000053639.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.if570_lab_uts_agustinus_00000053639.R
import com.example.if570_lab_uts_agustinus_00000053639.fragments.DetailFragment
import com.example.if570_lab_uts_agustinus_00000053639.model.Pokemon
import com.squareup.picasso.Picasso

class PokemonListAdapter(internal var context: Context, internal var pokemonList: List<Pokemon>) : RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {

    private val fragmentController = MyFragmentController(context as AppCompatActivity, R.id.fragment_detail_xml)

    override fun getItemCount(): Int {
        return pokemonList.size
    }
    // List menggunakan Recycler View dan berfungsi
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = itemView.findViewById(R.id.card_view)
        val imgpokemon: ImageView = itemView.findViewById(R.id.pokemon_image)
        val txtpokemon: TextView = itemView.findViewById(R.id.pokemon_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtpokemon.text = pokemonList[position].name
        val pokemon = pokemonList[position]
        val imageUrl = pokemon.img

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.iconhome)
            .into(holder.imgpokemon)

        holder.txtpokemon.text = pokemon.name

        holder.cardView.setOnClickListener {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable("pokemon", pokemonList[position])
            fragment.arguments = bundle

            // Mengakses tampilan fragment_detail_xml dan mengubah visibilitasnya
            val detailFragmentContainer = (context as AppCompatActivity).findViewById<FrameLayout>(R.id.fragment_detail_xml)
            detailFragmentContainer.visibility = View.VISIBLE

            // Tampilkan DetailFragment dalam FrameLayout
            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_detail_xml, fragment)
            transaction.addToBackStack(null)  // Tambahkan ke tumpukan kembali (back stack)
            transaction.commit()
        }

    }
}

