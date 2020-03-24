package com.example.profadvisor

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register.*
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_button.setOnClickListener {

            register_progressBar!!.visibility = View.VISIBLE

            var Email : String = register_email!!.text.toString()
            var Pass : String = register_pass!!.text.toString()
            var Pass2 : String = register_pass2!!.text.toString()

            if (TextUtils.isEmpty(Email)){

                Toast.makeText(activity, "Perfavore, inserisci una mail...",Toast.LENGTH_SHORT).show()

            }else if (TextUtils.isEmpty(Pass)){

                Toast.makeText(activity, "Perfavore, inserisci una Password...",Toast.LENGTH_SHORT).show()

            }else if (TextUtils.isEmpty(Pass2)){

                Toast.makeText(activity, "Perfavore, ripeti la Password...",Toast.LENGTH_SHORT).show()

            }else if (!Pass.equals(Pass2)){

                Toast.makeText(activity, "Le password non coincidono", Toast.LENGTH_SHORT).show()

            }
            //Dopo aver controllato che tutto sia corretto possiamo creare l'account per l'utente
            else{

                register_progressBar!!.visibility = View.VISIBLE

                auth.createUserWithEmailAndPassword(Email,Pass)
                    .addOnCompleteListener( OnCompleteListener { task ->

                        if(task.isSuccessful){
                            //Creato l'account posso chiamare l'intent che mi porta alla schermata di setup e eliminare la schermaata di loading
                            Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_profFragment)
                            Toast.makeText(activity, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show()

                            register_progressBar!!.visibility = View.GONE

                        }else{

                            register_progressBar!!.visibility = View.GONE

                            var errore = task.exception!!.message
                            Toast.makeText(activity, "Errore in fase di registrazione: "+ errore , Toast.LENGTH_SHORT).show()

                        }
                    })
            }

        }

    }



}
