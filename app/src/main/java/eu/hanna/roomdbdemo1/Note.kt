package eu.hanna.roomdbdemo1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//  Step 1
@Entity(tableName = "notesTable")
class Note(
    @ColumnInfo(name = "title")val noteTitle:String,
    @ColumnInfo(name = "description")val noteDescription :String,
    @ColumnInfo(name = "timestamp")val timeStamp :String) {

    @PrimaryKey(autoGenerate = true) var id = 0
}