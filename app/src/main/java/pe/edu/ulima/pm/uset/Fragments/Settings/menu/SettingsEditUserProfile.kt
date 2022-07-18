package pe.edu.ulima.pm.uset.Fragments.Settings.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.SettingsMenuActivity
import pe.edu.ulima.pm.uset.databinding.FragmentSettingsMenuBinding
import pe.edu.ulima.pm.uset.databinding.FragmentUserInfoEditBinding

class SettingsEditUserProfile:Fragment() {

    private var _binding : FragmentUserInfoEditBinding? = null
    private val binding get() = _binding!!
    private var userDoc = FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoEditBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        inicializarViews()

        binding.btncancelar.setOnClickListener { requireActivity().onBackPressed() }

        binding.ivChangeNameIcon.setOnClickListener { btnEditNombre() }
        binding.ivChangeLastNameIcon.setOnClickListener { btnEditApellido() }
        binding.ivChangeLastNameIcon2.setOnClickListener { btnEditJob() }
        binding.ivChangeSex.setOnClickListener { btnEditsex() }



        binding.tilCorreoEditText.addTextChangedListener {
            enableSave()
        }
        binding.tilChangeLastNameEditText.addTextChangedListener {
            enableSave()
        }
        binding.tilChangeJobEditText.addTextChangedListener {
            enableSave()
        }


        binding.btnguardar.setOnClickListener {
            saveButton()
        }

    }

    private fun enableSave(){
        binding.btnguardar.alpha=1f
        binding.btnguardar.isEnabled = true
    }

    private fun btnEditJob(){
        binding.tilChangeJob.isEnabled = true
        binding.tilChangeJob.alpha = 1f
    }

    private fun btnEditApellido(){
        binding.tilChangeLastName.isEnabled = true
        binding.tilChangeLastName.alpha = 1f
    }

    private fun btnEditsex(){
        binding.btngrpSex.isEnabled = true
        binding.btngrpSex.isClickable = true
        binding.btngrpSex.alpha = 1f
    }

    private fun btnEditNombre(){
        binding.tilChangeName.isEnabled = true
        binding.tilChangeName.alpha = 1f
    }

    private fun saveButton(){


        userDoc.get().addOnSuccessListener {
            var usuario = it.toObject(Usuario::class.java)
            modifyAndSave(usuario!!)
            resetViews()
        }




    }

    private fun modifyAndSave(usuario : Usuario){
        if(binding.tilChangeJob.isEnabled){
            usuario.ocupacion = binding.tilChangeJobEditText.text.toString()
        }
        if(binding.tilChangeLastName.isEnabled){
            usuario.apellidos  = binding.tilChangeLastNameEditText.text.toString()
        }
        if(binding.tilChangeName.isEnabled){
            usuario.nombres = binding.tilCorreoEditText.text.toString()
        }
        /*if(binding.btngrpSex.isClickable){
            FirebaseClass.db.collection("users")
                .document(FirebaseClass.updateUI()!!).update("ocupacion")
        }*/
        userDoc.set(usuario)
    }

    private fun resetViews(){
        binding.btnguardar.alpha=0.25f
        binding.btnguardar.isEnabled = false

        binding.tilChangeJob.isEnabled = false
        binding.tilChangeJob.alpha = 0.25f

        binding.tilChangeLastName.isEnabled = false
        binding.tilChangeLastName.alpha = 0.25f

        binding.btngrpSex.isEnabled = false
        binding.btngrpSex.isClickable = false
        binding.btngrpSex.alpha = 0.25f

        binding.tilChangeName.isEnabled = false
        binding.tilChangeName.alpha = 0.25f

    }


    private fun inicializarViews(){
        userDoc.get().addOnSuccessListener {
                pintarBoton(it.data!!["sexo"].toString())
                binding.tilChangeJobEditText.hint = it.data!!["ocupacion"].toString()
                binding.tilChangeLastName.hint = it.data!!["apellidos"].toString()
                binding.tilChangeName.hint = it.data!!["nombres"].toString()
            }
    }

    private fun pintarBoton(s : String){
        if("Hombre" == s){
            binding.btngrpSex.check(R.id.btnHombre)
        }else if("Mujer" == s){
            binding.btngrpSex.check(R.id.btnMujer)
        }else{
            binding.btngrpSex.check(R.id.btnOtro)
        }
    }
}