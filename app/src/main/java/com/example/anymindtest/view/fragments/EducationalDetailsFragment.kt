package com.example.anymindtest.view.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentEducationalDetailsBinding
import com.example.anymindtest.databinding.FragmentWorkExperienceBinding
import com.example.anymindtest.view.adapter.EducationListAdapter
import com.example.anymindtest.view.adapter.ExperienceListAdapter
import com.example.anymindtest.viewmodel.EducationViewModel
import com.example.anymindtest.viewmodel.ProjectsViewModel
import com.example.anymindtest.viewmodel.WorkExperienceViewModel

class EducationalDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEducationalDetailsBinding

    private lateinit var viewModel: EducationViewModel

    private val adapter by lazy { EducationListAdapter() }


    override fun onStart() {
        super.onStart()
        activity?.setTitle("Education Details")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_educational_details,container,false)

        viewModel= ViewModelProviders.of(this).get(EducationViewModel::class.java)

        setHasOptionsMenu(true)


        binding.recyerview.adapter=adapter

        binding.educationviewModel=viewModel

        viewModel.educationData.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.navigateToNextScreen.observe(this, Observer {
            if(it)
            {
                findNavController().navigate(EducationalDetailsFragmentDirections.actionEducationalDetailsFragmentToProjectsFragment())
                viewModel.resetNavigateToNextScreen()
            }
        })

        adapter.editEucation={
            findNavController().navigate(EducationalDetailsFragmentDirections.actionEducationalDetailsFragmentToAddEditEducationFragment(it))
        }


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(EducationalDetailsFragmentDirections.actionEducationalDetailsFragmentToAddEditEducationFragment(0))
        return super.onOptionsItemSelected(item)
    }
}