package edu.karolinawidz.bestwishes.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.model.Picture

const val TAG = "PictureItemAdapter"

class PictureItemAdapter(private val context: Context, private val data: List<Picture>) :
    RecyclerView.Adapter<PictureItemAdapter.ItemViewHolder>() {
    var selectedItemPosition = -1

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val radioButton: RadioButton = view.findViewById(R.id.picture_radio_button)
        val textView: TextView = view.findViewById(R.id.picture_text)
        val imageView: ImageView = view.findViewById(R.id.picture_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
        holder.radioButton.isChecked = position == selectedItemPosition
        holder.radioButton.setOnClickListener {
            val lastCheckedItemPosition = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(selectedItemPosition)
            notifyItemChanged(lastCheckedItemPosition)
        }

    }

    override fun getItemCount() = data.size

    fun getImageFromPosition(): Int? {
        return try {
            Log.i(TAG, "Picture selected")
            data[selectedItemPosition].imageResourceId
        } catch (e: IndexOutOfBoundsException) {
            Log.e(TAG, "No picture selected")
            null
        }
    }
}