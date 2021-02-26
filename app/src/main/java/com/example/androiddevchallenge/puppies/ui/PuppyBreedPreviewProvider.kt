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
package com.example.androiddevchallenge.puppies.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.androiddevchallenge.puppies.model.PuppyBreed
import com.example.androiddevchallenge.puppies.model.PuppyImage

class PuppyBreedPreviewProvider : PreviewParameterProvider<PuppyBreed> {

    override val values: Sequence<PuppyBreed>
        get() = sequence {
            yield(
                PuppyBreed(
                    id = "86",
                    name = "Cocker Spaniel",
                    breedGroup = "Sporting",
                    temperament = "Trainable, Friendly, Affectionate, Playful, Quiet, Faithful",
                    image = PuppyImage(
                        id = "1lFmrzECl",
                        url = "https://cdn2.thedogapi.com/images/1lFmrzECl.jpg",
                        height = 1080f,
                        width = 1080f
                    )
                )
            )

            yield(
                PuppyBreed(
                    id = "222",
                    name = "Shiba Inu",
                    breedGroup = "Non-Sporting",
                    bredFor = "Hunting in the mountains of Japan, Alert Watchdog",
                    temperament = "Charming, Fearless, Keen, Alert, Confident, Faithful",
                    countryCode = "JP",
                    image = PuppyImage(
                        id = "Zn3IjPX3f",
                        url = "https://cdn2.thedogapi.com/images/Zn3IjPX3f.jpg",
                        height = 1080f,
                        width = 1080f
                    )
                )
            )
        }
}
