package com.example.anymindtest.view.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.anymindtest.R
import com.example.anymindtest.databinding.EducationItemBinding
import com.example.anymindtest.model.EducationModel
import com.example.anymindtest.utils.EDIT_INFO
import com.example.anymindtest.utils.PRINT_INFO


/**
 * RecyclerView Adapter for show education list
 */

class EducationListAdapter() :
    ListAdapter<EducationModel, EducationViewHolder>(EucationDiffCallback()) {

    //function on edit button click
    var editEucation: ((Int) -> Unit)? = null

    var pageType= EDIT_INFO


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        return EducationViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.educationModel = getItem(position)
        }


        holder.viewDataBinding.edit.setOnClickListener {

            editEucation?.invoke(getItem(position).id)
        }

        if (pageType.equals(PRINT_INFO))
            holder.viewDataBinding.edit.visibility=View.GONE

        holder.bind()


    }


}


/**
 * ViewHolder for DevByte items. All work is done by data binding.
 */
class EducationViewHolder private constructor(val viewDataBinding: EducationItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.education_item

        public fun from(parent: ViewGroup): EducationViewHolder {
            val withDataBinding: EducationItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                LAYOUT,
                parent,
                false
            )
            return EducationViewHolder(withDataBinding)
        }


    }


    public fun bind(
    ) {

    }

}


class EucationDiffCallback :
    DiffUtil.ItemCallback<EducationModel>() {
    override fun areItemsTheSame(
        oldItem:EducationModel,
        newItem: EducationModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EducationModel,
        newItem: EducationModel
    ): Boolean {
        return oldItem == newItem;
    }
}