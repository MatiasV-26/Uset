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
import pe.edu.ulima.pm.uset.CreateProfileActivity
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentLogin01Binding

class Login01Fragment : Fragment() {
    //VIEW BINDING FOR FRAGMENTS
    private var _binding: FragmentLogin01Binding? = null
    private val binding get() = _binding!!
    //FACEBOOK CALLBACKMANAGER
    private val callBackManager = CallbackManager.Factory.create()

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
        binding.btnFacebook.            setOnClickListener  { BtnFacebook()             }
        binding.btnGoogle.              setOnClickListener  { BtnGoogle()               }
        binding.tvCrearUnaCuenta.       setOnClickListener  { TVCrearUnaCuenta()        }
    }

    //OnViewCreated Functions ON CLICK LISTENERS ***************************************************
    private fun TvOlvideMiContrasena() {
        Toast.makeText(context, "Implementacion futura ...", Toast.LENGTH_SHORT).show()
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        //VIEW BINDING FOR FRAGMENTS
        _binding = null
    }
}