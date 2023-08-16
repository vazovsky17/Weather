package app.vazovsky.weather.presentation.views.recyclerview

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

/** Базовый адаптер для RecyclerView */
abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected val items = mutableListOf<T>()

    override fun getItemCount() = items.size

    fun getItem(position: Int): T {
        return items[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(items: List<T>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    val currentList: List<T>
        get() = items
}