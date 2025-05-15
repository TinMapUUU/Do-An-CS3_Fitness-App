package com.doancs3_new.all_UI.Dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.doancs3_new.Data.Model.Workout
import com.doancs3_new.Viewmodel.ListWorkoutViewModel

@Composable
fun ListWorkout(
    viewModel: ListWorkoutViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val workouts by viewModel.workouts

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Danh sách bài tập của bạn",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            textAlign = TextAlign.Center
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(workouts) { workout ->
                ExpandableWorkoutCard(workout)
            }
        }
    }
}


@Composable
fun ExpandableWorkoutCard(workout: WorkoutItem) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .border(
                width = 2.dp,
                color = Color.Gray, // Chỉnh màu viền ở đây
                shape = RoundedCornerShape(16.dp) // Phải khớp với shape của Card
            )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = workout.title,
                style = MaterialTheme.typography.titleMedium
            )
// animation đổ xuống khi click vào
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = workout.description, // hiện mô tả
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = workout.subDescr, // hiện chi tiết mô tả
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
data class WorkoutItem(
    val title: String,
    val description: String,
    val subDescr: String
)

fun Workout.toWorkoutItem(): WorkoutItem {
    return WorkoutItem(
        title = this.title,
        description = this.description,
        subDescr = this.subDescr // hoặc video URL, duration, v.v.
    )
}


