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
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentAddEditExperienceBinding
import com.example.anymindtest.viewmodel.AddWorkExperienceViewModel


class AddEditExperienceFragment : Fragment() {

    private lateinit var binding: FragmentAddEditExperienceBinding

    private lateinit var viewModel: AddWorkExperienceViewModel

    // get the arguments from previous fragment
    private val args : AddEditExperienceFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_edit_experience,
            container,
            false
        )


        viewModel = ViewModelProviders.of(this).get(AddWorkExperienceViewModel::class.java)

        binding.addworkExperienceViewModel = viewModel

        /*
        * check if id is not zero from previous page
        * and load data from room
        * */
        if(args.itemId>0)
        {
            viewModel.getWorkExperienceData(args.itemId)
        }


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

        viewModel.workExperienceLiveData.observe(this, Observer {

            viewModel.companyName.value = it.company
            viewModel.startDate.value = it.startDate
            viewModel.endDate.value = it.endDate

            binding.addworkExperienceViewModel=viewModel

        })


        return binding.root
    }


}