package com.kudos.forageapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kudos.forageapp.databinding.FragmentForageDetailsBinding
import com.kudos.forageapp.viewmodel.ForageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgeDetailsFragment : Fragment() {

    private var forageId: Int = 0
    private lateinit var binding: FragmentForageDetailsBinding

    private val forageViewModel: ForageViewModel by viewModels()

    private val args: ForgeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForageDetailsBinding.inflate(layoutInflater)

        forageId = args.forageId

        getForageById()

        binding.apply {
            editButton.setOnClickListener {
                findNavController().navigate(
                    ForgeDetailsFragmentDirections.actionForageDetailsFragmentToAddUpdateForageFragment(
                        true,
                        forageId
                    )
                )
            }
        }

        return binding.root
    }

    private fun getForageById() {
        lifecycleScope.launch {

            forageViewModel.getForageById(forageId).observe(requireActivity()) { forage ->

                binding.apply {

                    forage?.let {
                        forageNameTextView.text = it.forageName
                        forageLocationTextView.text = it.forageLocation
                        forageSeasonTextView.text =
                            if (it.currentlyInSeason) "Currently in season" else "Currently not in season"
                        forageNotesTextView.text = it.forageNotes
                    }

                }

            }

        }
    }

}