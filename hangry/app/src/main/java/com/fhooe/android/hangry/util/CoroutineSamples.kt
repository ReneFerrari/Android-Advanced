package com.fhooe.android.hangry.util

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.yield


private val numbersUneven = listOf(
    "One",
    "Three",
    "Five",
    "Seven",
)

private val numbersEven = listOf(
    "Two",
    "Four",
    "Six",
    "Eight",
)

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {

    //Create a simple coroutine
    //runBlocking creates a bridge between "regular" code and the coroutine world
    //It creates a coroutine that blocks the current thread until its done
    /*runBlocking {
        numbersUneven.forEach {
            println("Number: $it")
        }
        numbersEven.forEach {
            println("Number: $it")
        }
    }*/
















    //Create two different coroutines inside the runBlocking generated Coroutine
    /*runBlocking {
        launch {
            numbersUneven.forEach {
                println("Number: $it")
            }
        }
        launch {
            numbersEven.forEach {
                println("Number: $it")
            }
        }
    }*/

















    /**
     * Switch between the coroutines with yield()
     * yield() is a suspending function that gives the thread to other coroutines to run
     */
    /*runBlocking {
        launch {
            numbersUneven.forEach {
                println("Number: $it")
                yield()
            }
        }
        launch {
            numbersEven.forEach {
                println("Number: $it")
                yield()
            }
        }
    }*/


















    /**
     * Trying to simplify the code by extracting to a fun
     * Why doesnt this work? printNumber could be called from anywhere,
     * but yield can only be called inside a coroutine as it basically
     * "pauses" the coroutine and gives the thread to other coroutines
     *
     * How can we fix this?
     * Using the suspend keyword. This allows us to execute other suspending
     * funnctions like yield(). Suspend signals the compiler this fun can be
     * paused and resumed later
     */

    /*/*suspend*/ fun printNumber(number: String) {
        println("Number: $number")
        yield()
    }

    runBlocking {
        launch {
            numbersUneven.forEach {
                printNumber(it)
            }
        }
        launch {
            numbersEven.forEach {
                printNumber(it)
            }
        }
    }*/
















    /**
     * By default all coroutines run on the main thread
     * We can change this by using Dispatchers
     * Dispatchers.IO -> optimized for network and disk operations
     * Dispatchers.Default -> optimized for CPU intensive work
     * Dispatchers.Main -> main thread, only available on Android
     *
     * Why is the order of numbers not sequentially now?
     * Dispatchers create a pool of threads and the coroutines are
     * distributed to the available threads. This means they can run
     * in parallel and the order is not guaranteed anymore. So those
     * two coroutines could each run on a different thread. Since yield
     * will hop from one coroutine to another, inside the same thread
     * order is guaranteed but not between threads
     */
    /*runBlocking {
        launch(Dispatchers.Default) {
            numbersUneven.forEach {
                println("Number: $it")
                yield()
            }
        }
        launch(Dispatchers.Default) {
            numbersEven.forEach {
                println("Number: $it")
                yield()
            }
        }
    }*/








    /**
     * How exceptions work in Coroutines
     *
     * runBlocking creates a parent coroutine
     * launch (uneven) creates a child coroutine
     * launch (even) creates another child coroutine
     *
     * launch (uneven) throws an exception
     * parent (runBlocking) is notified, it will cancel all its children
     *
     * meaning launch (even) is cancelled too!
     *
     * So by default:
     * an exception somewhere in the coroutine hierarchy will
     * cancel out the whole hierarchy. In our case the sibling and the
     * parent.
     *
     *
     * One solution: Using a try catch surrounding the code which potentially
     * throws
     */
    /*runBlocking {
        launch {
            numbersUneven.forEach {
                if (it == "One") throw RuntimeException("Uneven error!")
                println("Number: $it")
            }
        }
        launch {
            numbersEven.forEach {
                println("Number: $it")
            }
        }
    }*/












    /**
     * Handling exception using supervisor
     *
     * This will cancel the child coroutine which throws
     * but not its siblings or parent
     */
    /*runBlocking {
        supervisorScope {
            launch {
                numbersUneven.forEach {
                    if (it == "One") throw RuntimeException("Uneven error!")
                    println("Number: $it")
                }
            }
            launch {
                numbersEven.forEach {
                    println("Number: $it")
                }
            }
        }
    }*/












    /**
     * Simple Flow. Flow is built on top of coroutines
     *
     * Flow emits a stream of values that can be collected.
     * The core idea is similar to RxJava Observables, but much simpler.
     *
     * Flow is cold, meaning the code inside the flow builder does not
     * run until something collects the flow. This is different to
     * RxJava Observables which are hot by default.
     *
     * So once collect is called, the code inside the flow builder
     * is executed and the emitted values are collected.
     */
    /*runBlocking {
        val numbersFlow = flow {
            emit(1)
            emit(2)
            emit(3)
        }

        numbersFlow.collect { value ->
            println("Received: $value")
        }
    }*/


















    /**
     * Flow with delay
     * You can see values are emitted with a delay
     * We are on the main thread here, whilst delay is suspending,
     * we still block the App from being interactive as the runBlocking
     * blocks the main thread until everything inside is done
     */
    /*val numbersFlow = flow {
        for (i in 1..3) {
            delay(500)
            emit(i)
        }
    }

    runBlocking {
        numbersFlow.collect { value ->
            println("Received after delay: $value")
        }
    }*/


















    /**
     * Flow with delay not blocking the main Thread or is it?
     *
     * We use flowOn to switch to a background thread, but only for
     * the upstream work. What is upstream and downstream?
     * Upstream is everything before the flowOn operator, downstream
     * is everything after the flowOn operator.
     *
     * In our case the flow builder is upstream and the collect
     * is downstream. So the flow builder runs on a background
     * thread, but the collect runs on the main thread.
     *
     * Since runBlocking blocks the thread its running on until
     * the Coroutines inside it finish, we still block the main
     * thread. We do the emit(..) on a background thread, but
     * the collect(..) is still on the main thread.
     *
     * Hence if we'd run this in an App, we would still block its thread
     */
    /*val numbersFlow = flow {
        for (i in 1..3) {
            delay(500)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)

    runBlocking {
        numbersFlow.collect { value ->
            println("Received after delay: $value")
        }
    }*/

















    /**
     * Flow fully on background thread
     */
    /*val numbersFlow = flow {
        for (i in 1..3) {
            delay(500)
            emit(i)
        }
    }

    runBlocking(Dispatchers.IO) {
        numbersFlow.collect { value ->
            println("Received after delay: $value")
        }
    }*/








    /**
     * Transforming a Flow
     */
    /*val numbersFlow = flowOf(1, 2, 3, 4, 5)

    runBlocking {
        numbersFlow
            .filter { it % 2 == 0 }       // only even numbers
            .map { it * 10 }              // multiply by 10
            .collect { println(it) }
    }*/









    /**
     * Handling exceptions
     *
     * Using the catch operator we can catch exceptions
     * If we want to emit an item in case of an error,
     * we can use the emit function inside catch
     *
     * E.g. catch { emit(-1) } would emit -1 in case
     * of an error
     */
    /*val faultyFlow = flow {
        emit(1)
        emit(2)
        throw RuntimeException("Oops")
        emit(3)
    }

    runBlocking {
        faultyFlow
            .catch { e -> println("Caught exception: ${e.message}") }
            .collect { println(it) }
    }*/








    /**
     * Combining Flows
     *
     * combine emits a value whenever either flow emits, using the latest value from both.
     * zip would emit only when both flows have emitted a new value.
     */
    /*val flowA = flowOf(1, 2, 3).onEach { delay(300) }
    val flowB = flowOf("A", "B", "C").onEach { delay(500) }

    runBlocking {
        flowA.combine(flowB) { a, b -> "$a -> $b" }
            .collect { println(it) }
    }*/


    /**
     * Merging Flows
     *
     * merge combines multiple flows into one,
     * emitting values as soon as any upstream flow emits.
     *
     * Types must be the same for merge to work, as it merges several
     * flows into one flow.
     */
    /*val numbers = flowOf(1, 2, 3).map {
        delay(100)
        "Number $it"
    }
    val letters = flowOf("A", "B", "C").map {
        delay(50)
        "Letter $it"
    }

    runBlocking {
        merge(numbers, letters).collect { value ->
            println("Collected: $value")
        }
    }*/





    /**
     * Advanced:
     * Chaining Flows. Most often you'd use flatMapLatest.
     *
     * So you would run the first flow and based of its emissions execute
     * another Flow.
     *
     * flatMapConcat → waits for each inner flow to finish before moving to the next.
     * flatMapMerge → runs inner flows concurrently.
     * flatMapLatest → cancels previous flow if a new value comes in (useful for search queries).
     */
    /*data class User(val id: Int, val name: String)

    fun getUserById(id: Int): Flow<User> = flow {
        delay(500)
        emit(User(id, "User #$id"))
    }

    runBlocking {
        flowOf(1, 2, 3)
            .flatMapConcat { id -> getUserById(id) } // sequential fetching
            .collect { println(it) }
    }*/
}