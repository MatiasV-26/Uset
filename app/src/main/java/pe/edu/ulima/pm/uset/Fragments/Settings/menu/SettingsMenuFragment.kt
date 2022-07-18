package pe.edu.ulima.pm.uset.Fragments.Settings.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.PrivacySettingsActivity
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentSettingsMenuBinding

class SettingsMenuFragment: Fragment(){

    private var _binding : FragmentSettingsMenuBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsMenuBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.conlayOptionEditAccount.setOnClickListener{ onEditAccountPressed()}

        binding.conlayOptionEditSecurity.setOnClickListener{ onPrivacySettingPressed() }

        binding.conlayEditNotifications.setOnClickListener{ onNotificationSettingPressed() }

        binding.swActivateNotifications.setOnCheckedChangeListener{ _ , isChecked ->
            onNotificationSwitchChanged(isChecked)
        }
    }

    private fun onEditAccountPressed(){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fcwSettingsMenu,SettingsEditUserProfile())
            .addToBackStack(null)
            .commit()
    }

    private fun onNotificationSettingPressed(){
        if(binding.ivSettingsNotificationArrow.rotation == 0f){
            binding.conlayActivateNotification.visibility = View.VISIBLE
            binding.ivSettingsNotificationArrow.rotation = 90F
        }else{
            binding.conlayActivateNotification.visibility = View.GONE
            binding.ivSettingsNotificationArrow.rotation = 0f
        }
    }


    private fun onNotificationSwitchChanged(activado : Boolean){
        FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
            .get().addOnSuccessListener {
                var usuario = it.toObject(Usuario::class.java)
                var nuevossettingsBasicos = mapOf(
                    "notifications" to activado,
                    "estadoActividad" to usuario!!.settingsBasicos.get("estadoActividad")!!
                )
                FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                    .update("settingsBasicos",nuevossettingsBasicos)
            }
    }

    private fun onPrivacySettingPressed(){
        var intent = Intent(requireActivity(), PrivacySettingsActivity::class.java)
        startActivity(intent)
    }

}