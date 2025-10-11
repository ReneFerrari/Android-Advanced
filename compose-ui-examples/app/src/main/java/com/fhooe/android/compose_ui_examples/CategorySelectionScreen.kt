package com.fhooe.android.compose_ui_examples

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//UI: https://www.figma.com/design/k2lSw9VFAhPeI92E0kuf5L/Ulmo-E-Commerce-UI-kit--Community-?node-id=127-22&m=dev
//Show why paddingValues are needed
//Explain Scaffold
//First build LazyColumn including Button
//Explain splitting of Screen into smaller Composables
//Explain why its not working / offer solution
//mention preview requires surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectionScreen() {
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
                },
                actions = {
                    Text(
                        "Clear",
                        modifier = Modifier.padding(end = 16.dp),
                        style = TextStyles.bodyOneMedium
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ComponentButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "25 Items selected"
            ) { }
        }
    ) { paddingValues ->
        Box(
            Modifier.padding(paddingValues)
        ) {
            LazyColumn {
                items(20) {
                    CategoryCell()
                }
            }
        }
    }
}

@Composable
private fun CategoryCell() = Surface {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .requiredHeight(64.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Cell",
            style = TextStyles.bodyOneRegular
        )
        Spacer(modifier = Modifier.weight(1f))
        CircularCheckbox(
            checked = true,
            onCheckedChange = {},
        )
    }
}

@Composable
private fun CircularCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(if (checked) Colors.Charizard400 else Colors.Giratina100)
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center,
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Checked",
                tint = Colors.Black,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
private fun ComponentButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) = Button(
    shape = RoundedCornerShape(8.dp),
    colors = ButtonDefaults.buttonColors(
        containerColor = Colors.Charizard400
    ),
    modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(64.dp)
        .then(modifier),
    onClick = {
        onClick()
    }
) {
    Text(
        text = text,
        style = TextStyles.bodyOneMedium
    )
}

@Preview
@Composable
fun ComponentButtonPreview() {
    ComponentButton(text = "Press me", onClick = {})
}

@Preview
@Composable
private fun CategoryCellPreview() {
    CategoryCell()
}

@Preview
@Composable
fun CategorySelectionScreenPreview() {
    CategorySelectionScreen()
}
