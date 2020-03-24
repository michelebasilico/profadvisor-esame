package com.example.profadvisor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recensioni_riga.view.*

class RecensioniViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rcNome = view.nomeutente_rigaProf
    val rcRecensione = view.recensione_text
    val rcRating = view.ratingRecensione

}