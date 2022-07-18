package pe.edu.ulima.pm.uset.Fragments.Registro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.ulima.pm.uset.ChatActivity
import pe.edu.ulima.pm.uset.CreateProfileActivity
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro03Binding
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro04Binding

class Registro04Fragment : Fragment() {

    private var _binding: FragmentRegistro04Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val user = FirebaseClass.auth2.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegistro04Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnviar.setOnClickListener {
            Toast.makeText(context, RegistroActivity.correo, Toast.LENGTH_SHORT).show()
            btnEnviar()
        }
    }
    //TODO: OnViewCreated Functions ****************************************************************
    private fun btnEnviar() {
        val password = binding.tilContraseAEditText.text.toString()
        val password2 = binding.tilContraseA2EditText.text.toString()
        if (password != password2){
            Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }else{
            FirebaseClass.isVerifiedEmailAndSignInWithEmailAndPassword(RegistroActivity.correo!!,password,requireContext())?.addOnCompleteListener(requireActivity()){
                if (it.isSuccessful){
                    if(FirebaseClass.auth.currentUser!!.isEmailVerified) {
                        Toast.makeText(context, "UserVerified", Toast.LENGTH_SHORT).show()
                        goToChatActivity()
                    }else{
                        Toast.makeText(context, "Verifique su correo", Toast.LENGTH_SHORT).show()
                        FirebaseClass.auth.currentUser!!.sendEmailVerification()
                    }
                    BotonEnviar()
                }else{
                    Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun goToChatActivity() {
        requireActivity().finish()
        startActivity(Intent(requireActivity(), CreateProfileActivity::class.java))
    }

    //TODO: OnViewCreated Functions ****************************************************************

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun BotonEnviar() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewRegistro, Registro03Fragment(),"registro 3")
            .addToBackStack("3")
            .commit()
    }



}