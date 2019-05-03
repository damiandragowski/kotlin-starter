package com.ovalinfo.starter.model

import java.time.LocalDate
import java.time.ZoneId
import java.util.*

data class Animal(val nickname: String, var age: Int, var dateOfBirth: Date = Date()) {
    fun copy(nickname: String =this.nickname, age: Int = this.age) = Animal(nickname, age,
        Date.from(LocalDate.now().minusYears(age.toLong()).atStartOfDay().atZone(
        ZoneId.systemDefault()).toInstant()) )
}