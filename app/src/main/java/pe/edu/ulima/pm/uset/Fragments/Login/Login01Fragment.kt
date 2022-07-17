package pe.edu.ulima.pm.uset.Fragments.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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
        binding.tvOlvideMiContrasena.setOnClickListener { TvOlvideMiContrasena() }
        binding.btnIngresar.setOnClickListener{ BtnIngresar() }
        binding.btnFacebook.setOnClickListener { BtnFacebook() }
        binding.btnGoogle.setOnClickListener { BtnGoogle() }
        binding.tvCrearUnaCuenta.setOnClickListener { TVCrearUnaCuenta() }
    }

    //TODO: IMPLEMENTAR TODAS LAS FUNCIONES DE ABAJO
    private fun TvOlvideMiContrasena() {
        Toast.makeText(context, "Implementacion futura ...", Toast.LENGTH_SHORT).show()
    }
    private fun BtnIngresar() {
        if (binding.tilCorreoEditText.text.toString().isNotEmpty()
            &&
            binding.tilIngresarContrasenaEditText.text.toString().isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.tilCorreoEditText.text.toString(),
                binding.tilIngresarContrasenaEditText.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(context, "LAS CREDENCIALES SON CORRECTAS", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "LAS CREDENCIALES SON MALAS :(", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun BtnFacebook() {
        Toast.makeText(context, "BtnFacebook", Toast.LENGTH_SHORT).show()
    }
    private fun BtnGoogle() {
        Toast.makeText(context, "BtnGoogle", Toast.LENGTH_SHORT).show()
    }
    private fun TVCrearUnaCuenta() {
        Toast.makeText(context, "TVCrearUnaCuenta", Toast.LENGTH_SHORT).show()
    }
    //TODO: IMPLEMENTAR TODAS LAS FUNCIONES DE ARRIBA

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}