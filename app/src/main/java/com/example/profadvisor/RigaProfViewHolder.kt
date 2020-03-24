package com.example.profadvisor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prof_riga.view.*

/**
 * ViewHolder utilizzato per le righe della RecyclerView con l'elenco delle birre
 * contiene le proprietà corrispondenti agli elementi della riga
 */
class RigaProfViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvNome = view.nomedocente_rigaProf
    val tvMateria = view.materiadocente_text
    val tvEmail = view.emaildocente_text
    val tvRating = view.ratingBar4
}