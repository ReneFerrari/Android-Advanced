## Simple Random Number Generator
Input a number in the TextField click the Generate Button, a number from 0 to the number you put in will be generated.

Makes use of ViewModel.
Ensure that in the app/build.gradle.kts you add the dependency:

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.3")

Then you can use your ViewModel inside the Composable like this:

    @Composable
    fun RandomNumberGeneratorScreen(
        paddingValues: PaddingValues,
        viewModel: RandomNumberViewModel = viewModel()
    ) {

This will automatically remember the ViewModel for you.

The ViewModel in itself produces the Screen State, the UI Layer (Compose) passes events into it and the UI Layer observes the Screens state.  This is the core principle of Unidirectional DataFlow (UDF).

In this simple example we use Composes State directly. This is not best practice, but easier for the start.

