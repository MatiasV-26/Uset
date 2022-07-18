package pe.edu.ulima.pm.uset.Fragments.Registro
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro02Binding

class Registro02Fragment : Fragment() {
    private var _binding: FragmentRegistro02Binding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentRegistro02Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnviar.setOnClickListener { BotonEnviar() }
    }



    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
       // FirebaseClass.updateUI(requireContext())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun BotonEnviar() {
        if(binding.tilCorreoEditText.text!!.isNotEmpty()){
            RegistroActivity.correo = binding.tilCorreoEditText.text.toString().trim { it <= ' ' }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerViewRegistro, Registro03Fragment(),"registro 3")
                .addToBackStack("3")
                .commit()
        }else{
            Toast.makeText(context, "Ingrese su correo", Toast.LENGTH_SHORT).show()
        }

    }
}