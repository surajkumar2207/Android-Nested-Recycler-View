package com.example.nestedrecyclerview1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ParentRecyclerViewAdapter(var listSubject: MutableList<StudyModel> ,var mContext: Context): RecyclerView.Adapter<ParentRecyclerViewAdapter.ViewHolderParent>(), Filterable {

    var backup:  MutableList<StudyModel> = listSubject.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderParent {
        var view = LayoutInflater.from(mContext).inflate(R.layout.parent_rowview, parent, false)
        return ViewHolderParent(view);
    }



    override fun getItemCount(): Int {
        return listSubject.size
    }

    override fun onBindViewHolder(holder: ViewHolderParent, position: Int) {
        holder.bind(listSubject[position], mContext, this)


    }


    class ViewHolderParent(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var downArrow: ImageView
        lateinit var subjectLine: TextView
        lateinit var childRecyclerView: RecyclerView

       fun bind(studyModel: StudyModel, mContext: Context, recyclerViewAdapter: ParentRecyclerViewAdapter){
           downArrow = itemView.findViewById(R.id.down_arrow_img)
           subjectLine = itemView.findViewById(R.id.subject_heading)
           childRecyclerView = itemView.findViewById(R.id.child_recyclerview)


//           childRecyclerView.setHasFixedSize(true)
           subjectLine.text = studyModel.subject
           if(studyModel.toggle == false){
               downArrow.setImageResource(R.drawable.down_arrow)
               childRecyclerView.visibility = View.GONE}
//           }else
//           {
//               downArrow.setImageResource(R.drawable.up_arrow)
//               childRecyclerView.visibility = View.VISIBLE
//               studyModel.toggle = true
//           }


           downArrow.setOnClickListener {
               if(studyModel.toggle == true){
                   studyModel.toggle = false
                   downArrow.setImageResource(R.drawable.down_arrow)
                   childRecyclerView.visibility = View.GONE
               }else{
                   studyModel.toggle = true
                   downArrow.setImageResource(R.drawable.up_arrow)
                   childRecyclerView.adapter = ChildRowAdapter(studyModel.topics, mContext)
                   childRecyclerView.layoutManager = LinearLayoutManager(mContext)
                   childRecyclerView.setHasFixedSize(false)
                   recyclerViewAdapter.notifyDataSetChanged()
                   childRecyclerView.visibility = View.VISIBLE
               }
           }




       }

    }


    private val filterMy = object : Filter() {

        override fun performFiltering(keyword: CharSequence?): FilterResults {
            var filteredData = mutableListOf<StudyModel>()
            if(keyword?.toString()?.isEmpty() == true)
                filteredData.addAll(backup)
            else{
                for (obj : StudyModel in backup){
                    if(obj.subject.toString().lowercase().startsWith(keyword.toString().lowercase()))
                        filteredData.add(obj)
                }
            }

            var filterResultsMy : FilterResults = FilterResults()
            filterResultsMy.values = filteredData
            return filterResultsMy
        }

        override fun publishResults(keyword: CharSequence?, results: FilterResults?) {
            listSubject.clear()
            listSubject.addAll(results?.values as MutableList<StudyModel>)
            notifyDataSetChanged()
        }

    }

    override fun getFilter(): Filter {
        return filterMy
    }

}


