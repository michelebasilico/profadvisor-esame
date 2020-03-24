package com.example.profadvisor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.profadvisor.datamodel.Database
import com.example.profadvisor.datamodel.Prof
import kotlinx.android.synthetic.main.fragment_analisi_prof.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_pagina1.*
import kotlinx.android.synthetic.main.fragment_un_prof.*

class Pagina1Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pagina1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val prof: Prof? = it.getParcelable("prof")
            prof?.let {

                val b = Bundle()
                b.putParcelable("prof", prof)

                inizializzaRecensioni(it)

                btn_avantiPag1.setOnClickListener{

                    Navigation.findNavController(it)
                        .navigate(R.id.action_pagina1Fragment_to_pagina2Fragment,b)

                }

                btn_indietropg1.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_pagina1Fragment_to_analisiProfFragment,b)


                }

            }



        }

        arguments?.let {
            val prof: Prof? = it.getParcelable("prof")
            prof?.let {

                inizializzaRecensioni(prof)

            }
        }


    }

    fun inizializzaRecensioni(prof: Prof){

        var gentile = 0
        var disponibile = 0
        var severo = 0
        var divertente = 0
        var competente = 0

        var recensioni = Database.getElencoRecensioni(prof)
        for (i in 0 until recensioni.count()){

            if (recensioni[i].gentile == "true"){

                gentile++

            }
            if (recensioni[i].disponibile == "true"){

                disponibile++

            }
            if (recensioni[i].severo == "true"){

                severo++

            }
            if (recensioni[i].divertente == "true"){

                divertente++

            }
            if (recensioni[i].competente == "true"){

                competente++

            }

        }

        text_gentile.text = gentile.toString()
        text_disponibile.text = disponibile.toString()
        text_severo.text = severo.toString()
        text_divertente.text = divertente.toString()
        text_competente.text = competente.toString()
    }

}
