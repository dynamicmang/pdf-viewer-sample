package com.example.util

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

fun getLearningTime(millis: Long): String {
    val second = 1000
    val minute = second * 60
    val hour = minute * 60
    return if (millis == 0L) {
        "학습 전"
    } else if (millis < minute) {
        String.format("%d초 학습 중", TimeUnit.MILLISECONDS.toSeconds(millis))
    } else if(millis < hour){
        String.format("0시간 %d분 학습 중", TimeUnit.MILLISECONDS.toMinutes(millis))
    } else {
        val hourValue = TimeUnit.MILLISECONDS.toHours(millis)
        val minuteValue = TimeUnit.MILLISECONDS.toMinutes(millis - (hourValue * hour))
        String.format("%d시간 %d분 학습 중", hourValue, minuteValue)
    }
}

fun getLearningDate(millis: Long): String {
    return SimpleDateFormat("yyyy.MM.dd").format(millis)
}