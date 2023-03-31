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
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

class PictureItemAdapter(
    private val viewModel: CardViewModel,
    private val context: Context,
) : ListAdapter<Picture, PictureItemAdapter.ItemViewHolder>(PictureAdapterDiff) {

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
        val item = getItem(position)
        holder.run {
            textView.text = context.resources.getString(item.stringResourceId)
            imageView.setImageURI(item.imageUri)
            radioButton.isChecked = position == viewModel.selectedPictureId
            radioButton.setOnClickListener {
                val lastCheckedItemPosition = viewModel.selectedPictureId
                viewModel.setSelectedPictureId(position)
                notifyItemChanged(position)
                notifyItemChanged(lastCheckedItemPosition)
            }
        }
    }
}

object PictureAdapterDiff : DiffUtil.ItemCallback<Picture>() {
    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.imageUri == newItem.imageUri
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }

}