package com.example.nestedrecyclerview1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var parentRecyclerView : RecyclerView
    lateinit var adapterParent: ParentRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var subject1: MutableList<String> = mutableListOf<String>("PowerBi", "Tableau" , "MsExcel")
        var subject2: MutableList<String> = mutableListOf<String>("DSA", "Android Dev", "Btech Syllabus")
        var subject3: MutableList<String> = mutableListOf<String>("Stacks", "Queues", "LinkedLists")

        var studyModel1: StudyModel = StudyModel("Analytics", subject1, false)
        var studyModel2: StudyModel = StudyModel("Interviews", subject2, false)
        var studyModel3: StudyModel = StudyModel("DSA", subject3, false)

        var listOfStudyModel = mutableListOf<StudyModel>()

        listOfStudyModel.add(0, studyModel1)
        listOfStudyModel.add(1, studyModel2)
        listOfStudyModel.add(2, studyModel3)

        parentRecyclerView = findViewById(R.id.parent_recylerview)
        adapterParent = ParentRecyclerViewAdapter(listOfStudyModel, this)
        parentRecyclerView.adapter = adapterParent
        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var gridLayoutManager = GridLayoutManager(this, 2)
        parentRecyclerView.layoutManager = gridLayoutManager



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.my_main_menu,menu )
        val item: MenuItem = menu!!.findItem(R.id.my_menu_search)

        var searchView: SearchView = item.actionView as SearchView

        val queryListen = object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapterParent.filter.filter(p0)
                return false
            }

        }
        searchView.setOnQueryTextListener(queryListen)

        return super.onCreateOptionsMenu(menu)
    }
}