package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro01Binding

class Registro01Fragment : Fragment() {
    //VIEW BINDING FOR FRAGMENTS
    private var _binding: FragmentRegistro01Binding? = null
    private val binding get() = _binding!!

    //ACTIVITY LIFECYCLE
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //VIEW BINDING FOR FRAGMENTS
        _binding = FragmentRegistro01Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //BUTTONS CLICK LISTENERS
        binding.btnEmail.       setOnClickListener { BtnEmail()     }
        binding.btnFacebook.    setOnClickListener { BtnFacebook()  }
        binding.btnGoogle.      setOnClickListener { BtnGoogle()    }
        binding.btnRegresar.    setOnClickListener { BtnRegresar()  }
    }

    //OnViewCreated Functions ON CLICK LISTENERS ***************************************************
    private fun BtnEmail() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewRegistro, Registro02Fragment())
            .addToBackStack("2")
            .commit()
    }
    private fun BtnFacebook() {

    }
    private fun BtnGoogle() {

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