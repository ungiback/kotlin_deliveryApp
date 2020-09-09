package com.example.comin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.viewpager.widget.ViewPager
import com.example.comin.Auth.LoginActivity
import com.example.comin.Auth.MyCominActivity
import com.example.comin.Zzim.ZzimActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*
import java.util.*

class MainActivity : AppCompatActivity() {

    internal lateinit var viewpager : ViewPager
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth =  FirebaseAuth.getInstance();

        val img = arrayOf(
            R.drawable.ai,
            R.drawable.css,
            R.drawable.html,
            R.drawable.id,
            R.drawable.jpg,
            R.drawable.js,
            R.drawable.mp4,
            R.drawable.pdf,
            R.drawable.php,
            R.drawable.png,
            R.drawable.psd,
            R.drawable.tiff
        )
        val text = arrayOf(
            "ai",
            "css",
            "html",
            "id",
            "jpg",
            "js",
            "mp4",
            "pdf",
            "php",
            "png",
            "psd",
            "tiff"
        )
        val gridviewAdapter = GridviewAdapter(this, img, text)
        gridview.adapter = gridviewAdapter


        zzim_icon.setOnClickListener {
            val intent = Intent(this, ZzimActivity::class.java)
            startActivity(intent)
        }
        gridview.setOnItemClickListener{ parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            val intent = Intent(this,LectureActivity::class.java)
            startActivity(intent)
        }


        viewpager = findViewById(R.id.viewpager) as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter

        my_page.setOnClickListener {
            if(auth.currentUser == null){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this,MyCominActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
