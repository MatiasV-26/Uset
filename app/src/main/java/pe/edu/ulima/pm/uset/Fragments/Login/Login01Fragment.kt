package pe.edu.ulima.pm.uset.Fragments.Login
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.ulima.pm.uset.ChatActivity
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentLogin01Binding
class Login01Fragment : Fragment() {
    private var _binding: FragmentLogin01Binding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLogin01Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOlvideMiContrasena.   setOnClickListener  { TvOlvideMiContrasena()    }
        binding.btnIngresar.            setOnClickListener  { BtnIngresar()             }
        binding.btnFacebook.            setOnClickListener  { BtnFacebook()             }
        binding.btnGoogle.              setOnClickListener  { BtnGoogle()               }
        binding.tvCrearUnaCuenta.       setOnClickListener  { TVCrearUnaCuenta()        }
    }

    //TODO: OnViewCreated Functions ****************************************************************
    private fun TvOlvideMiContrasena() {
        Toast.makeText(context, "Implementacion futura ...", Toast.LENGTH_SHORT).show()
    }
    private fun BtnIngresar() {
        val email = binding.tilCorreoEditText.text.toString().trim { it <= ' ' }
        val password = binding.tilIngresarContrasenaEditText.text.toString()
        FirebaseClass.signInWithEmailAndPassword(email,password,requireContext())?.addOnCompleteListener(requireActivity()) {
            FirebaseClass.updateUI(requireContext())
            if (it.isSuccessful){
                requireActivity().finish()
                startActivity(Intent(requireActivity(), ChatActivity::class.java))
            }
            else {
                Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
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
        startActivity(Intent(requireActivity(), RegistroActivity::class.java))
    }                                                               //DONE
    //TODO: onViewCreated Functions ****************************************************************

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