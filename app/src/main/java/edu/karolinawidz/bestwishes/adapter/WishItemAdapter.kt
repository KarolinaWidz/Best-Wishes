package edu.karolinawidz.bestwishes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.model.Wish

class WishItemAdapter(
    private val context: Context,
    private val recyclerView: RecyclerView
) :
    ListAdapter<Wish, WishItemAdapter.ItemViewHolder>(WishAdapterDiff), View.OnClickListener {

    lateinit var itemClickListener: (wish: Wish) -> Unit
    lateinit var previousSelected: () -> Int

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioButton: RadioButton = itemView.findViewById(R.id.wish_radio_button)
        val textView: TextView = itemView.findViewById(R.id.wish_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.wish_list_item, parent, false)
        item.setOnClickListener(this)
        return ItemViewHolder(item)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.run {
            textView.text = context.resources.getString(item.stringResourceId)
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

object WishAdapterDiff : DiffUtil.ItemCallback<Wish>() {
    override fun areItemsTheSame(oldItem: Wish, newItem: Wish): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Wish, newItem: Wish): Boolean {
        return oldItem == newItem
    }

}