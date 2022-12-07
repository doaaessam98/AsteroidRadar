package com.example.asteroidradar.ui.details

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

   lateinit var binding:FragmentDetailsBinding
   val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding =DataBindingUtil.inflate(inflater,R.layout.fragment_details, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val asteroid = DetailsFragmentArgs.fromBundle(requireArguments()).selectedAsteroidDetails

        binding.asteroid = asteroid
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        viewModel.displayExplanationDialog.observe(viewLifecycleOwner) { displayExplanationDialog ->
            if(displayExplanationDialog) {
                displayAstronomicalUnitExplanationDialog()
                viewModel.onDisplayExplanationDialogDone()
            }
        }
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity(), R.style.DialogTheme)
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}


