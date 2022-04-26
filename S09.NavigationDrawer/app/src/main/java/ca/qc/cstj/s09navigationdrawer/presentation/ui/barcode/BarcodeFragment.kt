package ca.qc.cstj.s09navigationdrawer.presentation.ui.barcode

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.core.Resource
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentBarcodeBinding
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig

class BarcodeFragment : Fragment(R.layout.fragment_barcode) {

    private val binding: FragmentBarcodeBinding by viewBinding()
    private val viewModel: BarcodeViewModel by viewModels()

    private val quickieActivityLauncher =
        registerForActivityResult(ScanQRCode(), ::handleQuickieQRResult)

    private val codeBarActivityLauncher = registerForActivityResult(ScanCustomCode(), ::handleQuickieCodeBarre)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnQuickie.setOnClickListener {
            //quickieActivityLauncher.launch(null)

            codeBarActivityLauncher.launch(
                ScannerConfig.build {
                    setBarcodeFormats(listOf(BarcodeFormat.FORMAT_ALL_FORMATS)) // set interested barcode formats
                    setOverlayStringRes(R.string.Quickie) // string resource used for the scanner overlay
                    //setOverlayDrawableRes(R.drawable.ic_scan_barcode) // drawable resource used for the scanner overlay
                    setHapticSuccessFeedback(false) // enable (default) or disable haptic feedback when a barcode was detected
                    setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
                    setHorizontalFrameRatio(2.2f) // set the horizontal overlay ratio (default is 1 / square frame)
                    setUseFrontCamera(false) // use the front camera
                }
            )
        }

        viewModel.checkIn.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    binding.txvCodeContent.text = it.throwable.message
                }
                is Resource.Success -> {
                    binding.txvCodeContent.text = it.data.toString()
                }
            }
        }
    }

    private fun handleQuickieQRResult(qrResult: QRResult) {
        when (qrResult) {
            is QRResult.QRSuccess -> {
                binding.txvCodeContent.text = qrResult.content.rawValue
                viewModel.addCheckIn(qrResult.content.rawValue)
            }
            is QRResult.QRUserCanceled -> {
                binding.txvCodeContent.text = getString(R.string.user_canceled)
            }
            is QRResult.QRMissingPermission -> {
                binding.txvCodeContent.text = getString(R.string.missing_permission)
            }
            is QRResult.QRError -> {
                binding.txvCodeContent.text = qrResult.exception.message
            }
        }
    }

    private fun handleQuickieCodeBarre(qrResult: QRResult) {
        when (qrResult) {
            is QRResult.QRSuccess -> {
                binding.txvCodeContent.text = qrResult.content.rawValue
            }
            is QRResult.QRUserCanceled -> {
                binding.txvCodeContent.text = getString(R.string.user_canceled)
            }
            is QRResult.QRMissingPermission -> {
                binding.txvCodeContent.text = getString(R.string.missing_permission)
            }
            is QRResult.QRError -> {
                binding.txvCodeContent.text = qrResult.exception.message
            }
        }
    }

}