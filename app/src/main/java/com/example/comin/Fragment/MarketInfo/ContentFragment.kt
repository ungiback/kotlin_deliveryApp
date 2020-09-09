package com.example.comin.Fragment.MarketInfo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.comin.R
import kotlinx.android.synthetic.main.fragment_content.view.*

/**
 * A simple [Fragment] subclass.
 */
class ContentFragment : Fragment() {

    val list1 = ArrayList<String>()
    val list2 = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_content, container, false)

        list1.add("a")
        list1.add("a")
        list1.add("a")

        list2.add("b")
        list2.add("b")
        list2.add("b")

        val list_adapter = ListAdapter(requireContext(),list1,list2)
        view.content_list.adapter = list_adapter

        return view

    }


}
