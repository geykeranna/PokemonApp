package ru.testtask.pokemonapp.ui.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.testtask.pokemonapp.R
import ru.testtask.pokemonapp.ui.theme.BackgroundBoxColor

@Composable
fun TypeInfo(
    modifier: Modifier = Modifier,
    types: List<String>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp))
            .background(BackgroundBoxColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp)
    ) {
        items(types.size) {index ->
            val typeIcon = mapOfType.getOrDefault(types[index], R.drawable.normal_type)
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .width(60.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp),
                    painter = painterResource(id = typeIcon),
                    contentDescription = types[index])
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = types[index],
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun showCard(){
    val type = listOf("water", "fairy")
    
    TypeInfo(types = type)
}