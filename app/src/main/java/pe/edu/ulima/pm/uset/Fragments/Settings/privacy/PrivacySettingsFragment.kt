package pe.edu.ulima.pm.uset.Fragments.Settings.privacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.Usuario
import pe.edu.ulima.pm.uset.PrivacySettingsActivity
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentPrivacySettingsBinding

class PrivacySettingsFragment:Fragment(){
    private var _binding : FragmentPrivacySettingsBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrivacySettingsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHideInfoToOptions.setOnClickListener{onHidePersonalInfoPressed() }

        binding.conlayHideInfoFriends.setOnClickListener { onConfigPrivateData(true) }

        binding.conlayHideInfoContacts.setOnClickListener { onConfigPrivateData(false) }

        binding.ivHideInfoToArrow.setOnClickListener{onHidePersonalInfoPressed() }

        binding.swShowActivityState.setOnCheckedChangeListener{
            _ ,isChecked->onActivitySwitchChanged(isChecked)
        }

    }

    private fun onHidePersonalInfoPressed(){
        if(binding.ivHideInfoToArrow.rotation == 0f){
            binding.conlayHideInfoContacts.visibility = View.VISIBLE
            binding.conlayHideInfoFriends.visibility = View.VISIBLE
            binding.ivHideInfoToArrow.rotation = 90f
        }else{
            binding.conlayHideInfoContacts.visibility = View.GONE
            binding.conlayHideInfoFriends.visibility = View.GONE
            binding.ivHideInfoToArrow.rotation = 0f
        }
    }

    private fun onActivitySwitchChanged(activado : Boolean){
        FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
            .get().addOnSuccessListener {
                var usuario = it.toObject(Usuario::class.java)
                var nuevossettingsBasicos = mapOf(
                    "estadoActividad" to activado,
                    "notifications" to usuario!!.settingsBasicos[("notifications")]!!
                )
                FirebaseClass.db.collection("users").document(FirebaseClass.updateUI()!!)
                    .update("settingsBasicos",nuevossettingsBasicos)
            }
    }


    private fun onConfigPrivateData (isFriend : Boolean){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fcwPrivacySettings, PrivacySettingsFragment2(isFriend))
            .addToBackStack("part2Privacy")
            .commit()
    }
}