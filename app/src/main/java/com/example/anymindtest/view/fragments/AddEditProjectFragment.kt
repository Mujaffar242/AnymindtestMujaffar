package com.example.anymindtest.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentAddEditEducationBinding
import com.example.anymindtest.databinding.FragmentAddEditProjectBinding
import com.example.anymindtest.viewmodel.AddEducationViewModel
import com.example.anymindtest.viewmodel.AddProjectViewModel

class AddEditProjectFragment : Fragment() {

    private lateinit var binding: FragmentAddEditProjectBinding

    private lateinit var viewModel: AddProjectViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_edit_project,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(AddProjectViewModel::class.java)

        binding.projectModel = viewModel


        viewModel.errorString.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })


        viewModel.navigateToNextScreen.observe(this, Observer {
            //  findNavController().navigate(R.id.action_peronalInfoFragment_to_workExperienceFragment)
        })

        viewModel.projectModelLiveData.observe(this, Observer {

            viewModel.projectName.value = it.projectName
            viewModel.projectSummary.value = it.projectSummery
            viewModel.role.value = it.role
            viewModel.teamSize.value=it.teamSize
            viewModel.technologyused.value=it.technologyUsed
        })


        return binding.root
    }


}