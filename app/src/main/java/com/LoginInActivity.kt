package com

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akeel.fullfunctionalapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginInActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signInBtn = findViewById<Button>(R.id.signInBtn)
        val nameEdtTxt = findViewById<EditText>(R.id.nameEdtTxt)
        val emailEdtTxt = findViewById<EditText>(R.id.emailEdtTxt)
        val passwordEdtTxt = findViewById<EditText>(R.id.passwordEdtTxt)

        signInBtn.setOnClickListener {
            val name = nameEdtTxt.text.toString()
            val email = emailEdtTxt.text.toString()
            val password = passwordEdtTxt.text.toString()

            val user = User(name, email, password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(name).setValue(user)
                .addOnSuccessListener {
                    nameEdtTxt.text.clear()
                    emailEdtTxt.text.clear()
                    passwordEdtTxt.text.clear()

                    Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to Register", Toast.LENGTH_SHORT).show()


                }





        }
    }
}