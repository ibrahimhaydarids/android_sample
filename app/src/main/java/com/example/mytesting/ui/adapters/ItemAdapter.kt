package com.example.mytesting.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.mytesting.R
import com.example.mytesting.model.Item
import com.example.mytesting.ui.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.item_recycler_items.view.*
import javax.inject.Inject

class ItemAdapter @Inject constructor(
    private val glide: RequestManager,
    private val context: Context,
) : RecyclerView.Adapter<ItemAdapter.SavedViewHolder>() {

    private var onItemClickListener: ((Item) -> Unit)? = null
    private var onFavoriteCLickListener: ((Item, Int, ImageButton) -> Unit)? = null

    var items: List<Item>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    lateinit var homeViewModel: HomeViewModel
    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_items, parent, false)
        return SavedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.categoryName!!
        holder.tvDescription.text = item.date
        setFavorite(holder.btFavorite,item.favorite)

        holder.linearItem.setOnClickListener{
            onItemClickListener?.let { click ->
                click(item)
            }
        }

        holder.btFavorite.setOnClickListener{
            onFavoriteCLickListener?.let { click ->
                click(item,position,holder.btFavorite)
            }
        }


        holder.apply {
            homeViewModel.dataIds.observeForever {
                it?.let { list ->
                    if (list.contains(item.id)) {
                        item.favorite = true
                        setFavorite(holder.btFavorite,true)
                    } else {
                        setFavorite(holder.btFavorite,false)
                        item.favorite = false
                    }

                }
            }

        }
    }

    private fun setFavorite(btn:ImageButton,favorite:Boolean){
        btn.setBackgroundResource(if(favorite) R.drawable.ic_star_filled else R.drawable.ic_star)
    }

    override fun getItemCount(): Int = items.size

    fun setOnItemClickListener(listener: (Item) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnFavoriteClickListener(listener: (Item,Int,ImageButton) -> Unit) {
        onFavoriteCLickListener = listener
    }

    inner class SavedViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        val tvTitle = itemView.tvTitle
        val tvDescription = itemView.tvDescription
        val linearItem = itemView.linearItem
        val ivItem = itemView.ivItem
        val btFavorite = itemView.btFavorite
    }

}