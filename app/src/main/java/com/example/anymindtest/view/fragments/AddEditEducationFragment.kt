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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentAddEditEducationBinding
import com.example.anymindtest.databinding.FragmentAddEditExperienceBinding
import com.example.anymindtest.viewmodel.AddEducationViewModel
import com.example.anymindtest.viewmodel.AddWorkExperienceViewModel



class AddEditEducationFragment : Fragment() {

    private lateinit var binding: FragmentAddEditEducationBinding

    private lateinit var viewModel: AddEducationViewModel

    // get the arguments from previous fragment
    private val args : AddEditExperienceFragmentArgs by navArgs()

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


        /*
        * for show validation errors
        * */
        viewModel.errorString.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })


        /*
        * for navigate to next screen
        * */
        viewModel.navigateToNextScreen.observe(this, Observer {
              findNavController().popBackStack()

        })

        /*
        * show info from room database if available for edit
        * */
        viewModel.educationModelLiveData.observe(this, Observer {

            viewModel.className.value = it.className
            viewModel.passingYear.value = it.passingYear
            viewModel.percentage.value = it.percentage
            binding.educationviewModel=viewModel

        })


        /*
        * check if id is not zero from previous page
        * and load data from room
        * */
        if(args.itemId>0)
        {
            viewModel.getWorkEducationData(args.itemId)
        }


        return binding.root
    }


}