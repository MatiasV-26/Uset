package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.grpc.util.AdvancedTlsX509TrustManager
import pe.edu.ulima.pm.uset.Fragments.CreateProfile.CreateProfile01Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro02Binding
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro03Binding


class Registro04Fragment : Fragment() {


    private var _binding: FragmentRegistro03Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val user = FirebaseClass.auth2.currentUser

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
        VerificacionCorreo()
        binding.btnEnviar.setOnClickListener {
            if (user != null) {
                BotonEnviar()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun VerificacionCorreo(){
        user?.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context, "Correo Enviado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun BotonEnviar() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewRegistro, CreateProfile01Fragment(),"registro 4")
            .addToBackStack("4")
            .commit()
    }



}