package com.example.expandablerecyclerview.Adapter

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.DataModel
import com.example.expandablerecyclerview.ParentModel
import com.example.expandablerecyclerview.R
import kotlinx.android.synthetic.main.item_child_layout.view.*


class ChildAdapter(
    var context: Context,
    var adapterPos: Int,
    var parentModel: ArrayList<ChildParentModel>
) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_child_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("Size", "getItemCount: " + parentModel.get(adapterPos).childModel.size)
        return parentModel.get(adapterPos).childModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Log.d("Position", "onBindViewHolder: " + position)
            for (i in 0 until parentModel.size) {
                for (k in 0 until parentModel.get(adapterPos).childModel!!.size)
                    Log.d(
                        "Check",
                        "onCreate$i $k: " + parentModel.get(adapterPos).childModel!!.get(k).email + " " + parentModel.get(
                            adapterPos
                        ).childModel!!.get(k).name + " " + parentModel.get(adapterPos).childModel!!.get(
                            k
                        ).number
                    )
            }
        }

        holder.itemView.edtName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var parentModels : ParentModel=parentModel.get(adapterPos).childModel.get(position)
                parentModels!!.name = p0.toString()
                parentModel.get(adapterPos).childModel!!.set(position,
                    parentModels!!
                )
            }
        })
        holder.itemView.edtcontact.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var parentModels : ParentModel=parentModel.get(adapterPos).childModel.get(position)
                parentModels!!.number = p0.toString()
                parentModel.get(adapterPos).childModel!!.set(position,
                    parentModels!!
                )
            }

        })
        holder.itemView.edtemail.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var parentModels : ParentModel=parentModel.get(adapterPos).childModel.get(position)
                parentModels!!.email = p0.toString()
                parentModel.get(adapterPos).childModel!!.set(position,
                    parentModels!!
                )
            }

        })

       /* if(parentModel.get(adapterPos).childModel.isNotEmpty())
        {
            parentModel.get(adapterPos).childModel!!.set(position,
                parentModels!!
            )
        }*/


        holder.itemView.submitBtn.setOnClickListener { v->
            Log.d("ChildAdapterPosition", "onBindViewHolder: " + holder.adapterPosition)
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow( holder.itemView.submitBtn.getWindowToken(), 0);

            parentModel.get(adapterPos).childModel!!.set(position,
                ParentModel(
                    holder.itemView.edtName.text.toString(),
                    holder.itemView.edtcontact.text.toString(),
                    holder.itemView.edtemail.text.toString()
                )
            )
        }

        if (parentModel.get(adapterPos).childModel.size > 0) {
            holder.itemView.edtName.setText(parentModel.get(adapterPos).childModel.get(position).name)
            holder.itemView.edtcontact.setText(parentModel.get(adapterPos).childModel.get(position).number)
            holder.itemView.edtemail.setText(parentModel.get(adapterPos).childModel.get(position).email)
            Log.d("CheckCode", "onBindViewHolder: " + (position))
        }
    }

    interface OnClick {
        fun onClick()
    }

}