package com.example.comunidadesautonomas.DB

import androidx.recyclerview.widget.DiffUtil
import com.example.comunidadesautonomas.Entities.Comunity

class ComunityDiffUtil(
    private val oldList: List<Comunity>,
    private val newList: List<Comunity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList[oldItemPosition].flag == newList[newItemPosition].flag
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}