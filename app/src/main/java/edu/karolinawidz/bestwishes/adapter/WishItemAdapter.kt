package edu.karolinawidz.bestwishes.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.model.Wish

private const val TAG = "WishItemAdapter"

class WishItemAdapter(private val context: Context, private val data: List<Wish>) :
    RecyclerView.Adapter<WishItemAdapter.ItemViewHolder>() {

    var selectedItemPosition = -1

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButton: RadioButton = itemView.findViewById(R.id.wish_radio_button)
        val textView: TextView = itemView.findViewById(R.id.wish_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.wish_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.radioButton.isChecked = selectedItemPosition == position
        holder.radioButton.setOnClickListener {
            val previousPosition = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedItemPosition)

        }
    }

    override fun getItemCount() = data.size

    fun getWishFromPosition(): Int? {
        return try {
            Log.i(TAG, "Wish selected")
            data[selectedItemPosition].stringResourceId
        } catch (e: IndexOutOfBoundsException) {
            Log.e(TAG, "No wish selected")
            null
        }
    }

}