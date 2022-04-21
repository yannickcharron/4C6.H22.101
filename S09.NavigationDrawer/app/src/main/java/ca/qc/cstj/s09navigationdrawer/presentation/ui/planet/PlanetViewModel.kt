package ca.qc.cstj.s09navigationdrawer.presentation.ui.planet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.qc.cstj.s09navigationdrawer.core.Resource
import ca.qc.cstj.s09navigationdrawer.data.repositories.PlanetRepository
import ca.qc.cstj.s09navigationdrawer.domain.models.Planet

class PlanetViewModel(private val href: String) : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _planet = MutableLiveData<Resource<Planet>>()
    val planet: LiveData<Resource<Planet>> get() = _planet


    class Factory(private val href: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(href)
        }

    }

}