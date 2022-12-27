package com.kudos.forageapp.viewmodel

import androidx.lifecycle.*
import com.kudos.forageapp.db.entities.Forage
import com.kudos.forageapp.repository.ForageRepository
import com.kudos.forageapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForageViewModel @Inject constructor(private val forageRepository: ForageRepository) :
    ViewModel() {

    private val _addForageStatus = MutableLiveData<Resource<Boolean>>()
    val addForageStatus: LiveData<Resource<Boolean>> = _addForageStatus

    private val _updateForageStatus = MutableLiveData<Resource<Boolean>>()
    val updateForageStatus: LiveData<Resource<Boolean>> = _updateForageStatus

    private val _deleteForageStatus = MutableLiveData<Resource<Boolean>>()
    val deleteForageStatus: LiveData<Resource<Boolean>> = _deleteForageStatus

    val allForages: LiveData<List<Forage>> = forageRepository.getAllForage().asLiveData()

    fun getForageById(forageId: Int) = forageRepository.getForageByID(forageId).asLiveData()

    fun addNewForage(forage: Forage) {
        viewModelScope.launch {
            try {
                forageRepository.addNewForage(forage)
                _addForageStatus.value = Resource.success(true)
            } catch (e: Exception) {
                _addForageStatus.value = Resource.error("Error: ${e.localizedMessage}", false)
            }
        }
    }

    fun updateForage(forage: Forage) {
        viewModelScope.launch {
            try {
                forageRepository.updateForage(forage)
                _updateForageStatus.value = Resource.success(true)
            } catch (e: Exception) {
                _updateForageStatus.value = Resource.error("Error: ${e.localizedMessage}", false)
            }
        }
    }

    fun deleteForage(forage: Forage) {
        viewModelScope.launch {
            try {
                forageRepository.deleteForage(forage)
                _deleteForageStatus.value = Resource.success(true)
            } catch (e: Exception) {
                _deleteForageStatus.value = Resource.error("Error: ${e.localizedMessage}", false)
            }
        }
    }

}