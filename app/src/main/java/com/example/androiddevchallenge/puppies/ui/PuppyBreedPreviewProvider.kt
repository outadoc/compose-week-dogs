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