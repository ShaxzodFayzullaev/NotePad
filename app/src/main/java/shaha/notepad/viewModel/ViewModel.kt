package shaha.notepad.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.launch
import shaha.notepad.db.UserDatabase
import shaha.notepad.db.UserEntity

class ViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var allUsers: MutableLiveData<List<UserEntity>?>

    init {
       allUsers= MutableLiveData()

    }

    fun insertUserInfo(entity: UserEntity) = viewModelScope.launch {
        val userDao = UserDatabase.getDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
        getAllUser()
    }


    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    fun getAllUser() {
        val userDao = UserDatabase.getDatabase(getApplication())?.userDao()
        val list = userDao?.getAllUserInfo()


        allUsers.postValue(list)
    }



}