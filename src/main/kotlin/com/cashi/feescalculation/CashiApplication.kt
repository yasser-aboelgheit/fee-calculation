package com.cashi.feescalculation

import dev.restate.sdk.springboot.EnableRestate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRestate
class CashiApplication

fun main(args: Array<String>) {
	runApplication<CashiApplication>(*args)
}
