package ge.btu.firebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class PasswordChange : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var editTextCurrentPassword: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPasswordConfirmation: EditText
    private lateinit var button: Button
    private lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)
        mAuth = FirebaseAuth.getInstance()
        user = FirebaseAuth.getInstance().currentUser!!
        this.init()
        this.buttonListener()
    }
    private fun init(){
        editTextCurrentPassword = findViewById(R.id.editTextCurrentPassword)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPasswordConfirmation = findViewById(R.id.editTextPasswordConfirmation)
        button = findViewById(R.id.button)
    }
    private fun buttonListener(){
        button.setOnClickListener {
            val email: String = user.email!!
            val currentPassword: String = editTextCurrentPassword.text.toString()
            val newPassword: String = editTextPassword.text.toString()
            val newPasswordConfirmation: String = editTextPasswordConfirmation.text.toString()
            val credentials = EmailAuthProvider.getCredential(email, currentPassword)
            if(newPassword == newPasswordConfirmation){
                user.reauthenticate(credentials).addOnCompleteListener {
                    task ->
                    if (task.isSuccessful){
                        user!!.updatePassword(newPassword).addOnCompleteListener {
                            task ->
                            if (task.isSuccessful){
                                Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Not Successful!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}