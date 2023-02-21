package com.example

interface ExactAlarms {
    fun canScheduleExactAlarms() : Boolean
    fun clearExactAlarm()
    fun setExactAlarmSetAlarmClock(triggerAtMillis: Long)
}