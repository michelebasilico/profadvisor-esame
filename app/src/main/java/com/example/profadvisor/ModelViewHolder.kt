package com.example.profadvisor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.miglioriprof.view.*


class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val mdNome = view.nomedocente_migliore
    val mdRating = view.ratingBar_migliorProf
}