package com.example.expandablerecyclerview.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.DataModel
import com.example.expandablerecyclerview.ParentModel
import com.example.expandablerecyclerview.R
import kotlinx.android.synthetic.main.item_child_layout.view.*
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class ParentAdapter(var context : Context , var parentModel : ArrayList<ChildParentModel>,var dataModel :  ArrayList<DataModel>) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
     var childAdapter: ChildAdapter? = null
    var sizeCount : Int = 0
    var positions  = 0
    var viewPool : RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return parentModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.details.layoutManager = LinearLayoutManager(context)

        holder.itemView.add.setOnClickListener {

            parentModel.get(holder.adapterPosition).childModel!!.add(
                ParentModel("","",""
                )
            )
            holder.itemView.details.visibility = View.VISIBLE
            childAdapter = ChildAdapter(context,holder.adapterPosition,parentModel)
            holder.itemView.details.adapter = childAdapter
         //   holder.itemView.details.setRecycledViewPool(viewPool)
            Log.d("childCount", "onBindViewHolder: "+ childAdapter!!.itemCount)
            holder.itemView.details.adapter!!.notifyDataSetChanged()
            if(parentModel.get(holder.adapterPosition).childModel.isNotEmpty())
            {
                holder.itemView.count.setText(parentModel.get(holder.adapterPosition).childModel.size.toString())
            }
            else
            {
                holder.itemView.count.setText("0")
            }
        }
        Log.d("Size", "onBindViewHolder: "+parentModel.size)
        holder.itemView.minus.setOnClickListener {

            if(parentModel.get(holder.adapterPosition).childModel.isNotEmpty())
            {
                Log.d("Sizecountbefore", "onBindViewHolder: "+sizeCount)
                Log.d("Sizecount", "onBindViewHolder: "+sizeCount)
                Log.d("ItemCount", "onBindViewHolder: "+childAdapter!!.itemCount)
                parentModel.get(holder.adapterPosition).childModel.removeAt(parentModel.get(holder.adapterPosition).childModel.size - 1)
               // childAdapter!!.notifyDataSetChanged()
                if(parentModel.get(holder.adapterPosition).childModel.isNotEmpty())
                {
                    holder.itemView.count.setText(parentModel.get(holder.adapterPosition).childModel.size.toString())
                }
                else
                {
                    holder.itemView.count.setText("0")
                }
            }
            holder.itemView.details.adapter!!.notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener {
            Log.d("Position", "onBindViewHolder: "+position)
            Log.d("AdapterItemCount", "onBindViewHolder: "+ childAdapter!!.itemCount)
            if(parentModel.get(holder.adapterPosition).childModel.size > 0)
            {
                for (i in 0 until parentModel.size) {
                    for(k in 0 until parentModel.get(holder.adapterPosition).childModel!!.size)
                        Log.d(
                            "Check",
                            "onCreate$i $k: " + parentModel.get(holder.adapterPosition).childModel!!.get(k).email + " " + parentModel.get(
                                holder.adapterPosition
                            ).childModel!!.get(k).name + " " + parentModel.get(holder.adapterPosition).childModel!!.get(k).number
                        )
                }
            }
        }
    }
    interface OnClick{ fun onClick(holder : ViewHolder, size : Int, flag : Int) }
}