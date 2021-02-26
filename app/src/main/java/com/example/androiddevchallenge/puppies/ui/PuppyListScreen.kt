package com.example.androiddevchallenge.puppies.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.puppies.PuppyListViewModel
import com.example.androiddevchallenge.puppies.model.PuppyBreed
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyListScreen(
    onPuppySelected: (PuppyBreed) -> Unit = {},
    onAboutClicked: () -> Unit = {}
) {
    val viewModel: PuppyListViewModel = viewModel()
    val state = viewModel.state.observeAsState(PuppyListViewModel.State.Loading)

    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    color = MaterialTheme.colors.onPrimary
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
            navigationIcon = {
                IconButton(onClick = {}, enabled = false) {
                    Icon(
                        imageVector = Icons.Default.Pets,
                        contentDescription = stringResource(R.string.list_cd_paw_print)
                    )
                }
            },
            actions = {
                IconButton(onClick = onAboutClicked) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(R.string.list_cd_about)
                    )
                }
            }
        )
        when (val stateValue = state.value) {
            PuppyListViewModel.State.Loading -> LoadingScreen()
            is PuppyListViewModel.State.Error -> ErrorScreen(
                errorMessage = stateValue.message,
                onRetry = {
                    viewModel.loadPuppies()
                }
            )
            is PuppyListViewModel.State.Done -> PuppyList(
                puppies = stateValue.breeds,
                onPuppySelected = onPuppySelected
            )
        }
    }
}

@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    CenteredBox {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                errorMessage,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(onClick = onRetry) {
                Text(stringResource(R.string.list_retry_action))
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    CenteredBox {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun PuppyList(
    @PreviewParameter(PuppyBreedPreviewProvider::class) puppies: List<PuppyBreed>,
    onPuppySelected: (PuppyBreed) -> Unit = {}
) {
    var filterBy by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = filterBy,
                onValueChange = { value ->
                    filterBy = value
                },
                label = { Text(stringResource(R.string.list_filter_hint)) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.list_cd_search)
                    )
                },
                trailingIcon = {
                    if (filterBy.isNotEmpty()) {
                        IconButton(onClick = { filterBy = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(R.string.list_cd_clear_results)
                            )
                        }
                    }
                }
            )
        }

        items(puppies) { puppy ->
            if (filterBy.isEmpty() || puppy.name.contains(filterBy, ignoreCase = true)) {
                PuppyBreedListItem(puppy = puppy, onPuppySelected = onPuppySelected)
            }
        }
    }
}

@Composable
@Preview(widthDp = 256, heightDp = 150)
fun PuppyBreedListItem(
    @PreviewParameter(PuppyBreedPreviewProvider::class) puppy: PuppyBreed,
    onPuppySelected: (PuppyBreed) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                onPuppySelected(puppy)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(72.dp)
        ) {
            CoilImage(
                data = puppy.image.url,
                contentDescription = puppy.name,
                fadeIn = true,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxHeight(),
                requestBuilder = {
                    transformations(CircleCropTransformation())
                }
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    puppy.name,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2
                )
                if (!puppy.breedGroup.isNullOrBlank()) {
                    Text(
                        puppy.breedGroup,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
