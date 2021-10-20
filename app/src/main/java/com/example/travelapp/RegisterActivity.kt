package com.example.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        btnLogRegister.setOnClickListener {
            onBackPressed()
            val intent= Intent(this,login::class.java)
            startActivity(intent)
        }
        //on Click Reg Button
        button.setOnClickListener(){
            signupUser()
        }
    }

    private fun signupUser() {
        if (mail.text.toString().trim().isEmpty()){
            mail.error = "Input Required"
            mail.requestFocus()
            return
        }
//        if (Patterns.EMAIL_ADDRESS.matcher(mail.text.toString().trim()).matches()){
//            mail.error = "Invalid Input"
//            mail.requestFocus()
//            return
//        }
        if (pass.text.toString().isEmpty()){
            pass.error = "Input Required"
            pass.requestFocus()
            return
        }
        if (pass.length() < 6){
            pass.error = "Short Password"
            pass.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(mail.text.toString().trim(),pass.text.toString().trim())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                startActivity(Intent(this,login::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }

}