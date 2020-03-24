package com.example.profadvisor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profadvisor.datamodel.Database
import com.example.profadvisor.datamodel.Prof
import com.example.profadvisor.datamodel.ProfRecensione
import kotlinx.android.synthetic.main.fragment_un_prof.*



class UnProfFragment : Fragment() {

    private lateinit var adapter: RecensioniAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_un_prof, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listRecensioni.layoutManager = LinearLayoutManager(activity)


        // Estraggo il parametro (prof) dal bundle ed eventualmente lo visualizzo

        arguments?.let {
            val prof: Prof? = it.getParcelable("prof")
            prof?.let {
                nomedocente1_text.text = it.Nome
                materiadocente1_text.text = it.Materia
                emaildocente1_text.text = it.Email
                ratingBar3.rating = it.Rating

                // Associo l'adapter alla RecyclerView
                adapter = RecensioniAdapter(Database.getElencoRecensioni(it), requireContext())
                listRecensioni.adapter = adapter

                Database.notificaLetturaRecensioni {
                    var recensioni = Database.getElencoRecensioni(it)
                    adapter.datiAggiornati(recensioni)
                }



                button_infoProf.setOnClickListener {

                    val b = Bundle()
                    b.putParcelable("prof", prof)

                    Navigation.findNavController(it)
                        .navigate(R.id.action_unProfFragment_to_analisiProfFragment,b)

                }

                fairecensione_button.setOnClickListener {

                    val b = Bundle()
                    b.putParcelable("prof", prof)

                    Navigation.findNavController(it)
                        .navigate(R.id.action_unProfFragment_to_recensioneFragment,b)


                }

                home_button.setOnClickListener {

                    Navigation.findNavController(it)
                        .navigate(R.id.action_unProfFragment_to_profFragment)

                }

            }

        }

        /*arguments?.let {
            val profQuestionario: Prof? = it.getParcelable("prof_upload")
            profQuestionario?.let {

                nomedocente1_text.text = it.Nome
                materiadocente1_text.text = it.Materia
                emaildocente1_text.text = it.Email

                // Associo l'adapter alla RecyclerView
                adapter = RecensioniAdapter(Database.getElencoRecensioni(it), requireContext())
                listRecensioni.adapter = adapter

                Database.notificaLetturaRecensioni {
                    var recensioni = Database.getElencoRecensioni(it)
                    adapter.datiAggiornati(recensioni)
                }

                ratingBar3.rating = Database.getRating(it)


                button_infoProf.setOnClickListener {

                    val b = Bundle()
                    b.putParcelable("prof", profQuestionario)

                    Navigation.findNavController(it)
                        .navigate(R.id.action_unProfFragment_to_analisiProfFragment,b)

                }

                fairecensione_button.setOnClickListener {

                    val b = Bundle()
                    b.putParcelable("prof", profQuestionario)

                    Navigation.findNavController(it)
                        .navigate(R.id.action_unProfFragment_to_recensioneFragment,b)


                }


            }
        }*/


    }

}