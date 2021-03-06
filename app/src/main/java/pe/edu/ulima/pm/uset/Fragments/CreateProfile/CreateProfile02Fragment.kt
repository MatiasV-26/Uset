package pe.edu.ulima.pm.uset.Fragments.CreateProfile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.edu.ulima.pm.uset.ChatActivity
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentCreateProfile02Binding

class CreateProfile02Fragment : Fragment() {


    private var _binding: FragmentCreateProfile02Binding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateProfile02Binding.inflate(inflater, container, false)
        binding

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOmitir.setOnClickListener {
            goToChatActivity()
        }
        binding.btnFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            requireActivity().startActivityForResult(intent, 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun goToChatActivity() {
        requireActivity().finish()
        startActivity(Intent(requireActivity(), ChatActivity::class.java))
    }


}