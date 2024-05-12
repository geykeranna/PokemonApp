package ru.testtask.pokemonapp.ui.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import ru.testtask.pokemonapp.domain.model.Stat
import ru.testtask.pokemonapp.ui.theme.BackgroundBoxColor
import java.util.Locale

@Composable
fun StatsInfo(
    modifier: Modifier = Modifier,
    stats: List<Stat>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp))
            .background(BackgroundBoxColor),
        contentPadding = PaddingValues(20.dp),
    ) {
        items(stats.size){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Text(
                    modifier = Modifier
                        .width(145.dp)
                        .padding(end = 20.dp),
                    text = "${stats[it].name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString()
                    }.replace('-', ' ')} (${stats[it].baseStat})"
                )
                Box(modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = 5.dp)
                ){
                    Box(modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.LightGray)
                    )
                    Box (
                        modifier = Modifier
                            .width((stats[it].baseStat * 2).dp)
                            .height(20.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Blue)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun showStats(){
    val stats = listOf(
        Stat(
            baseStat = 100,
            effort = 3,
            name = "hp"
        ),
        Stat(
            baseStat = 50,
            effort = 0,
            name = "attack"
        ),
        Stat(
            baseStat = 90,
            effort = 0,
            name = "defense"
        ),
        Stat(
            baseStat = 80,
            effort = 0,
            name = "special-defense"
        ),
    )

    StatsInfo(stats = stats)
}