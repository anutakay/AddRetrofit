package com.issart.addretrofit.presentation.languages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.issart.addretrofit.entities.LanguagesEntity
import com.issart.addretrofit.R
import javax.inject.Inject

class LanguagesRecyclerViewAdapter
@Inject constructor() : RecyclerView.Adapter<LanguagesRecyclerViewAdapter.ViewHolder>() {

    var outputPort: InteractionListener? = null

    var values: List<LanguagesEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_language, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = values.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.input.text = String.format("%s -> %s", item.input, item.output)

        holder.itemView.setOnClickListener {
            outputPort?.onSelected(item)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val input: TextView = view.findViewById(R.id.text)
    }

    interface InteractionListener {
        fun onSelected(lang: LanguagesEntity)
    }
}