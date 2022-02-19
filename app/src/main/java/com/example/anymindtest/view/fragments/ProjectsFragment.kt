package com.example.anymindtest.view.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentEducationalDetailsBinding
import com.example.anymindtest.databinding.FragmentProjectsBinding
import com.example.anymindtest.view.adapter.EducationListAdapter
import com.example.anymindtest.view.adapter.ProjectListAdapter
import com.example.anymindtest.viewmodel.EducationViewModel
import com.example.anymindtest.viewmodel.ProjectsViewModel

class ProjectsFragment : Fragment() {

    private lateinit var binding: FragmentProjectsBinding

    private lateinit var viewModel: ProjectsViewModel

    private val adapter by lazy { ProjectListAdapter() }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_projects,container,false)

        viewModel= ViewModelProviders.of(this).get(ProjectsViewModel::class.java)

        setHasOptionsMenu(true)


        binding.recyerview.adapter=adapter

        binding.projectViewModel=viewModel

        viewModel.projectsData.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.navigateToNextScreen.observe(this, Observer {
            // findNavController().popBackStack()
        })

        adapter.editProject={
            findNavController().navigate(ProjectsFragmentDirections.actionProjectsFragmentToAddEditProjectFragment(it))
        }


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(ProjectsFragmentDirections.actionProjectsFragmentToAddEditProjectFragment(0))
        return super.onOptionsItemSelected(item)
    }
}