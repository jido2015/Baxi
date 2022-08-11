package com.olajide.capricon.transactions.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olajide.capricon.databinding.ListItemBinding
import com.olajide.capricon.transactions.data.model.DataX

class ListAdapter(val transactions: List<DataX>) : RecyclerView.Adapter<ListAdapter.ViewHolder>()  {

    inner class ViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind( trnx: DataX) {
            binding.trnxName.text = trnx.transaction_description
            binding.trnxAmt.text = trnx.transaction_amount.toString()
            binding.trnxDate.text = trnx.transaction_date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = transactions[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}