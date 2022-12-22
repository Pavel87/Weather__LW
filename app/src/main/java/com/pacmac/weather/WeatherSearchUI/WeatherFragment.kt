package com.pacmac.weather.WeatherSearchUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.pacmac.weather.R
import com.pacmac.weather.ui.theme.ComposeWeatherTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class WeatherFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeWeatherTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column() {
                            WeatherSearch()
                            SearchResults()
                        }
                        LoadingIndicator(Modifier.align(Alignment.Center))

                        ErrorMessage()
                    }
                }
            }
        }
    }


    @Composable
    private fun LoadingIndicator(modifier: Modifier) {
        if (viewModel.onLoading.value) {
            CircularProgressIndicator(modifier = modifier)
        }
    }

    @Composable
    private fun ErrorMessage() {
        if (viewModel.onError.value) {
            Toast.makeText(context, stringResource(id = R.string.error_message), Toast.LENGTH_SHORT)
                .show()
            viewModel.onError.value = false
        }
    }

    @Composable
    private fun WeatherSearch(modifier: Modifier = Modifier) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colors.background
        ) {
            UserInputView()
        }
    }

    @Composable
    private fun SearchBox(searchTerm: MutableState<String>, modifier: Modifier = Modifier) {
        Surface(
            modifier = modifier,
        ) {
            TextField(
                value = searchTerm.value,
                onValueChange = {
                    searchTerm.value = it
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface
                ),
                placeholder = {
                    Text(stringResource(R.string.enter_city_name))
                },
                label = { Text(text = stringResource(id = R.string.search)) },
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
                    .padding(top = 24.dp)

            )

        }
    }

    @Composable
    fun UserInputView(modifier: Modifier = Modifier) {
        Surface(color = MaterialTheme.colors.surface) {
            Column(
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colors.background)
            ) {
                val searchTerm = rememberSaveable { mutableStateOf("") }

                SearchBox(searchTerm)
                Button(
                    onClick = { viewModel.searchWeather(searchTerm.value) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.search))
                }
            }
        }
    }

    @Composable
    fun SearchResults(modifier: Modifier = Modifier) {
        Surface(color = MaterialTheme.colors.surface) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .fillMaxSize()
            ) {
                items(
                    items = viewModel.weatherList,
                ) { weatherItem ->
                    WeatherItem(weatherItem)
                    Divider(thickness = 4.dp)
                }
            }
        }
    }
}