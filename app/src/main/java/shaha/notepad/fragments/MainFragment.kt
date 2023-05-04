package shaha.notepad.fragments

import RvAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shaha.notepad.R
import shaha.notepad.adapters.RvEvent
import shaha.notepad.databinding.AddDialogBinding
import shaha.notepad.databinding.FragmentMainBinding
import shaha.notepad.db.UserDatabase
import shaha.notepad.db.UserEntity
import shaha.notepad.viewModel.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment(), RvEvent {


    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: ViewModel
    private lateinit var rvAdapter: RvAdapter
    private lateinit var rvEvent: RvEvent
    private lateinit var addDialogBinding: AddDialogBinding
    lateinit var database: UserDatabase
    private lateinit var list: ArrayList<UserEntity>


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View? {

        binding = FragmentMainBinding.inflate(layoutInflater)

        list = ArrayList()
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                rvAdapter = RvAdapter(list, rvEvent)
                binding.recyclerView.adapter = rvAdapter

            }

            viewModel = ViewModelProvider(this@MainFragment)
                .get(ViewModel::class.java)

            viewModel.getAllUsersObservers().observe(requireActivity(), Observer {
                it.let {
                    rvAdapter.updateList(it as ArrayList<UserEntity>)
                }

            })

            btnAdd.setOnClickListener {
                val addDialog =
                    LayoutInflater.from(requireContext())
                        .inflate(R.layout.add_dialog, null)
                val keyDialog =
                    androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setView(addDialog)
                        .show()
                addDialogBinding = AddDialogBinding.bind(addDialog)
                val currentDate = SimpleDateFormat(
                    "dd.MM.yyyy",
                    Locale.getDefault()
                ).format(Date())
                addDialogBinding.tvCalendar.text = currentDate
                addDialogBinding.tvAdd.setOnClickListener {

                    if (addDialogBinding.tvAdd.text.equals("Qo'shish")) {
                        val user = UserEntity(
                            userName = addDialogBinding.tvName.text.toString(),
                            qiymat = addDialogBinding.tvQiymat1.text.toString()
                                .toInt(),
                            izoh = addDialogBinding.tvIzoh.text.toString(),
                            data = addDialogBinding.tvCalendar.text.toString()

                        )
                        viewModel.insertUserInfo(user)
                        list.add(user)
                        list = rvAdapter.list as ArrayList<UserEntity>
                        keyDialog.cancel()


                        rvAdapter.notifyDataSetChanged()
                    } else {
                        val user = UserEntity(
                            addDialogBinding.tvName.getTag(addDialogBinding.tvName.id)
                                .toString()
                                .toInt()
                        )
                        viewModel.updateUserInfo(user)
                    }
                }
                keyDialog.show()


            }
        }
        return binding.root
    }

    override fun edit(userEntity: UserEntity, view: ConstraintLayout) {
    }

    override fun menuClick2(userEntity: UserEntity, view: Int) {
    }
}
//    override fun edit(userEntity: UserEntity, view: ConstraintLayout) {
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    override fun menuClick2(userEntity: UserEntity, view: View) {
//        binding.menuMore.setOnClickListener {
//            val popupMenu = PopupMenu(context, binding.menuMore)
//            popupMenu.setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.delete -> {
//                        val id = userEntity.id
//                        list.clear()
//                        viewModel.deleteUserInfo(userEntity)
//                        rvAdapter.list = list
//                        rvAdapter.notifyDataSetChanged()
//                    }
//                }
//                    return@setOnMenuItemClickListener true}
//
//            popupMenu.show()
//        }
//    }
//}
