package com.example.myapplication.ui.medstep

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable

class CustomArrayAdapter(
    context: Context,
    private val resource: Int,
    private val items: List<String>
) : ArrayAdapter<String>(context, resource, items), Filterable {

    private var filteredItems: List<String> = items

    override fun getCount(): Int {
        return filteredItems.size
    }

    override fun getItem(position: Int): String? {
        return filteredItems[position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val filteredList = if (constraint.isNullOrEmpty()) {
                    items // Mostrar todos los elementos si no hay filtro
                } else {
                    items.filter {
                        it.contains(constraint, ignoreCase = true)
                    }
                }
                results.values = filteredList
                results.count = filteredList.size
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredItems = if (results?.values == null) {
                    emptyList()
                } else {
                    results.values as List<String>
                }
                notifyDataSetChanged()
            }
        }
    }
}

