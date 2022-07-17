package pe.edu.ulima.pm.uset.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.uset.Models.MessageChat
import pe.edu.ulima.pm.uset.R

class ChatsBtwUsersAdapter:RecyclerView.Adapter<ChatsBtwUsersHolder>() {

    var a = MessageChat("Mensaje1","DASDADASD")
    var b = MessageChat("Mensaje2","DASDADASD")
    var c = MessageChat("Mensaje3","DASDADASD")

    var messageList: List<MessageChat> = listOf(a,b,c)

    override fun onCreateViewHolder(parent: ViewGroup
                                    , viewType: Int
    ): ChatsBtwUsersHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatsBtwUsersHolder(layoutInflater.inflate(R.layout.chat_users,
        parent,
        false))
    }

    override fun onBindViewHolder(holder: ChatsBtwUsersHolder, position: Int) {
        val msg = messageList[position]

        holder.render(msg)
    }

    override fun getItemCount(): Int = messageList.size


}