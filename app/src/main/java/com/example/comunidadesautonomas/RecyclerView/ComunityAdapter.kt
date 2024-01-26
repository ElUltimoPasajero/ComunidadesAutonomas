package com.example.comunidadesautonomas.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.comunidadesautonomas.DB.ComunityDiffUtil
import com.example.comunidadesautonomas.Entities.Comunity
import com.example.comunidadesautonomas.R

/**
 * Aqui se implementan los 3 metodos que encesitamsos para el recycler
 *Recibe la lista de objetos y implementaesta interfaz recycler
 */

class ComunityAdapter(
    private var comunitiesList: List<Comunity>,
    private val onClickListener: (Comunity) -> Unit
) : RecyclerView.Adapter<ComunityViewHolder>() {
    //Recibe un OnClickListener con una lambda para que cada vez que cliques en una comunidad haga lo que sea


    fun updateList(newList: List<Comunity>){

        val comunityDiff = ComunityDiffUtil(comunitiesList, newList)
        val result = DiffUtil.calculateDiff(comunityDiff)
        comunitiesList = newList
        result.dispatchUpdatesTo(this)

    }


    /**
     * Metodo para inflae el layout
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComunityViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ComunityViewHolder(layoutInflater.inflate(R.layout.item_comunity, parent, false))

    }
    /**
     * Llama al metodo render de cada objeto que estemos mostrando
     */


    override fun onBindViewHolder(holder: ComunityViewHolder, position: Int) {

        val item = comunitiesList[position]
        holder.render(item, onClickListener)


    }


    /**
     * Este solo devuelve el tamaño de la lista
     */

    override fun getItemCount(): Int {
        return comunitiesList.size
    }



}