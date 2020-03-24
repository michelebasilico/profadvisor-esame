package com.example.profadvisor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        //settaggio del pulsante Login
        login_button.setOnClickListener {

            val email = login_Email.text.toString()
            val password = login_Pass.text.toString()

            if (validateForm()) {

                login_progressBar!!.visibility = View.VISIBLE

                login_button.isEnabled = false //disabilito il pulsante se tutto è andato a buon fine
                login_button.isClickable = false


                auth.signInWithEmailAndPassword(
                        email,
                        password
                    ) //funzione di firebase per l'acccesso
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            //usato per il debug


                            var uid = auth.uid //prende l'user id dell'account
                            val bundle = Bundle()
                            bundle.putString(
                                "autenticazione",
                                uid
                            ) //passiamo l'uid ai fragment successsivi

                            login_progressBar!!.visibility = View.GONE

                            Navigation.findNavController(it)
                                .navigate(R.id.action_loginFragment_to_profFragment, bundle)


                        } else {

                            login_button.isEnabled = true //se non è andato a buon fine lo riabilito
                            login_button.isClickable = true

                            login_progressBar!!.visibility = View.GONE

                            // Se fallisce mostra un messaggio al'utente
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(context, "Autenticazione fallita.", Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
            }
        }


        //settaggio del pulsante per la creazione dell'account->Vai al fragment della registrazione
        login_register.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_loginFragment_to_registerFragment)


        }
    }


    //funzione per vedere se i dati sono validi
    private fun validateForm(): Boolean {
        var valid = true

        val email = login_Email.text.toString()
        if (TextUtils.isEmpty(email)) {
            login_Email.error = "Richiesta."
            valid = false
        } else {
            login_Email.error = null
        }

        val password = login_Pass.text.toString()
        if (TextUtils.isEmpty(password)) {
            login_Pass.error = "Richiesta."
            valid = false
        } else {
            login_Pass.error = null
        }

        return valid
    }

}
