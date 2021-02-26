/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

/**
 * Converts an ISO 3166-1 alpha-2 country code to the corresponding Unicode flag emoji.
 *
 * ```
 * "FR".countryCodeToUnicodeFlag() // 🇫🇷
 * "US".countryCodeToUnicodeFlag() // 🇺🇸
 * ```
 */
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
