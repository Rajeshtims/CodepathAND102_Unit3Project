package com.example.unit3_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unit3_project.R.id
import com.example.unit3_project.R.id.my_toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportActionBar?.setDisplayShowTitleEnabled(true);
        setSupportActionBar(findViewById(my_toolbar))

        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(id.content, MovieFragment(), null).commit()
    }


}