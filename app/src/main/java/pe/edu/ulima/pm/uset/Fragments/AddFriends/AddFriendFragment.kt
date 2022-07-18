package pe.edu.ulima.pm.uset.Fragments.AddFriends

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.uset.Fragments.Login.FirebaseClass
import pe.edu.ulima.pm.uset.Models.ChatRoom
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.databinding.FragmentAddFriendBinding
import pe.edu.ulima.pm.uset.firebase.FuncionesRandom
import java.util.*

class AddFriendFragment:Fragment() {
    private var _binding : FragmentAddFriendBinding? = null
    private val binding get() = _binding!!
    private var userID = FirebaseClass.updateUI()
    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFriendBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db.collection("users").document(userID!!).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.textUserFriendCode.text = document.data!!.get("friend_code").toString()
                } else {
                    Log.d("TAG", "No existe el documento")
                }
            }.addOnFailureListener{
                Log.d("f","Falla de conexion")
            }


        binding.buttonAddFriend.setOnClickListener {
            var friendCode = binding.editTextAAddFriend.text.toString()
            addFriendButtonAction(friendCode)
        }

        binding.buttonRoll.setOnClickListener {
            rollFriendCode()
        }

    }

    private fun addFriendButtonAction(friendCode: String) {
        val usersRef = db.collection("users")
        usersRef.whereEqualTo("friend_code",friendCode).get()
            .addOnSuccessListener {
                if(it.size()!=0){
                    for(document in it){
                        checkFriendAlready(document.id)
                    }
                }else{
                   Toast.makeText(context
                       ,"No se encontró el código en la base de datos"
                       ,Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                println("Error")
            }
    }

    private fun checkFriendAlready(friendID:String){
        db.collection("users").document(userID!!)
            .collection("friendsList").whereEqualTo("friend_Id",friendID)
            .get()
            .addOnSuccessListener {
                if(it.size()!=0){
                    Toast.makeText(context
                        ,"Ya es amigo de este usuario"
                        ,Toast.LENGTH_SHORT).show()
                }else{
                    addFriendDocument(friendID)
                    addChatWithNewFriend(friendID)
                }
            }
            .addOnFailureListener {
                println("Fallo en conexión")
            }
    }

    private fun addFriendDocument(friendID:String){
        val friend = hashMapOf(
            "friend_Id" to friendID,
            "since" to Date(),
        )

        val friendTo = hashMapOf(
        "friend_Id" to userID,
        "since" to Date(),
        )

        var usersCollectionRef = db.collection("users")
        usersCollectionRef.document(userID!!)
            .collection("friendsList")
            .document().set(friend)
            .addOnSuccessListener {
                println("Agregado exitosamente")
                Toast.makeText(context
                    ,"Amigo agregado correctamente"
                    ,Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                println("Error en carga")
            }

        usersCollectionRef.document(friendID)
            .collection("friendsList")
            .document().set(friendTo)
            .addOnSuccessListener {
                println("Agregado exitosamente")
            }
            .addOnFailureListener {
                println("Error en carga")
            }
    }

    private fun addChatWithNewFriend(friendID:String){
        val IDChat = UUID.randomUUID().toString()
        var name = "Chat con "

        db.collection("users").document(userID!!).get()
            .addOnSuccessListener {
                name = name + it.data!!.get("nombres")
                val newChatWithUser = UserChat(
                    id = IDChat,
                    name = name,
                    users = listOf(userID!!,friendID)
                )
                addToUserChatCollection(friendID,newChatWithUser)
            }

        db.collection("users").document(friendID).get()
            .addOnSuccessListener {
                name = name + it.data!!.get("nombres")
                val newChatWithUser = UserChat(
                    id = IDChat,
                    name = name,
                    users = listOf(userID!!,friendID)
                )
                addToUserChatCollection(userID!!,newChatWithUser)
            }

        val newChat = ChatRoom(
            IDChat,
            users = listOf(friendID,userID!!)
        )

        db.collection("chatRooms").document(IDChat).set(newChat)

    }

    private fun addToUserChatCollection(userID: String,newChat:UserChat){
        db.collection("users").document(userID!!).collection("chats")
            .document(newChat.id).set(newChat)
    }

    private fun rollFriendCode() {
        var code = FuncionesRandom.getRandomCode()
        db.collection("users").whereEqualTo("friend_code",code)
            .get()
            .addOnSuccessListener {
                if(it.size()==0){
                    updateFriendCode(code)
                }else{
                    rollFriendCode()
                }
            }
            .addOnFailureListener {
                println("Fallo en conexión")
            }


    }

    private fun updateFriendCode(code:String){
        db.collection("users").document(userID!!)
            .update("friend_code",code)
            .addOnSuccessListener {
                binding.textUserFriendCode.text = code
                println("Codigo actualizado exitosamente")
            }
            .addOnFailureListener{
                println("Error en update de codigo")
            }
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().title="Chats"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}