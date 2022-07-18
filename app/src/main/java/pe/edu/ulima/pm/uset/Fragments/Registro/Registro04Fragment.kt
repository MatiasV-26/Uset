package pe.edu.ulima.pm.uset.Fragments.Registro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.edu.ulima.pm.uset.CreateProfileActivity
import pe.edu.ulima.pm.uset.Fragments.CreateProfile.CreateProfile01Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.RegistroActivity
import pe.edu.ulima.pm.uset.databinding.FragmentRegistro04Binding

class Registro04Fragment : Fragment() {

    private var _binding: FragmentRegistro04Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        _binding = FragmentRegistro04Binding.inflate(inflater, container, false)
//        return binding.root
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        VerificacionCorreo()
//        binding.btnSiguiente.setOnClickListener {
//            if (user != null) {
//                FirebaseClass
//                    .isVerifiedEmailAndSignInWithEmailAndPassword(
//                        RegistroActivity.correo!!,
//                        RegistroActivity.password!!,
//                        requireContext(),
//                        { goToChatActivity() }
//                    )?.addOnCompleteListener(requireActivity()){
//                        if (it.isSuccessful){
//                            if(FirebaseClass.auth.currentUser!!.isEmailVerified) {
//                                Toast.makeText(context, "USUARIO VERIFICADO", Toast.LENGTH_SHORT).show()
//                                goToChatActivity()
//                            }else{
//                                Toast.makeText(context, "USUARIO NNOO VERIFICADO", Toast.LENGTH_SHORT).show()
//                                FirebaseClass.auth.currentUser!!.sendEmailVerification()
//                            }
//                        }else{
//                            Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//            }
//        }
//    }
//    private fun goToChatActivity() {
//        requireActivity().finish()
//        startActivity(Intent(requireActivity(), CreateProfileActivity::class.java))
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//    private fun VerificacionCorreo(){
//        user?.sendEmailVerification()?.addOnCompleteListener {
//            if (it.isSuccessful){
//                Toast.makeText(context, "Correo Enviado", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//    private fun BotonEnviar() {
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainerViewRegistro, CreateProfile01Fragment(),"registro 4")
//            .addToBackStack("4")
//            .commit()
//    }



}