package com.example.lab2_exercise_s74720

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNama = findViewById<EditText>(R.id.editNama)
        val editTarikh = findViewById<EditText>(R.id.editTarikh)
        val btnAnalisis = findViewById<Button>(R.id.btnAnalisis)

        btnAnalisis.setOnClickListener {
            // .trim() removes accidental leading/trailing spaces before checking isEmpty()
            val nama = editNama.text.toString().trim()
            val tarikh = editTarikh.text.toString().trim()

            // Practical constraint: Always validate data before processing or sending it over
            if (nama.isEmpty() || tarikh.isEmpty()) {
                Toast.makeText(this, "Sila lengkapkan semua maklumat", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Explicit Intent to Screen 2
            val intent = Intent(this, SecActivity_Exercise_S74720::class.java).apply {
                putExtra("NAMA", nama)
                putExtra("TARIKH", tarikh)
            }
            startActivity(intent)
        }
    }
}