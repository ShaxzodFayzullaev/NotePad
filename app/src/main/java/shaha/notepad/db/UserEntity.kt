package shaha.notepad.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shakha")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "userName") var userName: String? = null,
    @ColumnInfo(name = "qiymat") var qiymat: Int = 0,
    @ColumnInfo(name = "izoh") var izoh: String? = null,
    @ColumnInfo(name = "data") var data: String? = null,
)
