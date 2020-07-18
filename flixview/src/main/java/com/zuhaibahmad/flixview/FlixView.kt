package com.zuhaibahmad.flixview

import android.content.Context
import android.util.Log
import android.widget.Toast

class FlixView {
    fun testIntegration(context: Context) {
        Log.i("FlixView", "Flix View works!");
        Toast.makeText(context, "Flix View works!", Toast.LENGTH_LONG).show();
    }
}