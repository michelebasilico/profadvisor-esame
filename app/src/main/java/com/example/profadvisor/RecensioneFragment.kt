package com.example.profadvisor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.profadvisor.datamodel.Prof
import com.example.profadvisor.datamodel.ProfRecensione
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_recensione.*


class RecensioneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recensione, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            val prof: Prof? = it.getParcelable("prof")
            prof?.let {

                btn_aggiungi1.setOnClickListener {

                    val nomeDocente = prof.Nome
                    val emailDocente = prof.Email
                    val materiaDocente = prof.Materia
                    val recensione: String
                    val voto: Float
                    val nomeUtente = nomeUtente_text.text.toString()

                    if (recensioneUtente_text.text.toString().isNotEmpty() && votoUtente.rating.toString()
                            .isNotEmpty() && nomeUtente_text.text.toString().isNotEmpty()
                    ) {

                        recensione = recensioneUtente_text.text.toString()  // carico la recensione
                        voto = votoUtente.rating      // carico il voto

                        val recensioneMeta = ProfRecensione(
                            nomeUtente,
                            nomeDocente,
                            emailDocente,
                            materiaDocente,
                            recensione,
                            voto,
                            false.toString(),
                            false.toString(),
                            false.toString(),
                            false.toString(),
                            false.toString(),
                            false.toString(),
                            false.toString(),
                            false.toString(),
                            false.toString(),
                            false.toString()
                        )

                        // Creo un bundle e vi inserisco la prima parte della recensione che passeremo al questionario. Solo li caricheremo i dati nel database
                        val b = Bundle()
                        b.putParcelable("metarecensione_prof", recensioneMeta)


                        Navigation.findNavController(it)
                            .navigate(R.id.action_recensioneFragment_to_questionarioFragment, b)



                    } else {

                        Toast.makeText(
                            activity,
                            "Inserire una recensione e un voto perfavore",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
        //Quando premo avanti,carico la prima parte della recensione e passo al questionario


        }
    }


}
