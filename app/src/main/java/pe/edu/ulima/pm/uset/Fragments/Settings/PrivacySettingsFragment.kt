package pe.edu.ulima.pm.uset.Fragments.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        binding.ivHideInfoToArrow.setOnClickListener{onHidePersonalInfoPressed() }

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

}