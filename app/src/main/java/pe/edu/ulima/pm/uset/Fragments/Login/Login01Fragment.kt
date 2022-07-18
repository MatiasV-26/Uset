package pe.edu.ulima.pm.uset.Fragments.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import pe.edu.ulima.pm.uset.ChatActivity
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentLogin01Binding
class Login01Fragment : Fragment() {
    private var _binding: FragmentLogin01Binding? = null
    private val binding get() = _binding!!
    private val callBackManager = CallbackManager.Factory.create()
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
        //TODO
        LoginManager.getInstance().logInWithReadPermissions(requireActivity(), listOf("email"))
        LoginManager.getInstance().registerCallback(callBackManager,
            object: FacebookCallback<LoginResult>{
                override fun onSuccess(result: LoginResult) {
                    result?.let {
                        val credential = FacebookAuthProvider.getCredential(it.accessToken.token)
                        FirebaseClass.signInWithCredential(credential,requireContext())?.addOnCompleteListener(requireActivity()) { task ->
                            if(task.isSuccessful){
                                Toast.makeText(context, "correo=> ${task.result?.user?.email}", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "MAL INGRESO :( onSuccess", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                override fun onCancel() {
                    Toast.makeText(context, "MAL INGRESO :( onCancel", Toast.LENGTH_SHORT).show()
                }
                override fun onError(error: FacebookException) {
                    Toast.makeText(context, "MAL INGRESO :( onError", Toast.LENGTH_SHORT).show()
                }
            }
        )
        //TODO
    }
    private fun BtnGoogle() {
        Toast.makeText(context, "BtnGoogle", Toast.LENGTH_SHORT).show()
    }
    private fun TVCrearUnaCuenta() {
        startActivity(Intent(requireActivity(), RegistroActivity::class.java))
    }                                                               //DONE
    //TODO: onViewCreated Functions ****************************************************************

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        if (FirebaseClass.updateUI() != null){
            Toast.makeText(requireActivity(), "CONFIRMO, TENEMOS USUARIO", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireActivity(), "NO TENEMOS USUARIO :(", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}