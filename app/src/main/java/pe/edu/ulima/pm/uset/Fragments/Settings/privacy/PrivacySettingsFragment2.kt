package pe.edu.ulima.pm.uset.Fragments.Settings.privacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    }
}