package pe.edu.ulima.pm.uset.Fragments.Settings.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.uset.databinding.FragmentSettingsMenuBinding
import pe.edu.ulima.pm.uset.databinding.FragmentUserInfoEditBinding

class SettingsEditUserProfile:Fragment() {

    private var _binding : FragmentUserInfoEditBinding? = null
    private val binding get() = _binding!!


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
}