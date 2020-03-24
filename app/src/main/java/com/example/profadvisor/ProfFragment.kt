package com.example.profadvisor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profadvisor.datamodel.Database
import kotlinx.android.synthetic.main.fragment_prof.*

class ProfFragment : Fragment() {

    // L'adapter va dichiarato lateinit per inizializzarlo dopo la creazione della view
    private lateinit var adapter: ProfAdapter
    private lateinit var adapter2: MigliorProfAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prof, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Imposto il layout manager a lineare per avere scrolling in una direzione
        listProf.layoutManager = LinearLayoutManager(activity)
        listMiglioriProf.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Associo l'adapter alla RecyclerView
        adapter = ProfAdapter(Database.getElencoProf(), requireContext())
        listProf.adapter = adapter

        adapter2 = MigliorProfAdapter(Database.getElencoMiglioriProf(),requireContext())
        listMiglioriProf.adapter = adapter2

        //Viene chiamato ogni volta che c'è un aggiornamento ai Professessori
        Database.notificaLetturaProfessori {
            var professori = Database.getElencoProf()
            adapter.datiAggiornati(professori)
        }

        Database.notificaLetturaMiglioriProfessori {
            var professori = Database.getElencoMiglioriProf()
            adapter2.datiAggiornati(professori)
        }



        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        logout_button.setOnClickListener{
            Database.esci()
            Navigation.findNavController(it).navigate(R.id.action_profFragment_to_loginFragment)
        }


    }
}