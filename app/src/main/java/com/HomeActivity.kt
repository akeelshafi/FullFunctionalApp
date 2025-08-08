package com

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akeel.fullfunctionalapp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = intent.getStringExtra(SignUPActivity.KEY1)
        val email = intent.getStringExtra(SignUPActivity.KEY2)


        val welcomeTxt = findViewById<TextView>(R.id.welcomeTv)
        val mailTxt = findViewById<TextView>(R.id.emailTv)
        val userName = findViewById<TextView>(R.id.nameTv)

        welcomeTxt.text ="Welcome $name"
        mailTxt.text ="Email: $email"
        userName.text="Name: $name"


    }
}