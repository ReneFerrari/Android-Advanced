package com.fhooe.android.compose_ui_examples

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fhooe.android.compose_ui_examples.ui.theme.Colors
import com.fhooe.android.compose_ui_examples.ui.theme.TextStyles

//https://www.figma.com/design/k2lSw9VFAhPeI92E0kuf5L/Ulmo-E-Commerce-UI-kit--Community-?node-id=77-105&t=YeazOMeEIx334YD7-0
@Composable
fun ProductCard(hasBadge: Boolean = false) {
    Column(
        modifier = Modifier.width(164.dp)
    ) {
        ProductImage(hasBadge)
        Spacer(Modifier.height(8.dp))
        Row(modifier = Modifier.height(24.dp)) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "150.00$",
                style = TextStyles.bodyOneMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.heart),
                modifier = Modifier.size(24.dp),
                contentDescription = null,
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Wooden bedside table featuring a raised design",
            style = TextStyles.bodyThreeRegular
        )
    }
}

@Composable
private fun ProductImage(hasBadge: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(R.drawable.furniture),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        if (hasBadge) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Colors.Charizard400)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 2.dp
                    ),
                text = "new",
                style = TextStyles.bodyTwoMedium
            )
        }
    }
}

@Composable
@Preview
fun ProductCardPreview() {
    Column(modifier = Modifier.background(Color.White)) {
        ProductCard(hasBadge = true)
        Spacer(modifier = Modifier.height(16.dp))
        ProductCard()
    }
}