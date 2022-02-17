package com.example.anymindtest.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentWorkExperienceBinding
import com.example.anymindtest.viewmodel.PersonalInfoViewModel

class WorkExperienceFragment : Fragment() {

    private lateinit var binding: FragmentWorkExperienceBinding

    private lateinit var viewModel: PersonalInfoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_work_experience,container,false)

        viewModel= ViewModelProviders.of(this).get(PersonalInfoViewModel::class.java)

        setHasOptionsMenu(true)

        activity?.setTitle("Work Experience")

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