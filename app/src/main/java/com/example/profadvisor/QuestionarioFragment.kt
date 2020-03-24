package com.example.profadvisor

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.profadvisor.datamodel.Database
import com.example.profadvisor.datamodel.Prof
import com.example.profadvisor.datamodel.ProfRecensione
import kotlinx.android.synthetic.main.fragment_questionario.*



class QuestionarioFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questionario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Prendo le inforamzioni sulla recensione e sul rating dal bundle
        arguments?.let {

            var recensione: ProfRecensione? = it.getParcelable("metarecensione_prof")

            recensione?.let {

                val recensioneMeta = it

                //Se il pulsante aggiungi viene premuto, creo la recensioneProfe vado a chiamare i metodo el databae che o salva nel database di firease
                btn_aggiungi2.setOnClickListener {

                    val gent = check_gentile.isChecked
                    val disp = check_disponibile.isChecked
                    val sev = check_Severo.isChecked
                    val div = check_divertente.isChecked
                    val comp = check_competente.isChecked
                    val impa = check_imparziale.isChecked
                    val esig = check_esigente.isChecked
                    val socie = check_socievole.isChecked
                    val caris = check_carismatica.isChecked
                    val impegn = check_impegnativo.isChecked

                    val recensioneUpload = ProfRecensione(
                        recensioneMeta.nomeUtente,
                        recensioneMeta.nomeDocente,
                        recensioneMeta.email,
                        recensioneMeta.materia,
                        recensioneMeta.recensione,
                        recensioneMeta.rating,
                        gent.toString(),
                        disp.toString(),
                        sev.toString(),
                        div.toString(),
                        comp.toString(),
                        impa.toString(),
                        esig.toString(),
                        socie.toString(),
                        caris.toString(),
                        impegn.toString()
                    )

                    Database.salvaRecensione(recensioneUpload)


                    val prof = Database.profFROMrecensione(recensioneUpload)

                    Database.saveRating(prof)

                    val b = Bundle()
                    b.putParcelable("prof",prof)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_questionarioFragment_to_unProfFragment,b)
                }

            }


        }
    }

}
