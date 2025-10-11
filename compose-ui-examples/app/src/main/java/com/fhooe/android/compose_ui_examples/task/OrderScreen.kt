package com.fhooe.android.compose_ui_examples.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.AddLocation
import androidx.compose.material.icons.outlined.DriveEta
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fhooe.android.compose_ui_examples.Colors
import com.fhooe.android.compose_ui_examples.R
import com.fhooe.android.compose_ui_examples.TextStyles

/*
Implement screen from here: https://www.figma.com/design/k2lSw9VFAhPeI92E0kuf5L/Ulmo-E-Commerce-UI-kit--Community-?node-id=139-1110&m=dev
Ignore the Bottombar, just focus on the screen
Ensure the whole screen is scrollable.
Use Modifier.scrollable() on the parent Composable to achieve that.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Colors.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                title = {
                    Text(
                        text = "Category",
                        style = TextStyles.bodyOneMedium
                    )
                }
            )
        }
    ) { contentPadding ->
        Column(Modifier.padding(contentPadding).padding(horizontal = 16.dp)) {
            Text(
                style = TextStyles.headingTwoSemibold,
                text = "yesterday, 10:00 AM"
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Waiting for Payment",
                style = TextStyles.bodyTwoRegular
            )
            Spacer(Modifier.height(16.dp))
            Spacer(Modifier.height(16.dp))
            Order()
            Spacer(Modifier.height(24.dp))
            Order()
            Spacer(Modifier.height(16.dp))
            Spacer(Modifier.height(24.dp))
            Text(
                style = TextStyles.headingTwoSemibold,
                text = "delivery info"
            )
            TextCell(
                Icons.Outlined.DriveEta,
                title = "By courier"
            )
            TextCell(
                Icons.Outlined.AddLocation,
                title = "London, 221B Baker Street",
                description = "Hanna Gouse, +7 932 123-43-23"
            )
        }
    }
}

@Composable
fun TextCell(
    icon: ImageVector,
    title: String,
    description: String? = null
) {
    Row(modifier = Modifier.height(64.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, style = TextStyles.bodyOneRegular)
            description?.let {
                Text(description, style = TextStyles.bodyTwoRegular, color = Colors.Giratina500)
            }
        }
    }
}

@Composable
fun Order() {
    Row(modifier = Modifier.height(115.dp).fillMaxWidth()) {
        Image(
            modifier = Modifier.width(94.dp).fillMaxHeight().clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.furniture),
            contentDescription = "Product Image",
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(text = "$150.00", style = TextStyles.bodyOneMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Wooden bedside table featuring a raised design",
                style = TextStyles.bodyThreeRegular,
                color = Colors.Giratina500
            )
            Spacer(Modifier.weight(1f))
            Button(onClick = { }, shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(containerColor = Colors.Charizard400)) {
                Text("Order again", style = TextStyles.bodyTwoMedium)
            }
        }
    }
}

@Composable
@Preview
fun OrderScreenPreview() {
    OrderScreen()
}