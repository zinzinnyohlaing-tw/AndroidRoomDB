package eu.hanna.roomdbdemo1

import androidx.lifecycle.LiveData
import androidx.room.*

//  Step 2
@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note :Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}