package pe.edu.ulima.pm.uset.Fragments.Settings.privacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.databinding.FragmentPrivacySettings2Binding
import pe.edu.ulima.pm.uset.databinding.FragmentPrivacySettingsBinding

class PrivacySettingsFragment2(valor : Boolean) : Fragment(){
    private var _binding : FragmentPrivacySettings2Binding?=null
    private val binding get() = _binding!!
    private val valor = valor


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrivacySettings2Binding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(valor){
            binding.tvHideInfoToContacts.visibility = View.GONE
            binding.tvHideInfoToFriends.visibility = View.VISIBLE
        }else{
            binding.tvHideInfoToContacts.visibility = View.VISIBLE
            binding.tvHideInfoToFriends.visibility = View.GONE
        }

        actualizarViews()

        binding.swHideName.setOnCheckedChangeListener{_,isChecked->onNombresSwitchChanged(isChecked)}

    }


    private fun actualizarViews(){
        FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
            .get().addOnSuccessListener {
                var usuario = it.toObject(Usuario::class.java)
                if(valor){
                    actualizarSwitchApellidos(usuario!!.settingsPrivacidadAmigos["apellidos"]!!)
                    actualizarSwitchCorreo(usuario!!.settingsPrivacidadAmigos["correo"]!!)
                    actualizarSwitchNombres(usuario!!.settingsPrivacidadAmigos["nombre"]!!)
                    actualizarSwitchOcupacion(usuario!!.settingsPrivacidadAmigos["ocupacion"]!!)
                    actualizarSwitchSexo(usuario!!.settingsPrivacidadAmigos["sexo"]!!)
                }else{
                    actualizarSwitchApellidos(usuario!!.settingsPrivacidadContactos["apellidos"]!!)
                    actualizarSwitchCorreo(usuario!!.settingsPrivacidadContactos["correo"]!!)
                    actualizarSwitchNombres(usuario!!.settingsPrivacidadContactos["nombre"]!!)
                    actualizarSwitchOcupacion(usuario!!.settingsPrivacidadContactos["ocupacion"]!!)
                    actualizarSwitchSexo(usuario!!.settingsPrivacidadContactos["sexo"]!!)
                }
            }
    }

    private fun onNombresSwitchChanged(activado : Boolean){
        if(valor){
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "nombre" to activado,
                        "apellidos" to usuario!!.settingsPrivacidadAmigos["apellidos"]!!,
                        "correo" to usuario!!.settingsPrivacidadAmigos["correo"]!!,
                        "ocupacion" to usuario!!.settingsPrivacidadAmigos["ocupacion"]!!,
                        "sexo" to usuario!!.settingsPrivacidadAmigos["sexo"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadAmigos",nuevossettingsAmigos)
                }
        }else{
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "nombre" to activado,
                        "apellidos" to usuario!!.settingsPrivacidadContactos["apellidos"]!!,
                        "correo" to usuario!!.settingsPrivacidadContactos["correo"]!!,
                        "ocupacion" to usuario!!.settingsPrivacidadContactos["ocupacion"]!!,
                        "sexo" to usuario!!.settingsPrivacidadContactos["sexo"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadContactos",nuevossettingsAmigos)
                }
        }
    }

    private fun onApellidosSwitchChanged(activado : Boolean){
        if(valor){
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "apellidos" to activado,
                        "nombre" to usuario!!.settingsPrivacidadAmigos["nombre"]!!,
                        "correo" to usuario!!.settingsPrivacidadAmigos["correo"]!!,
                        "ocupacion" to usuario!!.settingsPrivacidadAmigos["ocupacion"]!!,
                        "sexo" to usuario!!.settingsPrivacidadAmigos["sexo"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadAmigos",nuevossettingsAmigos)
                }
        }else{
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "apellidos" to activado,
                        "nombre" to usuario!!.settingsPrivacidadContactos["nombre"]!!,
                        "correo" to usuario!!.settingsPrivacidadContactos["correo"]!!,
                        "ocupacion" to usuario!!.settingsPrivacidadContactos["ocupacion"]!!,
                        "sexo" to usuario!!.settingsPrivacidadContactos["sexo"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadContactos",nuevossettingsAmigos)
                }
        }
    }

    private fun onSexSwitchChanged(activado : Boolean){
        if(valor){
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "sexo" to activado,
                        "apellidos" to usuario!!.settingsPrivacidadAmigos["apellidos"]!!,
                        "correo" to usuario!!.settingsPrivacidadAmigos["correo"]!!,
                        "ocupacion" to usuario!!.settingsPrivacidadAmigos["ocupacion"]!!,
                        "nombre" to usuario!!.settingsPrivacidadAmigos["nombre"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadAmigos",nuevossettingsAmigos)
                }
        }else{
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "sexo" to activado,
                        "apellidos" to usuario!!.settingsPrivacidadContactos["apellidos"]!!,
                        "correo" to usuario!!.settingsPrivacidadContactos["correo"]!!,
                        "ocupacion" to usuario!!.settingsPrivacidadContactos["ocupacion"]!!,
                        "nombre" to usuario!!.settingsPrivacidadContactos["nombre"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadContactos",nuevossettingsAmigos)
                }
        }
    }

    private fun onOcupacionSwitchChanged(activado : Boolean){
        if(valor){
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "ocupacion" to activado,
                        "apellidos" to usuario!!.settingsPrivacidadAmigos["apellidos"]!!,
                        "correo" to usuario!!.settingsPrivacidadAmigos["correo"]!!,
                        "nombre" to usuario!!.settingsPrivacidadAmigos["nombre"]!!,
                        "sexo" to usuario!!.settingsPrivacidadAmigos["sexo"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadAmigos",nuevossettingsAmigos)
                }
        }else{
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "ocupacion" to activado,
                        "apellidos" to usuario!!.settingsPrivacidadContactos["apellidos"]!!,
                        "correo" to usuario!!.settingsPrivacidadContactos["correo"]!!,
                        "nombre" to usuario!!.settingsPrivacidadContactos["nombre"]!!,
                        "sexo" to usuario!!.settingsPrivacidadContactos["sexo"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadContactos",nuevossettingsAmigos)
                }
        }
    }

    private fun onCorreoSwitchChanged(activado : Boolean){
        if(valor){
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "correo" to activado,
                        "apellidos" to usuario!!.settingsPrivacidadAmigos["apellidos"]!!,
                        "nombre" to usuario!!.settingsPrivacidadAmigos["nombre"]!!,
                        "ocupacion" to usuario!!.settingsPrivacidadAmigos["ocupacion"]!!,
                        "sexo" to usuario!!.settingsPrivacidadAmigos["sexo"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadAmigos",nuevossettingsAmigos)
                }
        }else{
            FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                .get().addOnSuccessListener {
                    var usuario = it.toObject(Usuario::class.java)
                    var nuevossettingsAmigos = mapOf(
                        "correo" to activado,
                        "apellidos" to usuario!!.settingsPrivacidadContactos["apellidos"]!!,
                        "nombre" to usuario!!.settingsPrivacidadContactos["nombre"]!!,
                        "ocupacion" to usuario!!.settingsPrivacidadContactos["ocupacion"]!!,
                        "sexo" to usuario!!.settingsPrivacidadContactos["sexo"]!!
                    )
                    FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                        .update("settingsPrivacidadContactos",nuevossettingsAmigos)
                }
        }
    }




    private fun actualizarSwitchNombres(activado:Boolean){
        binding.swHideName.isChecked = activado
    }

    private fun actualizarSwitchCorreo(activado:Boolean){
        binding.swHideEmail.isChecked = activado
    }

    private fun actualizarSwitchApellidos(activado:Boolean){
        binding.swHideLastName.isChecked = activado
    }

    private fun actualizarSwitchOcupacion(activado:Boolean){
        binding.swHideJob.isChecked = activado
    }

    private fun actualizarSwitchSexo(activado:Boolean){
        binding.swHideSex.isChecked = activado
    }
}