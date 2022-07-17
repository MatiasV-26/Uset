package pe.edu.ulima.pm.uset.Fragments.Chats

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.ulima.pm.uset.Adapters.ChatsBtwUsersAdapter
import pe.edu.ulima.pm.uset.databinding.FragmentChatBetweenUsersBinding

class ChatBtwUsers : Fragment(){

    private var _binding : FragmentChatBetweenUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var thisContext : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBetweenUsersBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "XDDDDDDD"
        thisContext = this.requireActivity().applicationContext
        binding.recyclerViewMessageList.layoutManager = LinearLayoutManager(thisContext)
        binding.recyclerViewMessageList.adapter = ChatsBtwUsersAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}