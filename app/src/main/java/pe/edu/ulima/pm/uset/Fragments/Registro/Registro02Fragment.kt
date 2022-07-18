package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro02Binding

class Registro02Fragment : Fragment() {
    //VIEW BINDING FOR FRAGMENTS
    private var _binding: FragmentRegistro02Binding? = null
    private val binding get() = _binding!!

    //ACTIVITY LIFECYCLE
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //VIEW BINDING FOR FRAGMENTS
        _binding = FragmentRegistro02Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //BUTTONS CLICK LISTENERS
        binding.btnContinuar.   setOnClickListener { BtnContinuar()     }
        binding.btnRegresar.    setOnClickListener { BtnRegresar()      }
    }

    //OnViewCreated Functions ON CLICK LISTENERS ***************************************************
    private fun BtnContinuar() {
        //Save email in parent activity
        val email = binding.tilCorreoEditText.text.toString().trim { it <= ' ' }
        if (email.isNotEmpty()){
            //Email is not empty
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                //Email matches the pattern
                FirebaseClass.auth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
                    if (it.isSuccessful)
                        Log.e("LISTA",it.toString())
                    else
                        Log.e("LISTA","VACIO")
                }
            }else{
                //Email doesn't matches the pattern
                Toast.makeText(requireContext(), "Ingrese un correo valido", Toast.LENGTH_SHORT).show()
            }
        }else{
            //Email is empty
            Toast.makeText(requireContext(), "Ingrese un correo", Toast.LENGTH_SHORT).show()
        }
        RegistroActivity.correo = email
        //AddFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewRegistro, Registro03Fragment())
            .addToBackStack("3")
            .commit()
    }
    private fun BtnRegresar() {
        requireActivity().onBackPressed()
    }
    //OnViewCreated Functions ON CLICK LISTENERS ***************************************************

    //ACTIVITY LIFECYCLE
    override fun onDestroyView() {
        super.onDestroyView()
        //VIEW BINDING FOR FRAGMENTS
        _binding = null
    }
}