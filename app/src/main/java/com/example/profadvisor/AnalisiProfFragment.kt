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

class AnalisiProfFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analisi_prof, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val prof: Prof? = it.getParcelable("prof")
            prof?.let {

                analisiDocente_nome.text = it.Nome
                analisiDocente_rating.rating = it.Rating


                btn_avantiAnalisiProf.setOnClickListener {
                    val b = Bundle()
                    b.putParcelable("prof", prof)

                    Navigation.findNavController(it)
                        .navigate(R.id.action_analisiProfFragment_to_pagina1Fragment,b)


                }
            }


        }


    }

}