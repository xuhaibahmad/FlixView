package com.zuhaibahmad.netflixgriddemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        vFlixView.setOnChildClickedListener{ index, thumbnail ->
            Snackbar.make(vContainer, "Clicked: ${thumbnail.label}", Snackbar.LENGTH_LONG).show()
        }
        vFlixView.setOnChildSelectedListener{ index, thumbnail ->
            Snackbar.make(vContainer, "Selected: ${thumbnail.label}", Snackbar.LENGTH_SHORT).show()
        }
    }
}