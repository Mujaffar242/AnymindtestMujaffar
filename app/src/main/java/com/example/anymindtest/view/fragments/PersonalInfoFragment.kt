package com.example.anymindtest.view.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentPersonalInfoBinding
import com.example.anymindtest.viewmodel.PersonalInfoViewModel


class PersonalInfoFragment : Fragment() {

    private lateinit var binding: FragmentPersonalInfoBinding

    private lateinit var viewModel: PersonalInfoViewModel

    val PICK_IMAGE = 1

    override fun onStart() {
        super.onStart()
        activity?.setTitle("Personal Info")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_personal_info, container, false)

        viewModel = ViewModelProviders.of(this).get(PersonalInfoViewModel::class.java)

        /*
        * get personal data from room database if available
        * */
        viewModel.getPersonalInfoFromRoom()

        binding.personalInfoViewModel = viewModel


        viewModel.errorString.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        /*
        * set personal data from room database into views if available
        * */
        viewModel.personalInfoModelLiveData.observe(this, Observer {
            setImageOnImageView(it.imagePath)
            binding.email.setText(it.email)
            binding.mobile.setText(it.mobile)
            binding.address.setText(it.address)
            binding.carrerObjective.setText(it.careerObjective)
            binding.yearOfExperience.setText(it.yearOfExperience)

        })


        viewModel.navigateToNextScreen.observe(this, Observer {
            if (it) {
                findNavController().navigate(R.id.action_peronalInfoFragment_to_workExperienceFragment)
                viewModel.resetNavigateToNextScreen()
            }
        })

        binding.pickImage.setOnClickListener {
            pickImageFromGalley()
        }



        return binding.root
    }


    fun pickImageFromGalley() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "select a picture"), PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            //  val selectedImage: Uri = data.getData()!!


            // setImageOnImageView(ImageFilePath.getPath(activity, data.getData()))

        }
    }


    fun setImageOnImageView(imagePath: String) {
        viewModel.imagePath.value = imagePath
        binding.pickImage.setImageURI(Uri.parse(imagePath))


        /*Glide.with(activity as Activity)
            .load(File(imagePath))
            .into(binding.pickImage)*/;
    }


}