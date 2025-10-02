package at.rene.ferrari.xml_to_compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import at.rene.ferrari.xml_to_compose.ui.theme.XmltocomposeTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container,
            AuthFragment(),
            "Auth"
        ).commitNow()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XmltocomposeTheme {
        Greeting("Android")
    }
}


@Preview
@Composable
fun BoxExamplePreview() {
    BoxExample()
}

@Composable
fun BoxExample() {
    Box(modifier = Modifier
        .size(80.dp)
        .background(Color.Blue)) {

        Box(modifier = Modifier
            .size(20.dp)
            .align(Alignment.TopStart)
            .background(Color.Red))

        Box(modifier = Modifier
            .size(20.dp)
            .align(Alignment.BottomEnd)
            .background(Color.Red))
    }
}

@Preview
@Composable
fun RowExamplePreview() {
    ColumnExample()
}

@Composable
fun ColumnExample() {

    Column(verticalArrangement = Arrangement.spacedBy(space = 16.dp)) {
        Text("Hello")
        Text("this is")
        Text("an Example")
    }
}