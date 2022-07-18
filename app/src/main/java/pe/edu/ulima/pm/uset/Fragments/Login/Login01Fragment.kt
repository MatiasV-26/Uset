package pe.edu.ulima.pm.uset.Fragments.Login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import pe.edu.ulima.pm.uset.ChatActivity
import pe.edu.ulima.pm.uset.CreateProfileActivity
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentLogin01Binding

class Login01Fragment : Fragment() {
    //VIEW BINDING FOR FRAGMENTS
    private var _binding: FragmentLogin01Binding? = null
    private val binding get() = _binding!!

    //ACTIVITY LIFECYCLE
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //VIEW BINDING FOR FRAGMENTS
        _binding = FragmentLogin01Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //BUTTONS CLICK LISTENERS
        binding.tvOlvideMiContrasena.   setOnClickListener  { TvOlvideMiContrasena()    }
        binding.btnIngresar.            setOnClickListener  { BtnIngresar()             }
        binding.tvCrearUnaCuenta.       setOnClickListener  { TVCrearUnaCuenta()        }
    }

    //OnViewCreated Functions ON CLICK LISTENERS ***************************************************
    private fun TvOlvideMiContrasena() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Recuperar Contraseña")
        val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
        builder.setView(view)
        val correo = view.findViewById<EditText>(R.id.et_correo)
        builder.setPositiveButton("Reestablecer", DialogInterface.OnClickListener { _, _ ->
            ContraseñaOlvidada(correo)
        })
        builder.setNegativeButton("Cerrrar", DialogInterface.OnClickListener { _, _ ->  })
        builder.show()
    }
    private fun BtnIngresar() {
        //FIREBASE CLASS IMPLEMENTATION
        FirebaseClass.isVerifiedEmailAndSignInWithEmailAndPassword(
            binding.tilCorreoEditText.text.toString().trim { it <= ' ' },
            binding.tilIngresarContrasenaEditText.text.toString(),
            requireContext(),
            { goToChatActivity() },
            { goToCreateProfileActivity() }
        )
    }
    private fun TVCrearUnaCuenta() {
        goToRegistroActivity()
    }
    //OnViewCreated Functions ON CLICK LISTENERS ***************************************************

    //GO TO "ANOTHER" ACTIVITIES
    private fun goToChatActivity() {
        //This' parent activity must be deleted after going to Chat Activity
        requireActivity().finish()
        startActivity(Intent(requireActivity(), ChatActivity::class.java))
    }
    private fun goToCreateProfileActivity() {
        startActivity(Intent(requireActivity(), CreateProfileActivity::class.java))
    }
    private fun goToRegistroActivity() {
        startActivity(Intent(requireActivity(), RegistroActivity::class.java))
    }

    //ACTIVITY LIFECYCLE
    override fun onDestroyView() {
        super.onDestroyView()
        //VIEW BINDING FOR FRAGMENTS
        _binding = null
    }
    private fun ContraseñaOlvidada(correo : EditText){
        FirebaseClass.auth.sendPasswordResetEmail(correo.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(activity,"Email sent.",Toast.LENGTH_SHORT).show()
                }
            }

    }
}