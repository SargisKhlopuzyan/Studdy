package com.example.studdy

import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.ExactAlarms
import com.example.studdy.databinding.FragmentStudyBinding
import com.example.studdy.utils.toEpochMillis
import java.time.LocalDateTime
import java.time.LocalTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class StudyFragment : Fragment(), KoinComponent {

    private val exactAlarms: ExactAlarms by inject()

    private var _binding: FragmentStudyBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudyBinding.inflate(inflater, container, false)
        binding.btnSetStudyAlarm.setOnClickListener {
            if (!exactAlarms.canScheduleExactAlarms()) {
                (requireActivity() as? StudyActivity)?.openSettings()
            } else {
                val localDateTime = LocalDateTime.now()
                localDateTime.with(LocalTime.of(binding.tpSetStudyAlarm.hour, binding.tpSetStudyAlarm.minute))

                Log.e("LOG_TAG", "localDateTime : ${localDateTime.toEpochMillis()}")
                exactAlarms.setExactAlarmSetAlarmClock(localDateTime.toEpochMillis())
            }
        }
        binding.btnClearStudyAlarm.setOnClickListener {

        }
        binding.btnStopAlarm.setOnClickListener {

        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // set 24 hours mode for the time picker
        binding.tpSetStudyAlarm.setIs24HourView(DateFormat.is24HourFormat(context))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}