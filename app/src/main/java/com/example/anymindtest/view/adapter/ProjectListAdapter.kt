package com.example.anymindtest.view.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.anymindtest.R
import com.example.anymindtest.databinding.ProjectItemBinding
import com.example.anymindtest.model.ProjectModel


/**
 * RecyclerView Adapter for show work experience list
 */

class ProjectListAdapter() :
    ListAdapter<ProjectModel, ProjectViewHolder>(ProjectDiffCallback()) {

    //function on edit button click
    var editWorkExperience: ((Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.projectModel = getItem(position)
        }


        holder.viewDataBinding.root.setOnClickListener {

            editWorkExperience?.invoke(position)
        }

        holder.bind()


    }


}


/**
 * ViewHolder for DevByte items. All work is done by data binding.
 */
class ProjectViewHolder private constructor(val viewDataBinding: ProjectItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.work_experience_item

        public fun from(parent: ViewGroup): ProjectViewHolder {
            val withDataBinding: ProjectItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                LAYOUT,
                parent,
                false
            )
            return ProjectViewHolder(withDataBinding)
        }


    }


    public fun bind(
    ) {

    }

}


class ProjectDiffCallback :
    DiffUtil.ItemCallback<ProjectModel>() {
    override fun areItemsTheSame(
        oldItem:ProjectModel,
        newItem: ProjectModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ProjectModel,
        newItem: ProjectModel
    ): Boolean {
        return oldItem == newItem;
    }
}