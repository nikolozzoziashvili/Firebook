package ge.btu.firebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Registration : AppCompatActivity() {
    private lateinit var editTextEmailAddress: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPasswordConfirmation: EditText
    private lateinit var registration: Button
    private lateinit var goBack: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mAuth = FirebaseAuth.getInstance()
        this.init()
        this.registerListener()
    }
    private fun init(){
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPasswordConfirmation = findViewById(R.id.editTextPasswordConfirmation)
        registration = findViewById(R.id.registration)
        goBack = findViewById(R.id.goBack)
    }
    private fun registerListener(){
        registration.setOnClickListener {
            val email: String = editTextEmailAddress.text.toString()
            val password: String = editTextPassword.text.toString()
            val passwordConfirmation: String = editTextPasswordConfirmation.text.toString()
            if (email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()){
                Toast.makeText(this, "Forms should not be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isEmailValid(email)){
                Toast.makeText(this, "Email format is incorrect!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != passwordConfirmation){
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isPasswordValid(password)){
                Toast.makeText(this, "Password does not meet requirements", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                task ->
                if (task.isSuccessful){
                    goToContent()
                } else {
                    Toast.makeText(this, "Registration was unsuccessful. Please try again", Toast.LENGTH_SHORT).show()
                }
            }
        }
        goBack.setOnClickListener {
            onBackPressed()
        }
    }
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isPasswordValid(password: String): Boolean{
        val reg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{9,}".toRegex()
        return reg.matches(password)
    }
    private fun goToContent(){
        startActivity(Intent(this, Content::class.java))
        finish()
    }
}