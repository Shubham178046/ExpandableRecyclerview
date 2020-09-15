package com.example.expandablerecyclerview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expandablerecyclerview.Adapter.ChildAdapter
import com.example.expandablerecyclerview.Adapter.ChildParentModel
import com.example.expandablerecyclerview.Adapter.ParentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var parentAdapter: ParentAdapter? = null
    var parentModel = ArrayList<ChildParentModel>()
    var dataModel =  ArrayList<DataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0 until 3) {
            parentModel.add(ChildParentModel(i, ArrayList<ParentModel>()))
        }
        recyclerview_list.layoutManager = LinearLayoutManager(this)
        parentAdapter = ParentAdapter(applicationContext, parentModel,dataModel)
        recyclerview_list.adapter = parentAdapter
    }

    fun hideKeyboard()
    {

    }
}