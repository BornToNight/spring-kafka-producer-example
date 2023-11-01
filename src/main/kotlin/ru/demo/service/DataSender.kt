package ru.demo.service

import ru.demo.model.StringValue

interface DataSender {

    fun send(value: StringValue)

}