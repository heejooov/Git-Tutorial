package com.example.myapplication;

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemContentBinding
import java.util.*

class ItemAdapter(val context: Context, val itemlist: ArrayList<ServiceModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding).apply {
            itemView.setOnClickListener {
                val intent = Intent(context as MainActivity, dummy_serviDetail::class.java).apply {
                    putExtra("service_key", itemlist.get(adapterPosition).servId)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemlist[position]
        if(item.servNm == null) {
            item.servNm = "아이돌봄 서비스"
            item.servDgst = "취업부모 등의 만 12세 이하 자녀의 양육부담 경감"
        }
        else (holder as ViewHolder).bind(item)
    }


    override fun getItemCount(): Int {
        return itemlist.size
    }


    fun replaceAll(items: ArrayList<ServiceModel>) {
        itemlist.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    fun addAll(items: ArrayList<ServiceModel>) {
        itemlist.apply {
            addAll(items)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ItemContentBinding

        constructor(binding: ItemContentBinding) : this(binding.root) {
            Log.d("ViewHolder", " create")
            this.binding = binding
        }

        fun bind(sm: ServiceModel) {
            binding.setVariable(BR.listContent, sm)
        }

    }

}