package com.tm00nlight.chitasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

private const val KEY_COUNTER = "ClickCounter"

class MainActivity : AppCompatActivity() {
    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        counter = savedInstanceState?.getInt(KEY_COUNTER, 0)?:0
        textView.text = getString(R.string.text_clicked, counter)

        button.setOnClickListener {
            counter++
            textView.text = getString(R.string.text_clicked, counter)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_COUNTER, counter)
    }
}