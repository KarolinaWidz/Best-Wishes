package edu.karolinawidz.bestwishes.ui.adapter

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
import com.bumptech.glide.Glide
import edu.karolinawidz.bestwishes.model.listItems.Picture
import edu.karolinawidz.bestwishes.util.Options


class PictureItemAdapter(
    private val context: Context,
    private val recyclerView: RecyclerView
) : ListAdapter<Picture, PictureItemAdapter.ItemViewHolder>(PictureAdapterDiff),
    View.OnClickListener {

    lateinit var itemClickListener: (picture: Picture) -> Unit
    lateinit var loadMore: () -> Unit
    lateinit var previousSelected: () -> Int

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val radioButton: RadioButton =
            view.findViewById(edu.karolinawidz.bestwishes.R.id.picture_radio_button)
        val textView: TextView = view.findViewById(edu.karolinawidz.bestwishes.R.id.picture_text)
        val imageView: ImageView = view.findViewById(edu.karolinawidz.bestwishes.R.id.picture_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(edu.karolinawidz.bestwishes.R.layout.picture_list_item, parent, false)
        item.setOnClickListener(this)
        return ItemViewHolder(item)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (position == itemCount - 1) {
            loadMore()
        }

        val item = getItem(position)
        holder.run {
            textView.text = item.description
            Glide.with(context).load(item.imageUri).apply(Options.glideOptions).into(imageView)
            radioButton.isChecked = item.isSet
        }
    }

    override fun onClick(v: View) {
        val position = recyclerView.getChildAdapterPosition(v)
        notifyItemChanged(position)
        notifyItemChanged(previousSelected())
        itemClickListener(getItem(position))
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