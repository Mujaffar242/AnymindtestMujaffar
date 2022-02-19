package com.example.anymindtest.view.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.anymindtest.R
import com.example.anymindtest.databinding.WorkExperienceItemBinding
import com.example.anymindtest.model.WorkExperienceModel
import com.example.anymindtest.utils.EDIT_INFO
import com.example.anymindtest.utils.PRINT_INFO


/**
 * RecyclerView Adapter for show work experience list
 */

class ExperienceListAdapter() :
    ListAdapter<WorkExperienceModel, WorkExperienceViewHolder>(WorkExperienceDiffCallback()) {

    //function on edit button click
    var editWorkExperience: ((Int) -> Unit)? = null

    var pageType= EDIT_INFO

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkExperienceViewHolder {
        return WorkExperienceViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: WorkExperienceViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.workExperienceModel = getItem(position)
        }


        holder.viewDataBinding.edit.setOnClickListener {

            editWorkExperience?.invoke(getItem(position).id)
        }

        if (pageType.equals(PRINT_INFO))
            holder.viewDataBinding.edit.visibility= View.GONE

        holder.bind()


    }


}


/**
 * ViewHolder for DevByte items. All work is done by data binding.
 */
class WorkExperienceViewHolder private constructor(val viewDataBinding: WorkExperienceItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.work_experience_item

        public fun from(parent: ViewGroup): WorkExperienceViewHolder {
            val withDataBinding: WorkExperienceItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                LAYOUT,
                parent,
                false
            )
            return WorkExperienceViewHolder(withDataBinding)
        }


    }


    public fun bind(
    ) {

    }

}


class WorkExperienceDiffCallback :
    DiffUtil.ItemCallback<WorkExperienceModel>() {
    override fun areItemsTheSame(
        oldItem:WorkExperienceModel,
        newItem: WorkExperienceModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WorkExperienceModel,
        newItem: WorkExperienceModel
    ): Boolean {
        return oldItem == newItem;
    }
}