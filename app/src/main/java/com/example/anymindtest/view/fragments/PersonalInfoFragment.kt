package com.example.anymindtest.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.anymindtest.R
import com.example.anymindtest.databinding.FragmentPersonalInfoBinding
import com.example.anymindtest.viewmodel.PersonalInfoViewModel


class PersonalInfoFragment : Fragment() {

    private lateinit var binding:FragmentPersonalInfoBinding

    private lateinit var viewModel:PersonalInfoViewModel

    val PICK_IMAGE = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_personal_info,container,false)

        viewModel=ViewModelProviders.of(this).get(PersonalInfoViewModel::class.java)

        viewModel.getPersonalInfoFromRoom()

        binding.personalInfoViewModel=viewModel


        viewModel.errorString.observe(this, Observer {
            Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
        })

        viewModel.personalInfoModel.observe(this, Observer {
            binding.email.setText(it.email)
        })


        viewModel.navigateToNextScreen.observe(this, Observer {
            findNavController().navigate(R.id.action_peronalInfoFragment_to_workExperienceFragment)
        })

        binding.pickImage.setOnClickListener {
            pickImageFromGalley()
        }
        return binding.root
    }


    fun pickImageFromGalley()
    {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null)
        {
            val selectedImage: Uri = data.getData()!!
            viewModel.imagePath.value=selectedImage
            binding.pickImage.setImageURI(selectedImage)

        }
    }
}