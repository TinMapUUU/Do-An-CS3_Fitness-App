package com.doancs3_new.Data.Model

import com.doancs3_new.Data.Respository.WorkoutRepository
import javax.inject.Inject

class WorkoutSeeder @Inject constructor(
    private val workoutRepository: WorkoutRepository
) {

    suspend fun Seeders() {
        val workoutList = listOf(
// GIẢM CÂN
            Workout(
                id = "1",
                type = "Giảm cân",
                title = "Bài tập Jumping Jacks",
                description = "Bài tập cardio toàn thân giúp tăng nhịp tim và đốt cháy calo",
                videoUrl = "https://www.youtube.com/watch?v=c4DAnQ6DtF8"
            ),
            Workout(
                id = "2",
                type = "Giảm cân",
                title = "Bài tập Mountain Climbers",
                description = "Tăng cường đốt mỡ bụng và cải thiện độ bền toàn thân",
                videoUrl = "https://www.youtube.com/watch?v=nmwgirgXLYM"
            ),
            Workout(
                id = "3",
                type = "Giảm cân",
                title = "Bài tập Burpees",
                description = "Bài tập toàn thân giúp đốt cháy calo cực hiệu quả.",
                videoUrl = "https://www.youtube.com/watch?v=dZgVxmf6jkA"
            ),

// TĂNG CÂN
            Workout(
                id = "4",
                type = "Tăng cân",
                title = "Bài tập Chống đẩy",
                description = "Tăng cường cơ ngực và tay sau, giúp phát triển sức mạnh phần thân trên",
                videoUrl = "https://www.youtube.com/watch?v=_l3ySVKYVJ8"
            ),
            Workout(
                id = "5",
                type = "Tăng cân",
                title = "Bài tập Squats",
                description = "Phát triển cơ đùi và mông, cải thiện sức mạnh phần thân dưới.",
                videoUrl = "https://www.youtube.com/watch?v=aclHkVaku9U"
            ),
            Workout(
                id = "6",
                type = "Tăng cân",
                title = "Bài tập Plank",
                description = "Tăng cường cơ core, cải thiện sự ổn định và tư thế cơ thể.",
                videoUrl = "https://www.youtube.com/watch?v=pSHjTRCQxIw"
            ),

// GIỮ DÁNG
            Workout(
                id = "7",
                type = "Giữ dáng",
                title = "Bài tập Plank",
                description = "Tăng cường cơ core, cải thiện sự ổn định và tư thế cơ thể.",
                videoUrl = "https://www.youtube.com/watch?v=pSHjTRCQxIw"
            ),
            Workout(
                id = "8",
                type = "Giữ dáng",
                title = "Bài tập Lunges",
                description = "Giúp săn chắc phần thân dưới và cải thiện cân bằng.",
                videoUrl = "https://www.youtube.com/watch?v=COKYKgQ8KR0"
            ),
            Workout(
                id = "9",
                type = "Giữ dáng",
                title = "Bài tập Bicycle Crunches",
                description = "Tác động mạnh vào vùng cơ bụng chéo và cơ bụng dưới",
                videoUrl = "https://www.youtube.com/watch?v=9FGilxCbdz8"
            ),
        )
//
//        // Thêm vào Firestore
//        workoutRepository.insertWorkout(workoutList)
    }
}

