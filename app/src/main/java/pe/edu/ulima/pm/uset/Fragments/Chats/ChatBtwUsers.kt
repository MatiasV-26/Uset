package pe.edu.ulima.pm.uset.Fragments.Chats

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.uset.Adapters.ChatsBtwUsersAdapter
import pe.edu.ulima.pm.uset.Models.MessageChat
import pe.edu.ulima.pm.uset.databinding.FragmentChatBetweenUsersBinding

class ChatBtwUsers : Fragment(){

    private var _binding : FragmentChatBetweenUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var thisContext : Context
    private val db = Firebase.firestore
    private var chatId = "72df181b-4a87-46a6-8b55-a3a2dda64363"
    private var userId = "y5Rfs1mv7JRptMxLD5y0L0Qgb4y1"

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
        thisContext = this.requireActivity().applicationContext

        establishTitle()
        initRecyclerView()

        binding.buttonSend.setOnClickListener {
            pressedSend()
        }
    }

    private fun pressedSend() {
        val text = binding.editTextMessageText.text.toString()
        if(text!="") {
            val msg = MessageChat(
                text,
                userId,
            )
            db.collection("chatRooms")
                .document(chatId).collection("messages")
                .document()
                .set(msg)

            binding.editTextMessageText.setText("")
        }
    }

    private fun establishTitle(){
        db.collection("chatRooms").document(chatId).get().addOnSuccessListener {
            requireActivity().title = it.get("name").toString()
        }
    }


    private fun initRecyclerView(){
        binding.recyclerViewMessageList.layoutManager = LinearLayoutManager(thisContext)
        binding.recyclerViewMessageList.adapter = ChatsBtwUsersAdapter(userId)

        val chatDoc = db.collection("chatRooms").document(chatId)

        chatDoc.collection("messages").orderBy("date", Query.Direction.ASCENDING).get()
            .addOnSuccessListener {
                val messageList = it.toObjects(MessageChat::class.java)
                (binding.recyclerViewMessageList.adapter as ChatsBtwUsersAdapter).setData(messageList)
            }

        chatDoc.collection("messages").orderBy("date", Query.Direction.ASCENDING)
            .addSnapshotListener { messages, error ->
                if(error == null){
                    messages?.let {
                        val listMessages = it.toObjects(MessageChat::class.java)
                        (binding.recyclerViewMessageList.adapter as ChatsBtwUsersAdapter).setData(listMessages)
                    }
                }
            }

    }






    override fun onDetach() {
        super.onDetach()
        requireActivity().title = "Chats"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}