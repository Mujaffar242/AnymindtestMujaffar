package com.example.anymindtest.view.activity

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.anymindtest.R
import com.example.anymindtest.databinding.ActivityPrintResumeBinding
import com.example.anymindtest.utils.PRINT_INFO
import com.example.anymindtest.view.adapter.EducationListAdapter
import com.example.anymindtest.view.adapter.ExperienceListAdapter
import com.example.anymindtest.view.adapter.ProjectListAdapter
import com.example.anymindtest.viewmodel.*


class PrintResumeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrintResumeBinding

    private lateinit var personalInfoViewModel: PersonalInfoViewModel
    private lateinit var workExperienceViewModel: WorkExperienceViewModel
    private lateinit var educationViewModel: EducationViewModel
    private lateinit var projectsViewModel: ProjectsViewModel

    private val experienceListAdapter by lazy { ExperienceListAdapter() }

    private val educationListAdapter by lazy { EducationListAdapter() }

    private val projectListAdapter by lazy { ProjectListAdapter() }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle("View Resume")

        /*
        * set page type with each adapter to hide edit button
        * */
        experienceListAdapter.pageType= PRINT_INFO
        educationListAdapter.pageType= PRINT_INFO
        projectListAdapter.pageType= PRINT_INFO


        binding=DataBindingUtil.setContentView(this,R.layout.activity_print_resume)

        //init viewmodels
        personalInfoViewModel=ViewModelProviders.of(this).get(PersonalInfoViewModel::class.java)
        workExperienceViewModel = ViewModelProviders.of(this).get(WorkExperienceViewModel::class.java)
        educationViewModel= ViewModelProviders.of(this).get(EducationViewModel::class.java)
        projectsViewModel=ViewModelProviders.of(this).get(ProjectsViewModel::class.java)


        //binding.personalInfoViewModel=personalInfoViewModel

        //set adapters to lists
        binding.expericeList.adapter=experienceListAdapter
        binding.eductionList.adapter=educationListAdapter
        binding.projectsList.adapter=projectListAdapter



        //get and set data releted to personal info
        personalInfoViewModel.getPersonalInfoFromRoom()

        personalInfoViewModel.personalInfoModelLiveData.observe(this, Observer {
            binding.email.setText(it.email)
            binding.mobile.setText(it.mobile)
            binding.address.setText(it.address)
            binding.carrerObjective.setText(it.careerObjective)
            binding.yearOfExperience.setText(it.yearOfExperience)
        })


        //get and set experience data
        workExperienceViewModel.workExperienceData.observe(this, Observer {
            experienceListAdapter.submitList(it)
        })

        //get and set education data
        educationViewModel.educationData.observe(this, Observer {
            educationListAdapter.submitList(it)
        })

        //get and set project data
        projectsViewModel.projectsData.observe(this, Observer {
            projectListAdapter.submitList(it)
        })


    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.print_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }*/
}