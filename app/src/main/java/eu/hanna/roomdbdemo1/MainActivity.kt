package eu.hanna.roomdbdemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(),NoteClickInterface,NoteClickDeleteInterface {

    lateinit var viewModal: NoteViewModel
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        notesRV.layoutManager = LinearLayoutManager(this)

        //on below line we are initializing our adapter class.
        val noteRVAdapter = NoteRVAdapter(this, this, this)

        //on below line we are setting adapter to our recycler view.
        notesRV.adapter = noteRVAdapter

        //on below line we are initializing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        //on below line we are calling all notes methof from our view modal class to observer the changes on list.
        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                //on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })

        addFAB.setOnClickListener {
            //adding a click listner for fab button and opening a new intent to add a new note.
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }


    }

    override fun onDeleteIconClick(note: Note) {

        //opening a new intent and passing a data to it.
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onNoteClick(note: Note) {

        //in on note click method we are calling delete method from our viw modal to delete our not.
        viewModal.deleteNote(note)
        //displaying a toast message
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()

    }
}