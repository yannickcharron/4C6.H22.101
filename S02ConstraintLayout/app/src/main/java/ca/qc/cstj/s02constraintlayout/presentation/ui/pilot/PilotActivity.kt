package ca.qc.cstj.s02constraintlayout.presentation.ui.pilot

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import ca.qc.cstj.s02constraintlayout.R
import ca.qc.cstj.s02constraintlayout.databinding.ActivityPilotBinding
import ca.qc.cstj.s02constraintlayout.domain.models.Pilot
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class PilotActivity : AppCompatActivity() {

    //Permettre d'accéder aux composants graphiques (boutons, textview) de l'interface
    private lateinit var binding: ActivityPilotBinding
    private val viewModel: PilotViewModel by viewModels()

    //private val _pilot = Pilot("Bee Zoom",10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Charger le visual dans la objet binding
        binding = ActivityPilotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.pilot.observe(this) {
            with(binding) {
                txvPilotName.text = it.name
                txvLevel.text = getString(R.string.level, it.level)
                txvCube.text = it.cube.toString()
                txvEnergy.text = it.energy.toString()
                txvShield.text = it.shield.toString()
                txvLife.text = it.life.toString()
            }
        }


        //refreshUI()

        binding.btnStart?.setOnClickListener {
//            if(_pilot.canFly()) {
//                binding.btnStart?.isEnabled = false
//                _pilot.fly(binding.sldRevolution.value.toInt(), binding.swtTraining.isChecked)
//
//                val layoutParams = binding.imvRocket.layoutParams as ConstraintLayout.LayoutParams
//                val startAngle = layoutParams.circleAngle
//                val endAngle = startAngle - (360 * binding.sldRevolution.value.toInt())
//                val animation = ValueAnimator.ofFloat(startAngle, endAngle)
//
//                //Log.d("Yannick", animation.repeatCount.toString())
//
//                //animation.repeatCount = binding.sldRevolution.value.toInt() - 1
//                animation.duration = Random.nextLong(3000, 5000)
//                animation.interpolator = AccelerateDecelerateInterpolator()
//
//
//                animation.addUpdateListener {
//                    val layoutParamsAnimation = binding.imvRocket.layoutParams as ConstraintLayout.LayoutParams
//                    val animatedValue = it.animatedValue as Float
//                    layoutParamsAnimation.circleAngle = animatedValue
//                    binding.imvRocket.layoutParams = layoutParamsAnimation
//                    binding.imvRocket.rotation = (animatedValue - 90) * 3
//                    Log.d("Yannick", animatedValue.toString())
//                }
//
//                animation.doOnEnd {
//                    binding.btnStart?.isEnabled = true
//                    refreshUI()
//                }
//
//                animation.start()
//
//
//            } else {
//                Snackbar.make(binding.root, getString(R.string.lowResource), Snackbar.LENGTH_INDEFINITE)
//                    .setAction(getString(R.string.recharge)) {
//                        _pilot.recharge()
//                        refreshUI()
//                    }.show()
//            }
        }

    }

//    private fun refreshUI() {
//
//        with(binding) {
//            txvPilotName.text = _pilot.name
//            txvLevel.text = getString(R.string.level, _pilot.level)
//            txvCube.text = _pilot.cube.toString()
//            txvEnergy.text = _pilot.energy.toString()
//            txvShield.text = _pilot.shield.toString()
//            txvLife.text = _pilot.life.toString()
//        }
//    }
}