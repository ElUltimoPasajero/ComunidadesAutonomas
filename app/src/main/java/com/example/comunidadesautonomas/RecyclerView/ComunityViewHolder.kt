package com.example.comunidadesautonomas.RecyclerView

import android.content.DialogInterface.OnClickListener
import android.view.ContextMenu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.comunidadesautonomas.Entities.Comunity
import com.example.comunidadesautonomas.R
import com.example.comunidadesautonomas.databinding.ItemComunityBinding

class ComunityViewHolder(view: View): ViewHolder(view), View.OnCreateContextMenuListener {

    val binding= ItemComunityBinding.bind(view)

    private val name : TextView= view.findViewById(R.id.txtComunidad)
    private val img : ImageView= view.findViewById(R.id.flagImg)

    private lateinit var comunity: Comunity



    fun render(item: Comunity, onClickListener:(Comunity)-> Unit){

        comunity= item
        name.text = item.name

        img.setImageResource(item.flag)
        itemView.setOnClickListener {
            onClickListener(item)
        }

        itemView.setOnCreateContextMenuListener(this)


    }

    override fun onCreateContextMenu(
        menu : ContextMenu?,
        v: View?,
        menuInfo : ContextMenu.ContextMenuInfo?
    ) {

        menu!!.setHeaderTitle(comunity.name)

        menu.add(this.adapterPosition,0,0,"Eliminar")
        menu.add(this.adapterPosition,1,1,"Editar")
        menu.add(this.adapterPosition,2,2,"Hacer Foto")
        menu.add(this.adapterPosition,3,3,"Ver Foto")

    }


}