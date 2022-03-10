package ca.qc.cstj.s05localdatasource.presentation.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ca.qc.cstj.s05localdatasource.R
import ca.qc.cstj.s05localdatasource.databinding.ActivitySplashBinding
import ca.qc.cstj.s05localdatasource.presentation.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.user.observe(this) { user ->
            //Afficher les valeurs du user dans l'écran

            //Section du haut
            binding.txvUserName.text = user.fullName
            when(user.isOnline) {
                true -> {
                    binding.imgUserIsOnline.setImageResource(R.drawable.ic_baseline_cloud_24)
                }
                false -> {
                    binding.imgUserIsOnline.setImageResource(R.drawable.ic_baseline_cloud_off_24)
                }
            }

            //TextInputLayout
            binding.tilFirstName.editText!!.setText(user.firstName)
            binding.tilLastName.editText!!.setText(user.lastName)
            binding.swtOnline.isChecked = user.isOnline

        }

        binding.btnSave.setOnClickListener {
            val firstName = binding.tilFirstName.editText!!.text.toString()
            val lastName = binding.tilLastName.editText!!.text.toString()
            val isOnline = binding.swtOnline.isChecked

            viewModel.save(firstName, lastName, isOnline)
        }


        binding.btnOpen.setOnClickListener {
            startActivity(MainActivity.newIntent(this))
        }
    }
}