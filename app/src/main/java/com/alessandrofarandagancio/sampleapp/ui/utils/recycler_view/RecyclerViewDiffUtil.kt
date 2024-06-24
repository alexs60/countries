package com.alessandrofarandagancio.sampleapp.ui.utils.recycler_view

import androidx.recyclerview.widget.DiffUtil

class RecyclerViewDiffUtil<T : ItemizableModel>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean =
        oldList[oldPos].id == newList[newPos].id


    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean = when {
        oldList[oldPos].id != newList[newPos].id -> false
        else -> true
    }

}