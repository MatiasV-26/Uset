package pe.edu.ulima.pm.uset.Fragments.CreateProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentCreateProfile01Binding
import pe.edu.ulima.pm.uset.firebase.FuncionesRandom


class CreateProfile01Fragment : Fragment() {

    private var _binding: FragmentCreateProfile01Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var correo : String = ""
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateProfile01Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSiguiente.setOnClickListener {
            ActualizarUsuario()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun ObtenerSexo() : String{
        with(binding){
            return when(btngrpSex.checkedButtonId){
                R.id.btnHombre -> btnHombre.text.toString()
                R.id.btnMujer -> btnMujer.text.toString()
                else -> btnOtro.text.toString()

            }
        }
    }
    private fun ActualizarUsuario(){
        var id = FirebaseClass.updateUI().toString()
        var nombre = binding.tilNombreEditText.text.toString()
        var apellidos = binding.tilApellidosEditText.text.toString()
        var ocupacion = binding.tilOcupacionEditText.text.toString()
        var friendcode = FuncionesRandom.getRandomCode()
        correo = FirebaseClass.Correo().toString()
        if (nombre.isNotEmpty() &&
            apellidos.isNotEmpty() &&
            ocupacion.isNotEmpty()){
            var usuario = Usuario(id, nombre, apellidos, correo, ObtenerSexo(), ocupacion, friend_code = friendcode)
            db.collection("users").document(id).set(
                usuario)
            BotonEnviar()
        }else{
            Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
        }

    }
    private fun BotonEnviar() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewCreateProfile, CreateProfile02Fragment(),"Create Profile 2")
            .addToBackStack("2")
            .commit()
    }


}