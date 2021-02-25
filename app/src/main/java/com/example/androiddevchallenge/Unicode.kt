package com.example.androiddevchallenge

fun String.countryCodeToUnicodeFlag(): String {
    return this
        .filter { it in 'A'..'Z' }
        .map { it.toByte() }
        .flatMap { char ->
            listOf(
                // First UTF-16 char is \uD83C
                0xD8.toByte(),
                0x3C.toByte(),
                // Second char is \uDDE6 for A and increments from there
                0xDD.toByte(),
                (0xE6.toByte() + (char - 'A'.toByte())).toByte()
            )
        }
        .toByteArray()
        .let { bytes ->
            String(bytes, Charsets.UTF_16)
        }
}