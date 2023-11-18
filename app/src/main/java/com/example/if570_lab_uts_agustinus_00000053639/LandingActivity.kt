package com.example.if570_lab_uts_agustinus_00000053639

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LandingActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var nameTextInputLayout: TextInputLayout
    private lateinit var nameEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        loginButton = findViewById(R.id.loginButton)
        nameTextInputLayout = findViewById(R.id.nameTextInputLayout)
        nameEditText = findViewById(R.id.nameEditText)

        // gradien linear NameAPK
        val deskripsiTextViewNameAPK = findViewById<TextView>(R.id.textView2)
        val deskripsiTextNameAPK = getString(R.string.NameAPK)

        val warnaPertamaNameAPK = resources.getColor(R.color.red)
        val warnaKeduaNameAPK = resources.getColor(R.color.orange)

        val spannableStringNameAPK = SpannableString(deskripsiTextNameAPK)
        val linearGradientNameAPK = LinearGradient(0f, 0f, 0f, deskripsiTextViewNameAPK.textSize, warnaPertamaNameAPK, warnaKeduaNameAPK, Shader.TileMode.CLAMP)

        val spanNameAPK = object : CharacterStyle() {
            override fun updateDrawState(tp: TextPaint) {
                tp.shader = linearGradientNameAPK
            }
        }

        spannableStringNameAPK.setSpan(spanNameAPK, 0, deskripsiTextNameAPK.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        deskripsiTextViewNameAPK.text = spannableStringNameAPK

        // gradien linear Deskripsi
        val deskripsiTextViewDeskripsi = findViewById<TextView>(R.id.textView)
        val deskripsiTextDeskripsi = getString(R.string.Deskripsi)

        val warnaPertamaDeskripsi = resources.getColor(R.color.orange)
        val warnaKeduaDeskripsi = resources.getColor(R.color.purple)

        val spannableStringDeskripsi = SpannableString(deskripsiTextDeskripsi)
        val linearGradientDeskripsi = LinearGradient(0f, 0f, 0f, deskripsiTextViewDeskripsi.textSize, warnaPertamaDeskripsi, warnaKeduaDeskripsi, Shader.TileMode.CLAMP)

        val spanDeskripsi = object : CharacterStyle() {
            override fun updateDrawState(tp: TextPaint) {
                tp.shader = linearGradientDeskripsi
            }
        }

        spannableStringDeskripsi.setSpan(spanDeskripsi, 0, deskripsiTextDeskripsi.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        deskripsiTextViewDeskripsi.text = spannableStringDeskripsi

        //Validasi Input (Tombol tidak bisa ditekan jika input kosong)
        loginButton.setOnClickListener {
            val name = nameEditText.text.toString()
            if (name.isNotEmpty()) {
                // Halaman berpindah ketika input tidak kosong dan tombol ditekan menggunakan Intent
                val intent = Intent(this, HomeActivity::class.java)
                // Mengirim nama pengguna ke HomeActivity
                intent.putExtra("user_name", name)
                startActivity(intent)
            } else {
                // Menampilkan pesan error menggunakan Toast jika input kosong
                Toast.makeText(this, getString(R.string.error_name_empty), Toast.LENGTH_SHORT).show()
            }

        }
    }
}