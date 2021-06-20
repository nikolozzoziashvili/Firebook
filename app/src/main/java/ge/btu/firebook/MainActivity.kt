package ge.btu.firebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var forgotCredentials: Button
    private lateinit var authorization: Button
    private lateinit var registration: Button
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null){
            goToContent()
        }
        this.init()
        this.buttonListener()
    }
    private fun init(){
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        forgotCredentials = findViewById(R.id.forgotCredentials)
        authorization = findViewById(R.id.authorization)
        registration = findViewById(R.id.registration)

    }
    private fun buttonListener(){
        authorization.setOnClickListener {
            val email: String = editTextEmailAddress.text.toString()
            val password: String = editTextPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Forms should not be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                task ->
                if (task.isSuccessful) {
                    goToContent()
                } else {
                    Toast.makeText(this,"Authentication was unsuccessful. Please try again", Toast.LENGTH_SHORT).show()
                }
            }
        }
        forgotCredentials.setOnClickListener{
            goToRecovery()
        }
        registration.setOnClickListener {
            goToRegistation()
        }
    }
    private fun goToContent(){
        startActivity(Intent(this, Content::class.java))
        finish()
    }
    private fun goToRegistation(){
        startActivity(Intent(this, Registration::class.java))
    }
    private fun goToRecovery(){
        startActivity(Intent(this, ForgotCredentials::class.java))
    }
}