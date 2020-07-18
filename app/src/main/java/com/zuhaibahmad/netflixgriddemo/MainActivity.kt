package com.zuhaibahmad.netflixgriddemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        vFlixView.apply {
            setItems(FakeDataFactory.getCategorizedContent())
            setOnChildClickedListener { _, thumbnail ->
                displayMessage("Clicked: ${thumbnail.label}")
            }
            setOnChildSelectedListener { _, thumbnail ->
                displayMessage("Selected: ${thumbnail.label}")
            }
        }
    }

    fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}