package com.jashanpreet.studentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentId       = findViewById<EditText>(R.id.student_id)
        val studentName     = findViewById<EditText>(R.id.student_name)
        val studentAge      = findViewById<EditText>(R.id.student_age)
        val studentAdd      = findViewById<EditText>(R.id.student_add)
        val studentEmail    = findViewById<EditText>(R.id.student_email)
        val button          = findViewById<Button>(R.id.add_btn)

        button.setOnClickListener() {

            val sId = studentId.text.toString()
            val sName = studentName.text.toString()
            val sAge = studentAge.text.toString()
            val sAdd = studentAdd.text.toString()
            val sEmail = studentEmail.text.toString()

            // input validation and firebase
            if (sId != "" && sName != "" && sAge != "" && sAdd != "" && sEmail != "" ){

                // Write a message to the database
                val database = Firebase.database
                val myRef = database.getReference("students")

                val st = Student(sName, sAge, sAdd, sEmail)
                myRef.child(sId).setValue(st)
//                myRef.setValue("Hello, World!")

                Toast.makeText(this, "record Added Successfully.", Toast.LENGTH_LONG).show()

            }else {
                Toast.makeText(this, "Please check your form!", Toast.LENGTH_LONG).show()
            }
        }
    }
}

data class Student(
    val name: String,
    val age: String,
    val address: String,
    val email: String
)