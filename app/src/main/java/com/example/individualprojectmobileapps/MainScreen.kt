package com.example.individualprojectmobileapps

import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(navController: NavHostController) {
    // list of questions for the quiz
    val questions = listOf(
        Question("Who created the Mona Lisa?", listOf("Vincent van Gogh", "Michaelangelo", "Leonardo da Vinci", "Pablo Picasso"), listOf(2)),
        Question("Who sings the song Bad Romance?", listOf("Lady Gaga", "Bruno Mars", "Michael Jackson", "Taylor Swift"), listOf(0)),
        Question("What colors make purple?", listOf("Red", "Green", "Blue", "Orange"), listOf(0, 2)),
        Question("How many legs do insects typically have?", listOf("2", "4", "6", "8"), listOf(2)),
        Question("What galaxy do we live in?", listOf("Pinwheel Galaxy", "Black Eye Galaxy", "Bodes Galaxy", "The Milky Way"), listOf(3))
    )

    // states for question index, answers, score, and dialog display
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswers by remember { mutableStateOf(emptyList<Int>()) }
    var selectedAnswer by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }
    var showScore by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    // column layout for quiz screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // popup dialog for confirmation
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("are you sure you want to proceed?") },
                text = { Text("you cannot come back to this question.") },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("proceed")
                    }
                }
            )
        }

        // show score if quiz is complete
        if (showScore) {
            Text("your score: $score / ${questions.size}", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
            Button(onClick = {
                // reset quiz state for restart
                currentQuestionIndex = 0
                score = 0
                selectedAnswers = emptyList()
                selectedAnswer = -1
                showScore = false
            }) {
                Text("restart quiz")
            }
        } else {
            val currentQuestion = questions[currentQuestionIndex]
            val isMultipleAnswerQuestion = currentQuestion.text == "What colors make purple?"

            // display the current question
            QuestionDisplay(
                question = currentQuestion,
                isMultipleAnswer = isMultipleAnswerQuestion,
                selectedAnswers = selectedAnswers,
                selectedAnswer = selectedAnswer,
                onAnswerToggle = { answerIndex ->
                    selectedAnswers = if (selectedAnswers.contains(answerIndex)) {
                        selectedAnswers - answerIndex
                    } else {
                        selectedAnswers + answerIndex
                    }
                },
                onSingleAnswerSelected = { selectedAnswer = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            var hasAttempt by remember { mutableStateOf(true) }

            // button to proceed to the next question or submit answers
            Button(
                onClick = {
                    // show dialog on first attempt, then proceed to check answer
                    if (hasAttempt) {
                        hasAttempt = false
                        showDialog = true
                    } else {
                        showDialog = false
                        // check answer and update score
                        if (isMultipleAnswerQuestion) {
                            if (selectedAnswers.sorted() == currentQuestion.correctAnswerIndices.sorted()) {
                                score++
                            }
                        } else if (selectedAnswer == currentQuestion.correctAnswerIndices.first()) {
                            score++
                        }
                        // move to next question or show final score if last question
                        if (currentQuestionIndex < questions.size - 1) {
                            currentQuestionIndex++
                            selectedAnswers = emptyList()
                            selectedAnswer = -1
                        } else {
                            showScore = true
                        }
                        hasAttempt = true
                    }
                },
                enabled = (isMultipleAnswerQuestion && selectedAnswers.isNotEmpty()) || (!isMultipleAnswerQuestion && selectedAnswer != -1)
            ) {
                Text(if (currentQuestionIndex < questions.size - 1) "next" else "submit")
            }
        }
    }
}

// function to display question and answer options
@Composable
fun QuestionDisplay(
    question: Question,
    isMultipleAnswer: Boolean,
    selectedAnswers: List<Int>,
    selectedAnswer: Int,
    onAnswerToggle: (Int) -> Unit,
    onSingleAnswerSelected: (Int) -> Unit
) {
    // show question text
    Text(question.text, fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
    question.answers.forEachIndexed { index, answer ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            // show checkbox for multiple answer questions or radiobutton for single answer
            if (isMultipleAnswer) {
                Checkbox(
                    checked = selectedAnswers.contains(index),
                    onCheckedChange = { onAnswerToggle(index) }
                )
            } else {
                RadioButton(
                    selected = index == selectedAnswer,
                    onClick = { onSingleAnswerSelected(index) }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(answer)
        }
    }
}

// question data class to hold question text, answer choices, and correct answers
data class Question(val text: String, val answers: List<String>, val correctAnswerIndices: List<Int>)

/*
store data on the device so the user can see game history and scores when logging back in (use a simple key-value storage).
*/
