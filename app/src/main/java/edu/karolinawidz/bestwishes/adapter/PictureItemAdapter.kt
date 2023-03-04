package edu.karolinawidz.bestwishes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.model.Picture
import edu.karolinawidz.bestwishes.viewModel.CardViewModel

class PictureItemAdapter(
    private val viewModel: CardViewModel,
    private val context: Context,
    private val data: List<Picture>
) :
    RecyclerView.Adapter<PictureItemAdapter.ItemViewHolder>() {

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
        holder.radioButton.isChecked = position == viewModel.selectedPictureId
        println(viewModel.selectedPictureId)
        holder.radioButton.setOnClickListener {
            val lastCheckedItemPosition = viewModel.selectedPictureId
            viewModel.setSelectedPictureId(position)
            notifyItemChanged(position)
            notifyItemChanged(lastCheckedItemPosition)
        }
    }

    override fun getItemCount() = data.size
}