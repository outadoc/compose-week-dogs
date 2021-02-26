package com.example.androiddevchallenge.puppies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Preview
@Composable
fun AboutScreen(onBackPressed: () -> Unit = {}) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.about_title),
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
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.about_author_title),
                style = MaterialTheme.typography.h4
            )
            Text(
                text = stringResource(id = R.string.about_author_message),
                style = MaterialTheme.typography.body1
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.about_resources_title),
                style = MaterialTheme.typography.h4
            )
            stringArrayResource(id = R.array.about_resources_messages).forEach {
                Text(text = it, style = MaterialTheme.typography.body1)
            }

            Text(
                text = stringResource(id = R.string.about_source_title),
                style = MaterialTheme.typography.h4
            )
            Text(
                text = stringResource(id = R.string.about_source_message),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }
}