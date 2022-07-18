package pe.edu.ulima.pm.uset.Fragments.Registro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.ulima.pm.uset.ChatActivity
import pe.edu.ulima.pm.uset.CreateProfileActivity
import pe.edu.ulima.pm.uset.Fragments.CreateProfile.CreateProfile01Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass.Companion.Correo
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro04Binding

class Registro04Fragment : Fragment() {
    //VIEW BINDING FOR FRAGMENTS
    private var _binding: FragmentRegistro04Binding? = null
    private val binding get() = _binding!!

    //ACTIVITY LIFECYCLE
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //VIEW BINDING FOR FRAGMENTS
        _binding = FragmentRegistro04Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //BUTTONS CLICK LISTENERS
        binding.btnSiguiente.setOnClickListener { BtnSiguiente()    }
        binding.btnRegresar.setOnClickListener  { BtnRegresar()     }
    }

    //OnViewCreated Functions ON CLICK LISTENERS ***************************************************
    private fun BtnSiguiente() {
        FirebaseClass.isVerifiedEmailAndSignInWithEmailAndPassword(
            RegistroActivity.correo!!,
            RegistroActivity.password!!,
            requireContext(),
            { goToChatActivity() },
            { goToCreateProfileActivity() }
        )
    }
    private fun goToChatActivity() {
        //This' parent activity must be deleted after going to Chat Activity
        requireActivity().finish()
        startActivity(Intent(requireActivity(), ChatActivity::class.java))
    }
    private fun goToCreateProfileActivity() {
        requireActivity().finish()
        startActivity(Intent(requireActivity(), CreateProfileActivity::class.java))
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