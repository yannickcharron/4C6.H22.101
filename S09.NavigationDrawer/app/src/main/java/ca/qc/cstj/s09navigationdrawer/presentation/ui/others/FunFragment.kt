package ca.qc.cstj.s09navigationdrawer.presentation.ui.others

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentFunBinding

class FunFragment : Fragment(R.layout.fragment_fun) {

    private val binding: FragmentFunBinding by viewBinding()
    private val viewModel : FunViewModel by viewModels()

    private lateinit var mediaPlayer: MediaPlayer

    private var counter = 0

    private val timer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.txvCounter.text = (++counter).toString()
            binding.pgbLoading.setProgress(counter, true)
        }

        override fun onFinish() {
            counter = 0
            binding.btnTimer.visibility = View.VISIBLE
            this.cancel()
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.abc)

        try {
            //val uri = Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.video}")
            //binding.vdvVideo.setVideoURI(uri)
            binding.vdvVideo.setVideoPath(Constants.VIDEO_URL)
        } catch(ex: Exception) {
            //TODO: Afficher un message d'erreur
        }

        binding.btnTimer.setOnClickListener {
            binding.vdvVideo.start()

            mediaPlayer.isLooping = true
            mediaPlayer.start()

            binding.pgbLoading.setProgress(counter, false)
            timer.start()
            binding.btnTimer.visibility = View.INVISIBLE


        }

    }

}