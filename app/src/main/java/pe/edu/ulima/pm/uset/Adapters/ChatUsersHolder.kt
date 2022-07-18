package pe.edu.ulima.pm.uset.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.ListChatBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatUsersHolder(view:View) :RecyclerView.ViewHolder(view){
    val binding = ListChatBinding.bind(view)

    fun render(chat: UserChat){

        val formatter = SimpleDateFormat("HH:mm")

        binding.textNombreUsuarioChat.text = chat.name
        binding.textUltimoChatHora.text = formatter.format(chat.lastMsgDate)
        binding.textUltimoChat.text = chat.lastMsg
        binding.imageView.setImageResource(R.drawable.download)
    }
}