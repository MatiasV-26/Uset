package pe.edu.ulima.pm.uset.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.auth.User
import pe.edu.ulima.pm.uset.Models.UserChat
import pe.edu.ulima.pm.uset.R
import pe.edu.ulima.pm.uset.databinding.ListChatBinding

class ChatUsersAdapter(val chatSelected : (UserChat)->Unit): RecyclerView.Adapter<ChatUsersHolder>() {

    var a = UserChat("SADADada","ASDADASDAS", listOf("Persona1","Persona2"))
    var b = UserChat("SADADada","ASDADASDAS", listOf("Persona2","Persona3"))

    var chatList : List<UserChat> = listOf(a,b)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatUsersHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatUsersHolder(layoutInflater.inflate(R.layout.list_chat
            ,parent,
            false))
    }

    override fun onBindViewHolder(holder: ChatUsersHolder, position: Int) {
        val chat = chatList[position]

        holder.render(chat)

        holder.itemView.setOnClickListener{
            chatSelected(chat)
        }
    }

    override fun getItemCount(): Int = chatList.size

}