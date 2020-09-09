package com.example.comin.Fragment.MarketInfo


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.comin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_review.view.*

/**
 * A simple [Fragment] subclass.
 */
class ReviewFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    private val text_array = ArrayList<String>()
    private val nickname_array = ArrayList<String>()
    private val rating_array = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()

        val view : View = inflater.inflate(R.layout.fragment_review,container,false)

        val review_adapter = ReviewListAdapter(requireContext(),nickname_array,text_array,rating_array)
        view.listview_review.adapter = review_adapter

        db.collection("reviews")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    rating_array.add(document.get("rating") as String)
                    text_array.add(document.get("text") as String)
                    nickname_array.add(document.get("writer") as String)
                }
                review_adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->

            }

        view.setOnClickListener {
            if (auth.currentUser == null ){
                Toast.makeText(requireContext(),"회원가입 or 로그인 해주세요",Toast.LENGTH_SHORT).show()

            }else {
                val intent = Intent(requireContext(),WriteActivity::class.java)
                startActivity(intent)
            }
        }
        return view
    }


}
