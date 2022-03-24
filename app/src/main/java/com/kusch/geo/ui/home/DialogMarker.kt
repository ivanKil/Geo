package com.kusch.geo.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.kusch.geo.R
import com.kusch.geo.databinding.DialogMarkerBinding
import com.kusch.geo.databinding.FragmentHomeBinding
import com.kusch.geo.model.data.MarkerEntity


class DialogMarker : DialogFragment() {
    private var _binding: DialogMarkerBinding? = null
    private val binding get() = _binding!!
    var actOnSave: ((MarkerEntity) -> Unit)? = null
    private var markerEntity: MarkerEntity? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding: DialogMarkerBinding = DialogMarkerBinding.inflate(LayoutInflater.from(context))
        val dialog = AlertDialog.Builder(requireContext()).setView(binding.root)
            .setMessage(getString(R.string.marker_info))
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> dismiss() }
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                actOnSave?.let {
                    it(
                        markerEntity!!.copy(
                            name = binding.markerName.text.toString(),
                            annotation = binding.markerAnnotation.text.toString()
                        )
                    )
                }
            }
            .create()
        arguments?.getParcelable<MarkerEntity>(ITEM_ID)?.let {
            binding.markerName.setText(it.name)
            binding.markerAnnotation.setText(it.annotation)
            markerEntity = it
        }
        return dialog
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
        const val ITEM_ID: String = "item_id"
        fun newInstance(markerEntity: MarkerEntity) = DialogMarker().apply {
            arguments = Bundle().apply { putParcelable(ITEM_ID, markerEntity) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
