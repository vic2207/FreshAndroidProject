package com.example.freshandroid

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : Activity() {
    private lateinit var inputWork: EditText
    private lateinit var layoutRecords: LinearLayout
    private val recordsList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val title = TextView(this).apply {
            text = "OK STS TOUŽIM - Облік праці"
            textSize = 20f
            setPadding(0, 0, 0, 24)
        }
        rootLayout.addView(title)

        inputWork = EditText(this).apply {
            hint = "Введіть опис виконаної праці..."
            setPadding(16, 16, 16, 16)
        }
        rootLayout.addView(inputWork)

        val btnSave = Button(this).apply {
            text = "Зберегти запис"
            setPadding(0, 24, 0, 24)
            setOnClickListener {
                val text = inputWork.text.toString()
                if (text.isNotBlank()) {
                    val currentDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date())
                    val record = "$currentDate: $text"
                    recordsList.add(0, record)
                    updateRecordsView()
                    inputWork.text.clear()
                }
            }
        }
        rootLayout.addView(btnSave)

        val scrollView = ScrollView(this).apply {
            setPadding(0, 24, 0, 0)
        }

        layoutRecords = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        scrollView.addView(layoutRecords)
        rootLayout.addView(scrollView)

        setContentView(rootLayout)
    }

    private fun updateRecordsView() {
        layoutRecords.removeAllViews()
        for (rec in recordsList) {
            val tv = TextView(this).apply {
                text = rec
                textSize = 16f
                setPadding(0, 12, 0, 12)
            }
            layoutRecords.addView(tv)
        }
    }
}
// test
