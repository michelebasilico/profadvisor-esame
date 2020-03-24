package com.example.profadvisor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.profadvisor.datamodel.Database
import com.example.profadvisor.datamodel.Prof

/**
 * Adapter utilizzato per la RecyclerView con l'elenco dei prof
 *
 */
class MigliorProfAdapter(var dataset: ArrayList<Prof>, val context: Context) : RecyclerView.Adapter<ModelViewHolder>() {

    // Invocata per creare un ViewHolder
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ModelViewHolder {
        // Crea e restituisce un viewholder, effettuando l'inflate del layout relativo alla riga
        return ModelViewHolder(LayoutInflater.from(context).inflate(R.layout.miglioriprof, viewGroup, false))
    }

    // Invocata per conoscere quanti elementi contiene il dataset
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Invocata per visualizzare all'interno del ViewHolder il dato corrispondente alla riga
    override fun onBindViewHolder(viewHolder: ModelViewHolder, position: Int) {


        val miglior_prof = dataset.get(position)
        viewHolder.mdNome.text = miglior_prof.Nome
        viewHolder.mdRating.rating = miglior_prof.Rating

        // Imposto il listner per passare a visualizzare il dettaglio
        viewHolder.itemView.setOnClickListener {

            // Creo un bundle e vi inserisco il prof da visualizzare
            val b = Bundle()
            b.putParcelable("prof", miglior_prof)     //TODO: Il nome dell'ogggetto andrebbe inserito in un solo punto!!
            Navigation.findNavController(it).navigate(R.id.action_profFragment_to_unProfFragment, b)
        }
    }

    fun datiAggiornati(professori: ArrayList<Prof>) {
        this.dataset = professori
        this.notifyDataSetChanged()
    }
}