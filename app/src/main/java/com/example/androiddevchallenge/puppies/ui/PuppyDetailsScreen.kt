package com.example.androiddevchallenge.puppies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
                        contentDescription = "Back to list"
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
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = puppy.name,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            PuppySpec(title = stringResource(R.string.details_prop_breedGroup), puppy.breedGroup)
            PuppySpec(title = stringResource(R.string.details_prop_temperament), puppy.temperament)
            PuppySpec(title = stringResource(R.string.details_prop_origin), puppy.origin)
            PuppySpec(title = stringResource(R.string.details_prop_avgLifespan), puppy.lifeSpan)
            PuppySpec(
                title = stringResource(R.string.details_prop_country),
                value = puppy.countryCode?.countryCodeToUnicodeFlag()
            )
        }
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
