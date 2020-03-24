package com.example.profadvisor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.profadvisor.datamodel.Database
import com.example.profadvisor.datamodel.Prof
import kotlinx.android.synthetic.main.fragment_pagina2.*


class Pagina2Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pagina2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val prof: Prof? = it.getParcelable("prof")

            prof?.let {

                val b = Bundle()
                b.putParcelable("prof", prof)

                inizializzarecensioni(it)

                btn_indietropg2.setOnClickListener {

                    Navigation.findNavController(it)
                        .navigate(R.id.action_pagina2Fragment_to_pagina1Fragment,b)


                }

                btn_avantiPag2.setOnClickListener {

                    Navigation.findNavController(it)
                        .navigate(R.id.action_pagina2Fragment_to_unProfFragment,b)
                }

            }



        }


    }

    fun inizializzarecensioni(prof: Prof){

        var imparziale = 0
        var esigente = 0
        var socievole = 0
        var carismatico = 0
        var impegnativo = 0

        var recensioni = Database.getElencoRecensioni(prof)
        for (i in 0 until recensioni.count()) {

            if (recensioni[i].imparziale == "true") {

                imparziale++

            }
            if (recensioni[i].esigente == "true") {

                esigente++

            }
            if (recensioni[i].socievole == "true") {

                socievole++

            }
            if (recensioni[i].carismatico == "true") {

                carismatico++

            }
            if (recensioni[i].impegnativo == "true") {

                impegnativo++

            }

        }

        text_imparziale.text = imparziale.toString()
        text_esigente.text = esigente.toString()
        text_socievole.text = socievole.toString()
        text_carismatico.text = carismatico.toString()
        text_impegnativo.text = impegnativo.toString()

    }
}
