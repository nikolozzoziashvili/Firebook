package ge.btu.firebook.fragments


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ge.btu.firebook.MainActivity
import ge.btu.firebook.PasswordChange
import ge.btu.firebook.R

class ProfileEditor : Fragment(R.layout.fragment_profile_editor){
    private lateinit var passwordChange: Button
    private lateinit var logout: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private lateinit var userEmail: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        passwordChange = view.findViewById(R.id.PasswordChange)
        logout = view.findViewById(R.id.Logout)
        userEmail = view.findViewById(R.id.userEmail)
        user = FirebaseAuth.getInstance().currentUser!!
        userEmail.text = user.email.toString()
        this.buttonListener()
    }
    private fun buttonListener(){
        passwordChange.setOnClickListener {
            startActivity(Intent(requireActivity(), PasswordChange::class.java))
        }
        logout.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(requireActivity(), MainActivity::class.java))

        }
    }
}