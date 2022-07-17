package pe.edu.ulima.pm.uset.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.uset.Models.MessageChat
import pe.edu.ulima.pm.uset.R

class ChatsBtwUsersAdapter(private val userId : String):RecyclerView.Adapter<ChatsBtwUsersHolder>() {

    var messageList: List<MessageChat> = listOf()

    fun setData(messages : List<MessageChat>){
        messageList = messages
        notifyDataSetChanged()
    }

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

        holder.render(msg,userId)
    }

    override fun getItemCount(): Int = messageList.size


}