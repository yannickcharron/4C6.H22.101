package ca.qc.cstj.s02constraintlayout.presentation.ui.pilot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.qc.cstj.s02constraintlayout.domain.models.Pilot
import ca.qc.cstj.s02constraintlayout.notify

class PilotViewModel : ViewModel() {

    private val _pilot = MutableLiveData<Pilot>()
    val pilot: LiveData<Pilot> get() = _pilot

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        // value = Nouveau post, nouvelle story
        _pilot.value = Pilot("Bee Zoom",10)
    }

    fun fly(revolutions: Int, isTraining: Boolean) {

        val pilotValue = _pilot.value!!

        if(pilotValue.canFly()) {
            pilotValue.fly(revolutions, isTraining)
            _pilot.notify()
        } else {
            _error.value = "Erreur"
        }

    }

    fun recharge() {
        _pilot.value!!.recharge()
        _pilot.notify()
    }
}