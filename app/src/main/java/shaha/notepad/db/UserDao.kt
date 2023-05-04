package shaha.notepad.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from shakha order by id desc")
    fun getAllUserInfo(): LiveData<List<UserEntity>>

    @Insert
    fun insertUser(user:UserEntity?)

    @Delete
    fun deleteUser(user: UserEntity?)

    @Update
    fun updateUser(user: UserEntity?)
}