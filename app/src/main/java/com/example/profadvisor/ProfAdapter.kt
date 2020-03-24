package com.example.profadvisor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.profadvisor.datamodel.Database
import com.example.profadvisor.datamodel.Prof
import kotlinx.android.synthetic.main.fragment_prof.view.*
import kotlinx.android.synthetic.main.prof_riga.view.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * Adapter utilizzato per la RecyclerView con l'elenco dei prof
 *
 */
 class ProfAdapter(var dataset: ArrayList<Prof>, val context: Context) : RecyclerView.Adapter<RigaProfViewHolder>(),Filterable {

    private var ricercaprof = ArrayList<Prof>()

    init {
        ricercaprof = dataset
    }


    // Invocata per creare un ViewHolder
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RigaProfViewHolder {
        // Crea e restituisce un viewholder, effettuando l'inflate del layout relativo alla riga
        return RigaProfViewHolder(LayoutInflater.from(context).inflate(R.layout.prof_riga, viewGroup, false))
    }

    // Invocata per conoscere quanti elementi contiene il dataset
    override fun getItemCount(): Int {
        return ricercaprof.size
    }

    // Invocata per visualizzare all'interno del ViewHolder il dato corrispondente alla riga
    override fun onBindViewHolder(viewHolder: RigaProfViewHolder, position: Int) {
        val prof = ricercaprof.get(position)

        viewHolder.tvNome.text = prof.Nome
        viewHolder.tvMateria.text = prof.Materia
        viewHolder.tvEmail.text = prof.Email
        viewHolder.tvRating.rating = prof.Rating


        // Imposto il listner per passare a visualizzare il dettaglio
        viewHolder.itemView.setOnClickListener {

            // Creo un bundle e vi inserisco il prof da visualizzare
            val b = Bundle()
            b.putParcelable("prof", prof)
            Navigation.findNavController(it).navigate(R.id.action_profFragment_to_unProfFragment, b)
        }
    }

    //Metodo utilizzato per filtrare la ricerca. Viene chiamato passando come argomento la stringa aggiornata della search bar
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                //Creo una stringa e metto l'argomento passato (la stringa aggiornata della search bar)
                val charSearch = constraint.toString()
                //Valuto se la stringa e vuoto. In tal caso mostro tutto il database di prof.
                if (charSearch.isEmpty()) {
                    ricercaprof = dataset
                } else {
                    //Altrimenti creo un vettore di prof e attraverso un ciclo for valuto per ogni riga del database quei NOMI che contengono la stringa passata
                    val resultList = ArrayList<Prof>()
                    for (row in dataset) {
                        if (row.Nome.toLowerCase().contains(charSearch.toLowerCase())) {
                            //Qui li aggiungiamo al nuovo vettore di prof
                            resultList.add(row)
                        }
                    }
                    ricercaprof = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = ricercaprof
                return filterResults
            }

            //Questo metodo serve per gestire l'aggiornamento del database
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ricercaprof = results?.values as ArrayList<Prof>
                notifyDataSetChanged()
            }

        }
    }

    fun datiAggiornati(professori: ArrayList<Prof>) {
        this.dataset = professori
        this.notifyDataSetChanged()
    }
}