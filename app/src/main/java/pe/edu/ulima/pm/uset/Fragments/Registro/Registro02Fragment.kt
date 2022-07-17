package pe.edu.ulima.pm.uset.Fragments.Registro
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.uset.ChatActivity
import pe.edu.ulima.pm.uset.Fragments.CreateProfile.CreateProfile01Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
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
        binding.btnEnviar.setOnClickListener { btnEnviar() }
    }

    //TODO: OnViewCreated Functions ****************************************************************
    private fun btnEnviar() {
        val email = binding.tilCorreoEditText.text.toString().trim { it <= ' ' }
        //val password = binding.tilIngresarContrasenaEditText.text.toString()
        val password = "123"
        if (FirebaseClass.createUserWithEmailAndPassword(email,password,requireActivity(),requireContext())){
            startActivity(Intent(requireActivity(), CreateProfile01Fragment::class.java))
        }
    }
    //TODO: OnViewCreated Functions ****************************************************************

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseClass.updateUI(requireContext())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}