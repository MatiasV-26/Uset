package pe.edu.ulima.pm.uset.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.ListChatBinding

class ChatUsersHolder(view:View) :RecyclerView.ViewHolder(view){
    val binding = ListChatBinding.bind(view)

    fun render(chat: UserChat){
        binding.textNombreUsuarioChat.text = chat.name
        binding.textUltimoChatHora.text = chat.lastMsgDate.toString()
        binding.textUltimoChat.text = chat.lastMsg
        binding.imageView.setImageResource(R.drawable.download)
    }
}