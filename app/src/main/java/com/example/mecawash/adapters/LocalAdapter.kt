package com.example.mecawash.adapters


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mecawash.R
import com.example.mecawash.activities.LocalActivity
import com.example.mecawash.models.LocalClass
import kotlinx.android.synthetic.main.content_local.view.*

class LocalAdapter(private var locals: List<LocalClass>) : RecyclerView.Adapter<LocalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_local, parent, false))
    }

    override fun getItemCount(): Int {
        return locals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(locals[position])
    }

    class ViewHolder(localView: View) : RecyclerView.ViewHolder(localView) {

        private var lastNameTextView: TextView = itemView.lastNameTextView
        private var context: Context = itemView.context
        private var content = itemView.contentLocal

        fun bindTo(local: LocalClass) {
            lastNameTextView.text = ""
            content.setOnClickListener {
                val intent = Intent(context, LocalActivity::class.java)
                val bundle = Bundle()

                bundle.apply {
                    putString("first_name", "")
                    putString("last_name", "")
                }
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
    }
}