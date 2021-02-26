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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.countryCodeToUnicodeFlag
import com.example.androiddevchallenge.puppies.model.PuppyBreed
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyDetailsScreen(puppy: PuppyBreed, onBackPressed: () -> Unit = {}) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = puppy.name,
                    color = MaterialTheme.colors.onPrimary
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.all_cd_back)
                    )
                }
            }
        )
        PuppyDetails(puppy = puppy)
    }
}

@Composable
fun PuppyDetails(puppy: PuppyBreed) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        CoilImage(
            modifier = Modifier
                .aspectRatio(puppy.image.aspectRatio)
                .fillMaxWidth(),
            data = puppy.image.url,
            contentDescription = puppy.name,
            fadeIn = true
        )
        PuppySpecList(puppy = puppy)
    }
}

@Preview
@Composable
fun PuppySpecList(@PreviewParameter(PuppyBreedPreviewProvider::class) puppy: PuppyBreed) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = puppy.name,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        PuppySpec(
            title = stringResource(R.string.details_prop_breedGroup),
            value = puppy.breedGroup
        )
        PuppySpec(
            title = stringResource(R.string.details_prop_temperament),
            value = puppy.temperament
        )
        PuppySpec(
            title = stringResource(R.string.details_prop_origin),
            value = puppy.origin
        )
        PuppySpec(
            title = stringResource(R.string.details_prop_country),
            value = puppy.countryCode?.countryCodeToUnicodeFlag()
        )
        PuppySpec(
            title = stringResource(R.string.details_prop_avgLifespan),
            value = puppy.lifeSpan
        )
        PuppySpec(
            title = stringResource(R.string.details_prop_height),
            value = puppy.height?.metric?.let { "$it cm" }
        )
        PuppySpec(
            title = stringResource(R.string.details_prop_weight),
            value = puppy.weight?.metric?.let { "$it kg" }
        )
    }
}

@Composable
fun PuppySpec(title: String, value: String?) {
    if (!value.isNullOrBlank()) {
        Column {
            Text(text = title, style = MaterialTheme.typography.h6)
            Text(
                text = value,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}
