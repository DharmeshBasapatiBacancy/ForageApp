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
import com.kudos.forageapp.R
import com.kudos.forageapp.databinding.FragmentAddUpdateForageBinding
import com.kudos.forageapp.db.entities.Forage
import com.kudos.forageapp.utils.DialogUtils.showCustomAlertDialog
import com.kudos.forageapp.utils.Status
import com.kudos.forageapp.utils.ViewUtils.toast
import com.kudos.forageapp.viewmodel.ForageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddUpdateForgeFragment : Fragment() {

    private var forageId: Int = 0
    private var isEdit: Boolean = false
    private lateinit var binding: FragmentAddUpdateForageBinding

    private val forageViewModel: ForageViewModel by viewModels()

    private val args: AddUpdateForgeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUpdateForageBinding.inflate(layoutInflater)
        isEdit = args.isEdit
        binding.apply {
            if (isEdit) {
                forageId = args.forageId
                addForageButton.text = "Update Details"
                deleteForageButton.visibility = View.VISIBLE
                getForageById()
            }
            addForageButton.setOnClickListener {
                if (!isEdit) {
                    addForageInDB()
                } else {
                    updateForgeInDB()
                }
            }
            deleteForageButton.setOnClickListener {
                requireContext().showCustomAlertDialog(layoutInflater, {
                    deleteForageFromDB()
                }, {}).show()
            }
        }
        return binding.root
    }

    private fun deleteForageFromDB() {
        lifecycleScope.launch {
            binding.apply {
                val forageItem = Forage(
                    forageId = forageId,
                    forageName = forageNameEditText.text.toString(),
                    forageLocation = forageLocationEditText.text.toString(),
                    forageNotes = forageNotesEditText.text.toString(),
                    currentlyInSeason = seasonCheckBox.isChecked
                )

                forageViewModel.deleteForage(forageItem)

                forageViewModel.deleteForageStatus.observe(requireActivity()) {
                    when (it.status) {
                        Status.SUCCESS -> {
                            toast("Forage Deleted Successfully !!!")
                            findNavController().popBackStack(R.id.forageDetailsFragment,true)
                            findNavController().navigate(R.id.forageListFragment)
                        }
                        Status.ERROR -> {
                            toast(it.message.toString())
                        }
                        Status.LOADING -> {}
                    }
                }
            }
        }
    }

    private fun updateForgeInDB() {
        binding.apply {
            val newForageItem = Forage(
                forageId = forageId,
                forageName = forageNameEditText.text.toString(),
                forageLocation = forageLocationEditText.text.toString(),
                forageNotes = forageNotesEditText.text.toString(),
                currentlyInSeason = seasonCheckBox.isChecked
            )

            forageViewModel.updateForage(newForageItem)

            forageViewModel.updateForageStatus.observe(requireActivity()) {
                when (it.status) {
                    Status.SUCCESS -> {
                        toast("Forage Details Updated !!!")
                        findNavController().navigateUp()
                    }
                    Status.ERROR -> {
                        toast(it.message.toString())
                    }
                    Status.LOADING -> {}
                }
            }
        }
    }

    private fun getForageById() {
        lifecycleScope.launch {

            forageViewModel.getForageById(forageId).observe(requireActivity()) { forage ->

                binding.apply {

                    forage?.let {
                        forageNameEditText.setText(it.forageName)
                        forageLocationEditText.setText(it.forageLocation)
                        seasonCheckBox.isChecked = it.currentlyInSeason
                        forageNotesEditText.setText(it.forageNotes)
                    }

                }

            }

        }
    }

    private fun addForageInDB() {
        binding.apply {

            lifecycleScope.launch {

                val forageName = forageNameEditText.text.toString()
                val forageLocation = forageLocationEditText.text.toString()
                val forageNotes = forageNotesEditText.text.toString()
                val forageSeason = seasonCheckBox.isChecked

                val forage = Forage(
                    forageName = forageName,
                    forageLocation = forageLocation,
                    forageNotes = forageNotes,
                    currentlyInSeason = forageSeason
                )

                forageViewModel.addNewForage(forage)

                forageViewModel.addForageStatus.observe(requireActivity()) {
                    when (it.status) {
                        Status.SUCCESS -> {
                            toast("New Forage Added !!!")
                            findNavController().navigateUp()
                        }
                        Status.ERROR -> {
                            toast(it.message.toString())
                        }
                        Status.LOADING -> {}
                    }
                }

            }

        }
    }

}