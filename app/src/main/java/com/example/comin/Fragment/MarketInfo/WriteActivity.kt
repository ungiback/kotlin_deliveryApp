package com.example.comin.Fragment.MarketInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import com.example.comin.MainActivity
import com.example.comin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_write.*
import kotlinx.android.synthetic.main.fragment_review.*

class WriteActivity : AppCompatActivity() {

    private lateinit var rating_num : String
    private lateinit var nickname: String
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        auth = FirebaseAuth.getInstance()

        rating_area.setOnRatingBarChangeListener { ratingBar, fl, b ->
            rating_num = fl.toString()
        }

        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())

        docRef.get().addOnSuccessListener { documentSnapshot ->
            nickname = documentSnapshot.get("nickname") as String
        }

        writing_button.setOnClickListener {
            val form = hashMapOf(
                "text" to text_input_area.text.toString(),
                "writer" to nickname,
                "rating" to rating_num
            )
            
            db.collection("reviews")
                .add(form)
                .addOnSuccessListener {
                    Toast.makeText(this,"성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                    finish()
                }
                .addOnFailureListener { Toast.makeText(this,"실패", Toast.LENGTH_SHORT).show() }
        }
    }
}
