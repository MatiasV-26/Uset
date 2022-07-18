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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.uset.Adapters.ChatUsersAdapter
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.FragmentChatListUsersBinding
import pe.edu.ulima.pm.uset.databinding.FragmentCreateProfile01Binding

class ChatListUsers : Fragment() {

    private var _binding : FragmentChatListUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var thisContext : Context
    private val db = Firebase.firestore
    private var userID = FirebaseClass.updateUI()

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

        val userDoc = db.collection("users").document(userID!!)

        userDoc.collection("chats").get()
            .addOnSuccessListener {
                val chatList = it.toObjects(UserChat::class.java)

                (binding.recyclerViewChatsUsers.adapter as ChatUsersAdapter).setData(chatList)
            }

    }

    private fun chatPressed(userchat: UserChat) {

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewChats,ChatBtwUsers(),"chats")
            .addToBackStack("2")
            .commit()
    }
}