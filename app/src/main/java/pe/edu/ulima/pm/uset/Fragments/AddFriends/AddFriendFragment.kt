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
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.databinding.FragmentAddFriendBinding
import java.util.*

class AddFriendFragment:Fragment() {
    private var _binding : FragmentAddFriendBinding? = null
    private val binding get() = _binding!!
    private var userID = "y5Rfs1mv7JRptMxLD5y0L0Qgb4y1"
    private val db = Firebase.firestore
    private val charset = ('a'..'z')+('A'..'Z')+('0'..'9')
    private var code = ""


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
        requireActivity().title = "Añadir Amigos"

        db.collection("users").document(userID).get()
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
        db.collection("users").document(userID)
            .collection("friendsList").whereEqualTo("friend_Id",friendID)
            .get()
            .addOnSuccessListener {
                if(it.size()!=0){
                    println("")
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
        usersCollectionRef.document(userID)
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
        val name = "Chat de prueba"

        val newChat = UserChat(
            UUID.randomUUID().toString(),
            name,
            listOf(friendID,userID)
        )

        db.collection("chatRooms").document(newChat.id).set(newChat)
        db.collection("users").document(userID).collection("chats")
            .document(newChat.id).set(newChat)
        db.collection("users").document(friendID).collection("chats")
            .document(newChat.id).set(newChat)

    }

    private fun rollFriendCode() {
        code = getRandomCode()
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
        db.collection("users").document(userID)
            .update("friend_code",code)
            .addOnSuccessListener {
                binding.textUserFriendCode.text = code
                println("Codigo actualizado exitosamente")
            }
            .addOnFailureListener{
                println("Error en update de codigo")
            }
    }

    private fun getRandomCode():String{
        return (1..15).map { charset.random() }.joinToString("")
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