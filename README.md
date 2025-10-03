Transform the XML UI to Compose


fragment_auth.xml holds the Layout
Don’t care about the actual logic, just build the UI
You will need following Compose components:
Text
Button
TextField
Pro: On Login Click show a LinearProgressIndicator
Questions? Just ask
Don’t put it in AI please, you won’t learn anything if you do


OutlinedTextField / Button

var email by remember { mutableStateOf("") }
OutlinedTextField(
    value = email,
    onValueChange = { email = it },
    label = { Text("Email") },
    placeholder = { Text("user@example.com") },
)

Button(onClick = { }) {
    Text("Press me \uD83D\uDC95")
}
