package shaha.notepad.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var appDataBase: UserDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): UserDatabase {

            if (appDataBase == null) {
                appDataBase =
                    Room.databaseBuilder(context, UserDatabase::class.java, "shakha")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            return appDataBase!!
        }

    }
}