package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro03Binding

class Registro03Fragment : Fragment() {
    //VIEW BINDING FOR FRAGMENTS
    private var _binding: FragmentRegistro03Binding? = null
    private val binding get() = _binding!!

    //ACTIVITY LIFECYCLE
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //VIEW BINDING FOR FRAGMENTS
        _binding = FragmentRegistro03Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //BUTTONS CLICK LISTENERS
        binding.btnSiguiente.      setOnClickListener { BtnEnviar()    }
        binding.btnRegresar.    setOnClickListener { BtnRegresar()  }
    }

    //OnViewCreated Functions ON CLICK LISTENERS ***************************************************
    private fun BtnEnviar() {
        //Compare passwords
        val pass1 = binding.tilContraseAEditText.text.toString()
        val pass2 = binding.tilContraseA2EditText.text.toString()
        //1.Passwords don't match
        if (pass1 != pass2){
            Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }
        //2.Passwords match
        else{
            //3.Check passwords length
            if(pass1.length>=6){
                //Password length is enough
                //Save email in parent activity
                RegistroActivity.password = pass1
                //Execute creation
                FirebaseClass.createUserWithEmailAndPassword(
                    RegistroActivity.correo!!,
                    RegistroActivity.password!!,
                    requireContext()
                ) { addFragmentRegistro04() }
            }else{
                //Password length is NOT enough
                Toast.makeText(context, "La contraseña debe tener 6 caracteres como mínimo", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun addFragmentRegistro04(){
        //AddFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewRegistro, Registro04Fragment(),"registro 3")
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