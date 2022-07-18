package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro02Binding
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro03Binding


class Registro03Fragment : Fragment() {


    private var _binding: FragmentRegistro03Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var correo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistro03Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnviar.setOnClickListener { BotonEnviar() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun BotonEnviar() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewRegistro, Registro04Fragment(),"registro 3")
            .addToBackStack("3")
            .commit()
    }


}