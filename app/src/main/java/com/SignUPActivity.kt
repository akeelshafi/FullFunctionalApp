package com

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akeel.fullfunctionalapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUPActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    companion object{
        const val KEY1 = "com.akeel.fullfunctionalapp.SignInActivity.name"
        const val KEY2 = "com.akeel.fullfunctionalapp.SignInActivity.email"
        const val KEY3 = "com.akeel.fullfunctionalapp.SignInActivity.password"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_upactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        val nameEdtTxt = findViewById<EditText>(R.id.SignInNameEdtTxt)
        val passwordEdtTxt = findViewById<EditText>(R.id.SignUppasswordEdtTxt)
        val signUp = findViewById<TextView>(R.id.signUpTv)

        signUpBtn.setOnClickListener {
            val name = nameEdtTxt.text.toString()
            val password = passwordEdtTxt.text.toString()

            if (name.isNotEmpty() && password.isNotEmpty()) {

                readData(name, password)

            } else {
                Toast.makeText(this, "Please Enter Email And Password", Toast.LENGTH_SHORT).show()


            }


        }
        signUp.setOnClickListener {
            val intent = Intent(this, LoginInActivity::class.java)
            startActivity(intent)
        }

    }

    private fun readData(name: String, password: String) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(name).get().addOnSuccessListener {
            if (it.exists()) {
                val nameDB = it.child("name").value
                val emailDB = it.child("email").value
                val passwordDB = it.child("password").value

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(KEY1, nameDB.toString())
                intent.putExtra(KEY2, emailDB.toString())
                intent.putExtra(KEY3, passwordDB.toString())
                startActivity(intent)


            } else {
                Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
            }


        }.addOnFailureListener {
            Toast.makeText(this, "Failed,Error in DateBase", Toast.LENGTH_SHORT).show()

        }

    }
}