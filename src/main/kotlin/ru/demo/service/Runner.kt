package ru.demo.service

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service


@Service
class Runner(private val valueSource: ValueSource) : CommandLineRunner {

    override fun run(vararg args: String) {
        valueSource.generate()
    }

}