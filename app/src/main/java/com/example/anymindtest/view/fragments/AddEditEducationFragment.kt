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
import com.example.anymindtest.databinding.FragmentAddEditExperienceBinding
import com.example.anymindtest.viewmodel.AddEducationViewModel
import com.example.anymindtest.viewmodel.AddWorkExperienceViewModel



class AddEditEducationFragment : Fragment() {

    private lateinit var binding: FragmentAddEditEducationBinding

    private lateinit var viewModel: AddEducationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_edit_education,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(AddEducationViewModel::class.java)

        binding.educationviewModel = viewModel


        viewModel.errorString.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })


        viewModel.navigateToNextScreen.observe(this, Observer {
            //  findNavController().navigate(R.id.action_peronalInfoFragment_to_workExperienceFragment)
        })

        viewModel.educationModelLiveData.observe(this, Observer {

            viewModel.className.value = it.className
            viewModel.passingYear.value = it.passingYear
            viewModel.percentage.value = it.percentage

        })


        return binding.root
    }


}