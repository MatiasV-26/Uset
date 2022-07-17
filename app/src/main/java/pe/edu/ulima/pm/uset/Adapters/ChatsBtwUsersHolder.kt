package pe.edu.ulima.pm.uset.Adapters

import android.os.Message
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.uset.Models.MessageChat
import pe.edu.ulima.pm.uset.databinding.ChatUsersBinding

class ChatsBtwUsersHolder(view: View):RecyclerView.ViewHolder(view){
    val binding =ChatUsersBinding.bind(view)

    fun render (msg : MessageChat,userId : String){
        if(userId == msg.fromID){
            binding.textMessageSend.visibility = View.VISIBLE
            binding.textMessageReceived.visibility = View.GONE
            binding.textMessageSend.text = msg.content
        }else{
            binding.textMessageSend.visibility = View.GONE
            binding.textMessageReceived.visibility = View.VISIBLE
            binding.textMessageReceived.text = msg.content
        }

    }



}