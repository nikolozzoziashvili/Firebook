package ge.btu.firebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotCredentials : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var resetPassword: Button
    private lateinit var goBack: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_credentials)
        mAuth = FirebaseAuth.getInstance()
        this.init()
        this.resetListener()
    }
    private fun init(){
        editTextEmail = findViewById(R.id.editTextEmailAddress)
        resetPassword = findViewById(R.id.resetPassword)
        goBack = findViewById(R.id.goBack)
    }
    private fun resetListener(){
        resetPassword.setOnClickListener {
            val email: String = editTextEmail.text.toString()
            if(email.isEmpty()){
                Toast.makeText(this, "E-Mail should not be empty!", Toast.LENGTH_SHORT).show()
            }
            if(!isEmailValid(email)){
                Toast.makeText(this, "Format is not correct!", Toast.LENGTH_SHORT).show()
            }
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Password reset link sent to the email address", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                } else {
                    Toast.makeText(this, "Password reset was unsuccessful. Please try again", Toast.LENGTH_SHORT).show()
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
}