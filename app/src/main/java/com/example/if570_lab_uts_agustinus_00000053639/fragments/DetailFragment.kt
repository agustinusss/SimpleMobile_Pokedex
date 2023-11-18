package com.example.if570_lab_uts_agustinus_00000053639.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.if570_lab_uts_agustinus_00000053639.R
import com.example.if570_lab_uts_agustinus_00000053639.model.Pokemon
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // Mengambil data Pokemon yang dikirim melalui argumen
        val pokemon = arguments?.getParcelable<Pokemon>("pokemon")

        // Inisialisasi elemen-elemen tampilan dari layout XML
        val backButton = view.findViewById<Button>(R.id.button2)
        val pokemonImage = view.findViewById<ImageView>(R.id.pokemon_image)
        val nameTextView = view.findViewById<TextView>(R.id.name)
        val heightTextView = view.findViewById<TextView>(R.id.height)
        val weightTextView = view.findViewById<TextView>(R.id.weight)
        val weaknessesTextView = view.findViewById<TextView>(R.id.weaknesses)

        // Tampilkan data Pokemon sesuai dengan data yang diterima
        nameTextView.text = pokemon?.name
        heightTextView.text = "Height: ${pokemon?.height}"
        weightTextView.text = "Weight: ${pokemon?.weight}"
        weaknessesTextView.text = "Weaknesses: \n${pokemon?.weaknesses}"

        Picasso.get()
            .load(pokemon?.img)
            .placeholder(R.drawable.pikafamilylogo)
            .into(pokemonImage)

        // Tombol back dari detail
        backButton.setOnClickListener {
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager2)
            viewPager.currentItem = 0

            val detailFragmentContainer = requireActivity().findViewById<FrameLayout>(R.id.fragment_detail_xml)
            detailFragmentContainer.visibility = View.GONE
        }

        return view
    }
}