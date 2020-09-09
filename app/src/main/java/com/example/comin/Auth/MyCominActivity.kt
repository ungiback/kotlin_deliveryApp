package com.example.comin.Auth

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.comin.MainActivity
import com.example.comin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_my_comin.*
import java.io.ByteArrayOutputStream

class MyCominActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var auth : FirebaseAuth

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_comin)

        auth = FirebaseAuth.getInstance()

        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())

        docRef.get().addOnSuccessListener { documentSnapshot ->
            nickname_area.setText(documentSnapshot.get("nickname").toString())
        }

        logout_button.setOnClickListener {
            auth.signOut()

            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        //이미지 다운로드
        image_download.setOnClickListener {
            val ref = FirebaseStorage.getInstance().getReference("write_button.png")

            ref.downloadUrl
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Glide.with(this)
                            .load(task.result)
                            .into(profile_img)
                    } else {
                        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        //이미지 업로드

        val mountainsRef = FirebaseStorage.getInstance().getReference().child("mountains.jpg")

        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Toast.makeText(this, "업로드 실패", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener {
            Toast.makeText(this, "업로드 성공", Toast.LENGTH_SHORT).show()
        }
    }
}
