package com.example.mecawash.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mecawash.R
import com.example.mecawash.activities.ProviderClientActivity
import com.example.mecawash.models.Local
import kotlinx.android.synthetic.main.item_local_client.view.*

class LocalClientApdater(var locals: ArrayList<Local> ): RecyclerView.Adapter<LocalClientApdater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_local_client, parent, false))
    }

    override fun getItemCount(): Int {
        return locals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateFrom(locals[position])
    }

    class ViewHolder(localView: View) : RecyclerView.ViewHolder(localView) {

        val addressTextView = localView.addressTextView
        val PunctuationRatingBar = localView.punctuationRatingBar
        val articleLayout = localView.localClient

        fun updateFrom(local: Local) {

            addressTextView.text = local.Address
            PunctuationRatingBar.rating = local.Punctuation.toFloat()

            articleLayout.setOnClickListener { view ->
                val context = view.context
                context.startActivity(Intent(context, ProviderClientActivity::class.java).putExtras(local.toBundle()))
            }
        }
    }
}