package pe.edu.ulima.pm.uset.Fragments.Chats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentAddFriendBinding
import pe.edu.ulima.pm.uset.databinding.FragmentInfoContactoBinding


class InfoContactoFragment : Fragment() {
    private var _binding : FragmentInfoContactoBinding? = null
    private val binding get() = _binding!!
    private val docRef = Firebase.firestore.collection("users").document(FirebaseClass.updateUI()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoContactoBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun inicializarViews(){
        docRef.get().addOnSuccessListener {
            val usuario = it.toObject(Usuario::class.java)

        }
    }

    private fun inicializarNombres(show : Boolean, info:String){
        if(show){
            binding.tvNombres.visibility = View.VISIBLE
            binding.tilNombre.visibility = View.VISIBLE
        }
    }

}