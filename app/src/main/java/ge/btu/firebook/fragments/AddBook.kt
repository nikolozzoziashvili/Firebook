package ge.btu.firebook.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ge.btu.firebook.R


class AddBook : Fragment(R.layout.fragment_add_book){
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var publishButton: Button
    private lateinit var saveButton: Button
    private lateinit var editTextBookName: EditText
    private lateinit var editTextCategory: EditText
    private lateinit var editTextAuthors: EditText
    private lateinit var editTextDescription: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        publishButton = view.findViewById(R.id.publish)
        saveButton = view.findViewById(R.id.save)
        editTextBookName = view.findViewById(R.id.editTextBookName)
        editTextCategory = view.findViewById(R.id.editTextCategory)
        editTextAuthors = view.findViewById(R.id.editTextAuthors)
        editTextDescription = view.findViewById(R.id.editTextDescription)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("book")
        this.buttonListener()

    }
    private fun buttonListener(){
        publishButton.setOnClickListener {
            val name = editTextBookName.text.toString()
            val authors = editTextAuthors.text.toString()
            val category = editTextCategory.text.toString()
            val description = editTextDescription.text.toString()
            if (name.isEmpty() || authors.isEmpty() || category.isEmpty() || description.isEmpty()){
                Toast.makeText(requireActivity(), "Forms should not be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            bookInfo(name, category, authors, description)
        }
    }

    private fun bookInfo(name: String, category: String, authors: String, description: String) {
        val book = bookInfo(name, category, authors, description)
        database.child(name).setValue(book).addOnCompleteListener{
            task ->
            if (task.isSuccessful){
                Toast.makeText(requireActivity(), "Successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Not Successful!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}