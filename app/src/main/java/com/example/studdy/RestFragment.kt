package com.example.studdy

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studdy.databinding.FragmentRestBinding

class RestFragment : Fragment() {

    private var _binding: FragmentRestBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // set 24 hours mode for the time picker
        DateFormat.is24HourFormat(context).let { is24HourFormat ->
            binding.tpSetRestAlarm.setIs24HourView(is24HourFormat)
            binding.tpSetRestWindowAlarm.setIs24HourView(is24HourFormat)
            binding.tpSetRepeatingRestAlarm.setIs24HourView(is24HourFormat)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}