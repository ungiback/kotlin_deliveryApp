package com.example.comin.Fragment.MarketInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.comin.R

class ListAdapter(val contentt : Context , val list_price : ArrayList<String>, val list_name : ArrayList<String>) :BaseAdapter(){
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val view : View = LayoutInflater.from(contentt).inflate(R.layout.listview_item,null)
        return view
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
       return list_name.size
    }

}