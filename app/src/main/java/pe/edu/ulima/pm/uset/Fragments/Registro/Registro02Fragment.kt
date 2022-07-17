package pe.edu.ulima.pm.uset.Fragments.Registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro01Binding
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro02Binding

class Registro02Fragment : Fragment() {


    private var _binding: FragmentRegistro02Binding? = null
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
        _binding = FragmentRegistro02Binding.inflate(inflater, container, false)
        var correo = binding.tilCorreoEditText.text
        binding.btnEnviar.setOnClickListener {
            if(correo!!.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo.toString(), "123")
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}