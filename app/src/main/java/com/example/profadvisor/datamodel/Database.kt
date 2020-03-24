package com.example.profadvisor.datamodel

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object Database {

    var auth= FirebaseAuth.getInstance()
    //Uso una referenza del database
    private var database = FirebaseDatabase.getInstance()
    val uid = FirebaseAuth.getInstance().uid!!
    //Array che useremo per contenere il database
    private var professori = ArrayList<Prof>()
    val recensioniProfessori = ArrayList<ProfRecensione>()
    private var recensioniProfessori2 = ArrayList<ProfRecensione>()
    private var miglioriProfessori = ArrayList<Prof>()

    //Nome nodo principale database a cui facciamo riferimento
    private val NODO_PROFESSORI = "Professori"
    private val NODO_RECENSIONI = "Recensioni"

    //Prova per aggiornamento dati
    private var aggiornamentoProfessori : (() -> Unit) ? = null
    private var aggiornamentoMiglioriProfessori : (() -> Unit) ? = null
    private var aggiornamentoRecensioni : (() -> Unit) ? = null



    // Inizializzatore per popolare il database
    init {

        //Sfruttiamo la nostra referenza, puntiamo al nodo del nostro database che ci interessa e andiamo a effettuare la lettura dei Prof
        database.getReference(NODO_PROFESSORI).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                professori.clear()
                miglioriProfessori.clear()
                for(professoriSnapshot in dataSnapshot.children){
                    val professore= professoriSnapshot.getValue(Prof::class.java)
                    professori.add(professore!!)
                    val miglioreprofessore = professoriSnapshot.getValue(Prof::class.java)
                    if (miglioreprofessore!!.Rating>=4){

                        miglioriProfessori.add(miglioreprofessore)

                    }

                }

                aggiornamentoProfessori?.invoke()
                aggiornamentoMiglioriProfessori?.invoke()
            }

            //Alla fine di questo processo il vettore professori conterrà i prof. del nostro database
            override fun onCancelled(po: DatabaseError){
                TODO("not implemented")
            }
        })



    }

    // Restituisce l'elenco di tutti i prof presenti(Che abbiamo precedentemente riempito)
    fun getElencoProf(): ArrayList<Prof> {
        return professori
    }

    /**
     * Aggiunge una nuova prof nel database
     */
    fun salvaRecensione(recensione_Prof: ProfRecensione) {

        val uid = FirebaseAuth.getInstance().uid!!
        val nomeDocente = recensione_Prof.nomeDocente
        val ref= database.getReference(NODO_RECENSIONI).child(nomeDocente!!).child(uid)

        ref.setValue(recensione_Prof)
    }

    fun getElencoMiglioriProf(): ArrayList<Prof> {
        return miglioriProfessori
    }

    fun getElencoRecensioni(prof: Prof): ArrayList<ProfRecensione>{

        val nome = prof.Nome
        //Sfruttiamo la nostra referenza, puntiamo al nodo del nostro database che ci interessa e andiamo a effettuare la lettura dei Prof
        database.getReference(NODO_RECENSIONI).child(nome).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                recensioniProfessori.clear()
                for(professoriSnapshot in dataSnapshot.children){

                    val recensioneprofessore= professoriSnapshot.getValue(ProfRecensione::class.java)
                    if (recensioneprofessore != null) {
                        recensioniProfessori.add(recensioneprofessore)
                    }
                }

                aggiornamentoRecensioni?.invoke()


            }

            //Alla fine di questo processo il vettore professori conterrà i prof. del nostro database
            override fun onCancelled(po: DatabaseError){
                TODO("not implemented")
            }
        })
        return recensioniProfessori

    }

    fun saveRating(prof: Prof){

        val nomeDocente = prof.Nome
        val ref= database.getReference(NODO_PROFESSORI).child(nomeDocente).child("Rating")

        recensioniProfessori2 = getElencoRecensioni(prof)
        var lunghezza = recensioniProfessori2.count()
        var ratingAggiornato = prof.Rating
        for(i in 0 until lunghezza){

            ratingAggiornato += recensioniProfessori2[i].rating!!
            Log.d("Rating:","$ratingAggiornato,$recensioniProfessori2")


        }
        ratingAggiornato /= lunghezza+1

        ref.setValue(ratingAggiornato)

    }


    fun profFROMrecensione(profrecensione: ProfRecensione): Prof{

        var professore = Prof()
        var professori= getElencoProf()
        var sizefor = getElencoProf().count()
        for (i in 0 until sizefor){

            if(professori[i].Nome == profrecensione.nomeDocente){

                professore = professori[i]

            }

        }

        return professore

    }


    fun esci(){
        auth.signOut()

    }

    fun notificaLetturaProfessori(aggiornamento: () -> Unit) {
        aggiornamentoProfessori = aggiornamento
    }

    fun notificaLetturaMiglioriProfessori(aggiornamento: () -> Unit) {
        aggiornamentoMiglioriProfessori = aggiornamento
    }

    fun notificaLetturaRecensioni(aggiornamento: () -> Unit) {
        aggiornamentoRecensioni = aggiornamento
    }

}