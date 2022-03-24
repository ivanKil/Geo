package com.kusch.geo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kusch.geo.databinding.FragmentDashboardBinding
import com.kusch.geo.ui.home.DialogMarker
import com.kusch.geo.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarkersFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val adapter: MarkerAdapter by lazy {
        MarkerAdapter {
            val addDialog = DialogMarker.newInstance(it)
            addDialog.actOnSave = {
                homeViewModel.addMarker(it)
            }
            addDialog.show(childFragmentManager, DialogMarker.TAG)
        }
    }
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.markersRecyclerview.adapter = adapter
        homeViewModel.ldMarkers.observe(viewLifecycleOwner) { adapter.setData(it) }
        homeViewModel.requestMarkers()
        homeViewModel.ldAddMarker.observe(viewLifecycleOwner) { homeViewModel.requestMarkers() }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}