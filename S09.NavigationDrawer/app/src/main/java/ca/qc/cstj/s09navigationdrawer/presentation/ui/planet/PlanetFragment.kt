package ca.qc.cstj.s09navigationdrawer.presentation.ui.planet

import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentPlanetBinding

class PlanetFragment : Fragment(R.layout.fragment_planet) {

    private val binding: FragmentPlanetBinding by viewBinding()
    private val viewModel: PlanetViewModel by viewModels {
        PlanetViewModel.Factory(args.href)
    }

    private val args : PlanetFragmentArgs by navArgs()



}