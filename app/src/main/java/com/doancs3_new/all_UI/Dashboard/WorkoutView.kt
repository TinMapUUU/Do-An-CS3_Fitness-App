package com.doancs3_new.all_UI.Dashboard

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doancs3_new.Data.Model.Workout
import com.doancs3_new.ui.theme.BabyBlue
import com.doancs3_new.ui.theme.Green
import com.doancs3_new.ui.theme.LightGray
import com.doancs3_new.ui.theme.Pink40

@Preview(showBackground = true)
@Composable
fun PreviewWorkoutView() {
    val sampleWorkouts = listOf(
        Workout("1", "Giảm cân", "Chống đẩy", "2x3", ""),
        Workout("2", "Giảm cân", "Chống đẩy", "3x4", ""),
        Workout("3", "Giảm cân", "Chống đẩy", "4x5", "")
    )
    val checkedStates = listOf(false, true, false)
    WorkoutView(
        dayNumber = 1,
        workouts = sampleWorkouts,
        checkedStates = checkedStates,
        onCheckChanged = { index, isChecked ->
            println("Checkbox at index $index changed to $isChecked")
        },
        onAllChecked = {
            println("All checkboxes checked")
        }
    )
}

@Composable
fun WorkoutView(
    dayNumber: Int,
    workouts: List<Workout>,
    onCheckChanged: (Int, Boolean) -> Unit,
    checkedStates: List<Boolean>,
    onAllChecked: () -> Unit // ✅ callback gọi updateCheckpointProgress()
) {
    val context = LocalContext.current

    // Kiểm tra nếu tất cả đều đã tick
    val allChecked = checkedStates.all { it }

    // Gọi callback khi tick đủ (dùng remember để không gọi nhiều lần)
    LaunchedEffect(allChecked) {
        if (allChecked) {
            onAllChecked() // Gọi từ bên ngoài class để xử lý updateCheckpoint
        }
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { }
            .border(3.dp, Green, RoundedCornerShape(15.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = BabyBlue),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Ngày $dayNumber",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            workouts.forEachIndexed { index, workout ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(workout.videoUrl))
                            context.startActivity(intent) // ✅ Gọi trực tiếp ở đây
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = LightGray),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = workout.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Text(
                                text = workout.description,
                                fontSize = 13.sp,
                                color = Pink40
                            )
                        }
                        Checkbox(
                            checked = checkedStates[index],
                            enabled = !allChecked,
                            onCheckedChange = { isChecked ->
                                onCheckChanged(index, isChecked)
                            }
                        )
                    }
                }
            }
        }
    }
}




