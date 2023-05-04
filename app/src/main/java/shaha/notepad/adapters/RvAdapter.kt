import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shaha.notepad.R
import shaha.notepad.adapters.RvEvent
import shaha.notepad.databinding.ItemRv1Binding
import shaha.notepad.db.UserEntity

class RvAdapter(var list: List<UserEntity>,var rvEvent: RvEvent) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRvBinding: ItemRv1Binding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(userEntity: UserEntity, position: Int) {
            itemRvBinding.tvName.text = userEntity.userName
            itemRvBinding.tvQiymat1.text = userEntity.qiymat.toString()
            itemRvBinding.tvIzoh.text = userEntity.izoh
            itemRvBinding.tvDate.text = userEntity.data

            itemRvBinding.deleteId.setOnClickListener {
                rvEvent.menuClick2(userEntity, R.id.deleteId)
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<UserEntity>){
        list.clear()
        list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRv1Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}
