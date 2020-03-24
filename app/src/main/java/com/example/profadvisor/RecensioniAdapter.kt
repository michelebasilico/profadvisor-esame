package com.example.profadvisor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.profadvisor.datamodel.Prof
import com.example.profadvisor.datamodel.ProfRecensione

class RecensioniAdapter (var dataset: ArrayList<ProfRecensione>, val context: Context) : RecyclerView.Adapter<RecensioniViewHolder>() {

    // Invocata per creare un ViewHolder
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecensioniViewHolder {
        // Crea e restituisce un viewholder, effettuando l'inflate del layout relativo alla riga
        return RecensioniViewHolder(LayoutInflater.from(context).inflate(R.layout.recensioni_riga, viewGroup, false))
    }

    // Invocata per conoscere quanti elementi contiene il dataset
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Invocata per visualizzare all'interno del ViewHolder il dato corrispondente alla riga
    override fun onBindViewHolder(viewHolder: RecensioniViewHolder, position: Int) {
        val recensioneProf = dataset.get(position)

        viewHolder.rcNome.text = recensioneProf.nomeUtente
        viewHolder.rcRecensione.text = recensioneProf.recensione
        viewHolder.rcRating.rating = recensioneProf.rating!!
    }

    fun datiAggiornati(recensioni: ArrayList<ProfRecensione>) {
        this.dataset = recensioni
        this.notifyDataSetChanged()
    }
}