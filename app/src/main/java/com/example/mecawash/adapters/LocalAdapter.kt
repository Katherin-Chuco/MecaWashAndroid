package com.example.mecawash.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mecawash.R
import com.example.mecawash.models.Local
import kotlinx.android.synthetic.main.item_local.view.*

class LocalAdapter(var locals: ArrayList<Local> ) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_local, parent, false))
    }

    override fun getItemCount(): Int {
        return locals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateFrom(locals[position])
    }

    class ViewHolder(localView: View) : RecyclerView.ViewHolder(localView) {

        val addressTextView = localView.addressTextView
        val PunctuationTextView = localView.punctuationTextView
        val articleLayout = localView.articleLocal

        fun updateFrom(local: Local) {

            addressTextView.text = local.Address
            PunctuationTextView.text = local.Punctuation.toString()

            articleLayout.setOnClickListener { view ->
                val context = view.context
                context.startActivity(Intent(context, this::class.java).putExtras(local.toBundle()))
            }
        }
    }


}