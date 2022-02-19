package com.example.anymindtest.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentWorkExperienceBinding
import com.example.anymindtest.view.adapter.ExperienceListAdapter
import com.example.anymindtest.viewmodel.PersonalInfoViewModel
import com.example.anymindtest.viewmodel.WorkExperienceViewModel

class WorkExperienceFragment : Fragment() {

    private lateinit var binding: FragmentWorkExperienceBinding

    private lateinit var viewModel: WorkExperienceViewModel

    private val adapter by lazy { ExperienceListAdapter() }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_work_experience,container,false)

        viewModel= ViewModelProviders.of(this).get(WorkExperienceViewModel::class.java)

        setHasOptionsMenu(true)

        activity?.setTitle("Work Experience")

        binding.recyerview.adapter=adapter

        binding.experienceViewModel=viewModel

        viewModel.workExperienceData.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.navigateToNextScreen.observe(this, Observer {
            findNavController().popBackStack()
        })



        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.action_workExperienceFragment_to_addEditExperienceFragment)
        return super.onOptionsItemSelected(item)
    }
}