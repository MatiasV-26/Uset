package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.edu.ulima.pm.uset.Fragments.Chats.ChatBtwUsers
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentLogin01Binding
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro01Binding

class Registro01Fragment : Fragment() {


    private var _binding: FragmentRegistro01Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistro01Binding.inflate(inflater, container, false)
        binding.btnIngresar.setOnClickListener { BotonIngresar() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun BotonIngresar() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewRegistro, Registro02Fragment(),"registro 2")
            .addToBackStack("2")
            .commit()
    }
}