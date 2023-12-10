package com.example.nestedrecyclerview1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var parentRecyclerView : RecyclerView

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
        var adapterParent: ParentRecyclerViewAdapter = ParentRecyclerViewAdapter(listOfStudyModel, this)
        parentRecyclerView.adapter = adapterParent
        parentRecyclerView.layoutManager = LinearLayoutManager(this)



    }
}