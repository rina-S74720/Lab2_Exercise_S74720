
package com.example.lab2_exercise_s74720

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class SecActivity_Exercise_S74720 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec_exercise_s74720)

        val textNama = findViewById<TextView>(R.id.textNama)
        val textTarikh = findViewById<TextView>(R.id.textTarikh)

        // Retrieve data from Intent
        val nama = intent.getStringExtra("NAMA") ?: ""
        val tarikhString = intent.getStringExtra("TARIKH") ?: ""

        // ==========================================
        // PART A: String Analysis
        // ==========================================
        // Remove spaces for accurate character counting and repetition checks
        val namaTanpaSpace = nama.replace(" ", "")

        val charCount = namaTanpaSpace.length
        val vowelCount = nama.filter { it.lowercaseChar() in "aeiou" }.count()

        // Group characters, filter for those appearing >1 time, extract keys, and format to string
        val repeatedChars = namaTanpaSpace.lowercase()
            .groupBy { it }
            .filter { it.value.size > 1 }
            .keys
            .joinToString(", ")

        val hasilNama = """
            Nama: $nama
            Jumlah abjad: $charCount
            Bilangan vokal: $vowelCount
            Abjad berulang: $repeatedChars
        """.trimIndent()

        textNama.text = hasilNama

        // ==========================================
        // PART B: Date Analysis
        // ==========================================
        var hasilTarikh = ""

        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val birthDate = LocalDate.parse(tarikhString, formatter)
            val currentDate = LocalDate.now()

            val age = Period.between(birthDate, currentDate).years

            // Map the standard 1-7 day integer to Malay string equivalents
            val dayMap = mapOf(
                1 to "Isnin", 2 to "Selasa", 3 to "Rabu",
                4 to "Khamis", 5 to "Jumaat", 6 to "Sabtu", 7 to "Ahad"
            )
            val dayOfWeek = dayMap[birthDate.dayOfWeek.value] ?: ""

            hasilTarikh = """
                Umur anda: $age tahun
                Dilahirkan pada: $dayOfWeek
            """.trimIndent()

        } catch (e: Exception) {
            hasilTarikh = "Ralat: Format tarikh tidak sah. Sila guna YYYY-MM-DD."
        }

        textTarikh.text = hasilTarikh
    }
}
