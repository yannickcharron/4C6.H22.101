package ca.qc.cstj.s02constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.qc.cstj.s02constraintlayout.databinding.ActivityPilotBinding
import ca.qc.cstj.s02constraintlayout.domain.models.Pilot

class PilotActivity : AppCompatActivity() {

    //Permettre d'accéder aux composants graphiques (boutons, textview) de l'interface
    private lateinit var binding: ActivityPilotBinding

    private val _pilot = Pilot("Bee Zoom",10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Charger le visual dans la objet binding
        binding = ActivityPilotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        refreshUI()

        binding.btnStart.setOnClickListener {
            if(_pilot.canFly()) {
                _pilot.fly(binding.sldRevolution.value.toInt(), binding.swtTraining.isChecked)
                refreshUI()
            } else {
                //TODO: SNACKBAR
            }
        }

    }

    private fun refreshUI() {

        with(binding) {
            txvPilotName.text = _pilot.name
            txvLevel.text = _pilot.level.toString()
            txvCube.text = _pilot.cube.toString()
            txvEnergy.text = _pilot.energy.toString()
            txvShield.text = _pilot.shield.toString()
            txvLife.text = _pilot.life.toString()
        }
    }
}