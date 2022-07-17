package pe.edu.ulima.pm.uset.Fragments.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.ulima.pm.uset.databinding.FragmentLogin01Binding

class Login01Fragment : Fragment() {

    private var _binding: FragmentLogin01Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogin01Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnIngresar.setOnClickListener{ BtnIngresarFunction() }
        binding.tvOlvideMiContrasena.setOnClickListener { TvOlvideMiContrasena() }
        binding.tvIngresarConOtros.setOnClickListener{ TVIngresarConOtros() }
        binding.tvCrearUnaCuenta.setOnClickListener { TVCrearUnaCuenta() }
        binding.btnFacebook.setOnClickListener { BtnFacebook() }
        binding.btnGoogle.setOnClickListener { BtnGoogle() }
    }

    private fun BtnGoogle() {
        Toast.makeText(context, "BtnGoogle", Toast.LENGTH_SHORT).show()
    }

    private fun BtnFacebook() {
        Toast.makeText(context, "BtnFacebook", Toast.LENGTH_SHORT).show()
    }

    private fun TVCrearUnaCuenta() {
        Toast.makeText(context, "TVCrearUnaCuenta", Toast.LENGTH_SHORT).show()
    }

    private fun TVIngresarConOtros() {
        Toast.makeText(context, "TVIngresarConOtros", Toast.LENGTH_SHORT).show()
    }

    private fun TvOlvideMiContrasena() {
        Toast.makeText(context, "TvOlvideMiContrasena", Toast.LENGTH_SHORT).show()
    }


    private fun BtnIngresarFunction() {
        Toast.makeText(context, "BtnIngresarFunction", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}