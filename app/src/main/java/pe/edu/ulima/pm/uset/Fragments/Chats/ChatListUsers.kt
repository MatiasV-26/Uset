package pe.edu.ulima.pm.uset.Fragments.Chats

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.ulima.pm.uset.Adapters.ChatUsersAdapter
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentChatListUsersBinding
import pe.edu.ulima.pm.uset.databinding.FragmentCreateProfile01Binding

class ChatListUsers : Fragment() {

    private var _binding : FragmentChatListUsersBinding? = null
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
        _binding = FragmentChatListUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thisContext = this.requireActivity().applicationContext

        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(){
        val manager = LinearLayoutManager(thisContext)
        binding.recyclerViewChatsUsers.layoutManager = manager
        binding.recyclerViewChatsUsers.adapter =
            ChatUsersAdapter{
                chat -> chatPressed(chat)
            }
    }

    private fun chatPressed(userchat: UserChat) {
        val idchat = userchat.id
        val user = "ADSADA"

        parentFragmentManager.setFragmentResult("idChat"
            , bundleOf("idChat" to idchat))
        parentFragmentManager.setFragmentResult("user"
            , bundleOf("user" to user))

        parentFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerViewChats,ChatBtwUsers(),"chats")
            .addToBackStack("2")
            .hide(parentFragmentManager.findFragmentByTag("listUsers")!!)
            .commit()
    }
}