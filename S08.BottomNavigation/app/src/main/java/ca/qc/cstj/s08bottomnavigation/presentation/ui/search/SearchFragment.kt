package ca.qc.cstj.s08bottomnavigation.presentation.ui.search

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ca.qc.cstj.s08bottomnavigation.R
import ca.qc.cstj.s08bottomnavigation.core.Constants
import ca.qc.cstj.s08bottomnavigation.core.LoadingResource
import ca.qc.cstj.s08bottomnavigation.core.text
import ca.qc.cstj.s08bottomnavigation.databinding.FragmentSearchBinding
import ca.qc.cstj.s08bottomnavigation.domain.models.Meteo
import com.bumptech.glide.Glide
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModels()

    private lateinit var ctlMainActivity: ConstraintLayout

    private var location: LatLng? = null

    //Équivalent de la méthode onCreate dans une activité
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctlMainActivity = requireActivity().findViewById(R.id.ctlMainActivity)

        viewModel.meteo.observe(viewLifecycleOwner) {
            //Mettre à jour l'interface
            when (it) {
                is LoadingResource.Error -> {
                    binding.pgbLoading.hide()
                    binding.grpMeteo.visibility = View.INVISIBLE
                    binding.txvNotAvailable.visibility = View.VISIBLE
                    Snackbar.make(
                        binding.root,
                        it.throwable.message!!,
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                }
                is LoadingResource.Loading -> {
                    binding.pgbLoading.show()
                    binding.grpMeteo.visibility = View.INVISIBLE
                    binding.txvNotAvailable.visibility = View.INVISIBLE
                }
                is LoadingResource.Success -> {
                    binding.pgbLoading.hide()
                    binding.grpMeteo.visibility = View.VISIBLE
                    binding.txvNotAvailable.visibility = View.INVISIBLE
                    displayMeteo(it.data!!)
                }
            }
        }

        binding.btnSearch.setOnClickListener {
            viewModel.search(binding.tilSearch.text)
        }

        binding.fabLocation.setOnClickListener {
            val action = SearchFragmentDirections
                .actionNavigationSearchToMapsActivity(location!!)
            findNavController().navigate(action)
        }

    }


    private fun displayMeteo(meteo: Meteo) {

        location = LatLng(meteo.latitude, meteo.longitude)

        with(binding) {
            txvCity.text = meteo.city
            txvTemperature.text = getString(R.string.temperatureFormat,meteo.temperature)
            txvSky.text = meteo.weather

            //IMAGE
            Glide.with(requireContext())
                .load(Constants.COUNTRY_FLAG_URL.format(meteo.country.lowercase()))
                .into(binding.imvCountry)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                txvDatePhone.text = formatter.format(Instant.now().atZone(ZoneId.systemDefault()))
                txvDateAPI.text = formatter.format(
                    Instant.ofEpochSecond(meteo.timestamp.toLong())
                        .atZone(ZoneOffset.UTC).plusSeconds(meteo.timeZone.toLong()))

            }

            val background = if(meteo.temperature >= Constants.HOT_TEMPERATURE_THRESHOLD) {
                ContextCompat.getDrawable(requireContext(), R.drawable.warm)
            } else {
                ContextCompat.getDrawable(requireContext(), R.drawable.cold)
            }

            background!!.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(requireContext(), R.color.filter), PorterDuff.Mode.DARKEN)

            ctlMainActivity.background = background

        }


    }

}