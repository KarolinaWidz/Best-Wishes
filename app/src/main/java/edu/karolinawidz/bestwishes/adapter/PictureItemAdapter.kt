package edu.karolinawidz.bestwishes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.model.Picture

class PictureItemAdapter(
    private val context: Context,
    private val recyclerView: RecyclerView
) : ListAdapter<Picture, PictureItemAdapter.ItemViewHolder>(PictureAdapterDiff),
    View.OnClickListener {

    lateinit var itemClickListener: (picture: Picture) -> Unit
    private var previousSelected = -1

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val radioButton: RadioButton = view.findViewById(R.id.picture_radio_button)
        val textView: TextView = view.findViewById(R.id.picture_text)
        val imageView: ImageView = view.findViewById(R.id.picture_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.picture_list_item, parent, false)
        item.setOnClickListener(this)
        return ItemViewHolder(item)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.run {
            textView.text = context.resources.getString(item.stringResourceId)
            imageView.setImageURI(item.imageUri)
            radioButton.isChecked = item.isSet
        }
    }

    override fun onClick(v: View) {
        val position = recyclerView.getChildAdapterPosition(v)
        notifyItemChanged(position)
        notifyItemChanged(previousSelected)
        itemClickListener(getItem(position))
        previousSelected = position
    }
}

object PictureAdapterDiff : DiffUtil.ItemCallback<Picture>() {
    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }
}