package pe.edu.ulima.pm.uset.Adapters

import android.os.Message
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.uset.Models.MessageChat
import pe.edu.ulima.pm.uset.databinding.ChatUsersBinding

class ChatsBtwUsersHolder(view: View):RecyclerView.ViewHolder(view){
    val binding =ChatUsersBinding.bind(view)

    fun render (msg : MessageChat){
        binding.textMessageSend.text = msg.content
        binding.textMessageReceived.text = msg.content
    }



}