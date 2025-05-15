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
                videoUrl = "https://www.youtube.com/watch?v=c4DAnQ6DtF8",
                subDescr = "Nhảy nhẹ lên, đồng thời đưa hai chân dang rộng sang hai bên và hai tay vung lên qua đầu (chắp tay nếu được).\n" +
                            " Nhảy trở lại vị trí ban đầu.\n" +
                            " Luôn giữ đầu gối hơi gập nhẹ khi tiếp đất, siết cơ bụng để giữ thăng bằng.\n" +
                            " 3–4 hiệp × 30–50 cái/hiệp\n" +
                            " Nghỉ 15–30 giây giữa các hiệp.\n" +
                            " Tổng thời gian: khoảng 4–6 phút"
            ),
            Workout(
                id = "2",
                type = "Giảm cân",
                title = "Bài tập Mountain Climbers",
                description = "Tăng cường đốt mỡ bụng và cải thiện độ bền toàn thân",
                videoUrl = "https://www.youtube.com/watch?v=nmwgirgXLYM",
                subDescr = "▸ Bắt đầu ở tư thế plank cao (chống tay duỗi thẳng, người thẳng từ đầu đến gót).\n" +
                        "▸ Siết chặt cơ bụng.\n" +
                        "➤ Kéo gối phải về phía ngực càng gần càng tốt mà không nâng hông.\n" +
                        "➤ Đổi chân liên tục như đang “chạy bộ tại chỗ” trong tư thế plank.\n" +
                        "Lưu ý: Không được đẩy hông quá cao; duy trì nhịp thở đều, tránh đạp chân loạn xạ.\n" +
                        "▸ 3–4 hiệp × 20–30 lần mỗi chân\n " +
                        "▸ Nghỉ 20–30 giây giữa các hiệp\n" +
                        "▸ Tổng thời gian: khoảng 5–7 phút"
            ),
            Workout(
                id = "3",
                type = "Giảm cân",
                title = "Bài tập Burpees",
                description = "Bài tập toàn thân giúp đốt cháy calo cực hiệu quả.",
                videoUrl = "https://www.youtube.com/watch?v=dZgVxmf6jkA",
                subDescr = "Đứng thẳng, hai chân rộng bằng vai.\n" +
                        "Ngồi xổm xuống, chống hai tay xuống đất (tư thế như squat low).\n" +
                        "Bật chân ra sau thành tư thế plank cao (giống hít đất).\n" +
                        "(Tuỳ cấp độ) Thực hiện 1 cái hít đất nếu muốn tăng độ khó.\n" +
                        "Bật chân trở lại tư thế squat.\n" +
                        "Bật nhảy lên cao, tay đưa qua đầu – rồi lặp lại.\n" +
                        "Lưu ý: Giữ lưng thẳng khi squat, không đổ vai quá thấp khi chống tay.\n" +
                        "▸ 3–5 hiệp × 8–15 cái\n" +
                        "▸ Nghỉ 30–45 giây giữa các hiệp\n" +
                        "▸ Tổng thời gian: 6–10 phút"
            ),
            Workout(
                id = "10",
                type = "Giảm cân",
                title = "Bài tập High Knees",
                description = "Bài tập cardio toàn thân giúp tăng nhịp tim và đốt cháy calo",
                videoUrl = "https://www.youtube.com/watch?v=sTvekaq6vOU",
                subDescr = "Đứng thẳng, chạy tại chỗ nâng gối càng cao càng tốt (ngang bụng). Tay đánh theo nhịp.\n" +
                        "Lưu ý: Siết bụng, nhón mũi chân, giữ nhịp đều.\n" +
                        "  3–4 hiệp × 30–45 giây\n" +
                        " Nghỉ 20 giây giữa hiệp | Tổng: 6–8 phút\n"
            ),
            Workout(
                id = "11",
                type = "Giảm cân",
                title = "Bài tập Skater Jumps",
                description = "Bài tập cardio toàn thân giúp tăng nhịp tim và đốt cháy calo",
                videoUrl = "https://www.youtube.com/watch?v=sTvekaq6vOU",
                subDescr = "Nhảy sang trái/phải như trượt băng, chân sau chéo ra sau, tay vung theo hướng ngược.\n" +
                        "Hiệp: 3–4 hiệp × 20 reps (10 mỗi bên)\n" +
                        "Thời gian: Nghỉ 30 giây | Tổng: 6 phút\n" +
                        "Lưu ý: Gối mềm, đáp nhẹ, giữ thăng bằng\n"
            ),
            Workout(
                id = "12",
                type = "Giảm cân",
                title = "Bài tập Jump Squats",
                description = "Bài tập cardio toàn thân giúp tăng nhịp tim và đốt cháy calo",
                videoUrl = "https://www.youtube.com/watch?v=sTvekaq6vOU",
                subDescr = "Squat xuống rồi bật nhảy mạnh lên cao, tiếp đất nhẹ nhàng và lặp lại.\n" +
                        "Hiệp: 3 hiệp × 12–15 cái\n" +
                        "Thời gian: Nghỉ 45 giây | Tổng: 6 phút\n" +
                        "Lưu ý: Không đẩy gối vượt mũi chân, dùng lực mông để bật"
            ),
// TĂNG CÂN
            Workout(
                id = "4",
                type = "Tăng cân",
                title = "Bài tập Chống đẩy",
                description = "Tăng cường cơ ngực và tay sau, giúp phát triển sức mạnh phần thân trên",
                videoUrl = "https://www.youtube.com/watch?v=_l3ySVKYVJ8",
                subDescr = "Bắt đầu ở tư thế plank cao: hai tay chống xuống đất rộng bằng vai (hoặc hơi rộng hơn một chút), người giữ thẳng từ đầu tới gót chân.\n" +
                        "Siết cơ bụng, hạ người xuống bằng cách gập khuỷu tay sang hai bên, ngực hạ gần sát mặt đất (cách 3–5 cm).\n" +
                        "Đẩy ngược cơ thể lên lại vị trí ban đầu bằng lực từ tay và ngực.\n" +
                        "Lưu ý: Không cong lưng, không đẩy mông cao. Giữ nhịp thở: hít vào khi hạ xuống, thở ra khi đẩy lên.\n" +
                        "3 hiệp × 8–12\n" +
                        "▸ Nghỉ 45–60 giây giữa các hiệp\n" +
                        "▸ Tổng thời gian: 6–8 phút"
            ),
            Workout(
                id = "5",
                type = "Tăng cân",
                title = "Bài tập Squats",
                description = "Phát triển cơ đùi và mông, cải thiện sức mạnh phần thân dưới.",
                videoUrl = "https://www.youtube.com/watch?v=aclHkVaku9U",
                subDescr = "▸ Đứng thẳng, hai chân rộng bằng vai.\n" +
                        "▸ Hạ người xuống như đang ngồi ghế, đẩy hông về sau, gối không vượt mũi chân, lưng giữ thẳng.\n" +
                        "▸ Khi đùi song song mặt đất, siết mông và đẩy người đứng lên.\n" +
                        "Lưu ý: Siết bụng, mắt nhìn thẳng, giữ trọng tâm ở gót chân.\n" +
                        "▸ 3–4 hiệp × 12–20 cái/hiệp\n" +
                        "▸ Nghỉ 30–45 giây giữa các hiệp\n" +
                        "▸ Tổng thời gian: 6–8 phút"
            ),
            Workout(
                id = "6",
                type = "Tăng cân",
                title = "Bài tập Plank",
                description = "Tăng cường cơ core, cải thiện sự ổn định và tư thế cơ thể.",
                videoUrl = "https://www.youtube.com/watch?v=pSHjTRCQxIw",
                subDescr = "▸ Chống hai khuỷu tay xuống đất, vai thẳng hàng với khuỷu tay.\n" +
                        "▸ Duỗi thẳng người từ đầu đến gót, siết bụng, không võng lưng hay đẩy mông cao.\n" +
                        "▸ Giữ yên tư thế càng lâu càng tốt.\n" +
                        "Lưu ý: Hít thở đều, siết core liên tục.\n" +
                        "3 hiệp × 30–45 giây\n" +
                        "▸ Nghỉ 30 giây giữa các hiệp\n" +
                        "▸ Tổng thời gian: 5–7 phút"
            ),
            Workout(
                id = "13",
                type = "Tăng cân",
                title = "Bài tập Bulgarian Split Squat",
                description = "Tăng cường cơ core, cải thiện sự ổn định và tư thế cơ thể.",
                videoUrl = "https://www.youtube.com/watch?v=TEXl2b3__S4",
                subDescr = "Một chân đặt lên ghế/ghế thấp sau lưng, chân còn lại squat xuống.\n" +
                        "Hiệp: 3 hiệp × 10–12 cái mỗi chân\n" +
                        "Thời gian: Nghỉ 45–60 giây | Tổng: 8–10 phút\n" +
                        "Lưu ý: Giữ thăng bằng, siết mông\n"
            ),
            Workout(
                id = "14",
                type = "Tăng cân",
                title = "Bài tập Pike Push-ups",
                description = "Tăng cường cơ core, cải thiện sự ổn định và tư thế cơ thể.",
                videoUrl = "https://www.youtube.com/watch?v=9NLyrC2joJs",
                subDescr = "Từ tư thế chống đẩy, nâng mông cao hình chữ V ngược, chống đẩy để tập vai.\n" +
                        "Hiệp: 3 hiệp × 8–12 cái\n" +
                        "Thời gian: Nghỉ 45 giây | Tổng: 6 phút\n" +
                        "Lưu ý: Không đổ vai ra sau, tập trung vào vai\n"

            ),
// GIỮ DÁNG
            Workout(
                id = "7",
                type = "Giữ dáng",
                title = "Bài tập Plank",
                description = "Tăng cường cơ core, cải thiện sự ổn định và tư thế cơ thể.",
                videoUrl = "https://www.youtube.com/watch?v=pSHjTRCQxIw",
                subDescr = "▸ Chống hai khuỷu tay xuống đất, vai thẳng hàng với khuỷu tay.\n" +
                        "▸ Duỗi thẳng người từ đầu đến gót, siết bụng, không võng lưng hay đẩy mông cao.\n" +
                        "▸ Giữ yên tư thế càng lâu càng tốt.\n" +
                        "Lưu ý: Hít thở đều, siết core liên tục.\n" +
                        "3 hiệp × 30–45 giây\n" +
                        "▸ Nghỉ 30 giây giữa các hiệp\n" +
                        "▸ Tổng thời gian: 5–7 phút"
            ),
            Workout(
                id = "8",
                type = "Giữ dáng",
                title = "Bài tập Lunges",
                description = "Giúp săn chắc phần thân dưới và cải thiện cân bằng.",
                videoUrl = "https://www.youtube.com/watch?v=COKYKgQ8KR0",
                subDescr = "▸ Đứng thẳng, bước một chân về phía trước.\n" +
                        "▸ Hạ gối sau gần chạm đất, đầu gối trước vuông góc, thân trên giữ thẳng.\n" +
                        "▸ Đẩy về vị trí ban đầu, đổi chân.\n" +
                        "Lưu ý: Gót chân trước tiếp đất chắc, không để đầu gối vượt mũi chân.\n" +
                        "▸ 3–4 hiệp × 10–12 lần mỗi chân (tổng 20–24 reps/hiệp)\n" +
                        "▸ Có thể tăng độ khó với tạ tay nhẹ\n" +
                        "▸ Nghỉ 30–45 giây giữa các hiệp\n" +
                        "▸ Tổng thời gian: 6–8 phút"
            ),
            Workout(
                id = "9",
                type = "Giữ dáng",
                title = "Bài tập Bicycle Crunches",
                description = "Bài tập Bicycle Crunches",
                videoUrl = "https://www.youtube.com/watch?v=9FGilxCbdz8",
                subDescr = "▸ Nằm ngửa, hai tay để sau đầu, chân nâng lên tạo góc 90 độ.\n" +
                        "▸ Đưa khuỷu tay phải chạm gối trái, đồng thời duỗi chân phải ra.\n" +
                        "▸ Đổi bên liên tục như đạp xe chéo bụng.\n" +
                        "Lưu ý: Không dùng tay kéo cổ; siết bụng, thực hiện chậm có kiểm soát.\n" +
                        "▸ 3–4 hiệp × 20–30 lần\n" +
                        "▸ Nghỉ 20–30 giây giữa các hiệp\n" +
                        "▸ Tổng thời gian: 5–7 phút"
            ),
            Workout(
                id = "15",
                type = "Giữ dáng",
                title = "Bài tập Glute Bridge",
                description = "Bài tập Glute Bridge",
                videoUrl = "https://www.youtube.com/watch?v=tqp5XQPpTxY",
                subDescr = "Nằm ngửa, gập gối, nâng mông lên cao, siết mông – giữ 1 giây rồi hạ xuống.\n" +
                        "Hiệp: 3–4 hiệp × 15–20 cái\n" +
                        "Thời gian: Nghỉ 30 giây | Tổng: 6 phút\n" +
                        "Lưu ý: Siết mông ở đỉnh, không dùng lưng dưới\n"
            ),
            Workout(
                id = "16",
                type = "Giữ dáng",
                title = "Bài tập Superman Hold",
                description = "Bài tập Superman Hold",
                videoUrl = "https://www.youtube.com/watch?v=kTMBsUwEGPM",
                subDescr = "Nằm sấp, nâng tay và chân khỏi mặt đất, siết lưng giữ trong 30–60 giây.\n" +
                        "Hiệp: 3 hiệp × 30–45 giây\n" +
                        "Thời gian: Nghỉ 30 giây | Tổng: 5 phút\n" +
                        "Lưu ý: Nhìn xuống, không gồng cổ"
            ),
            Workout(
                id = "17",
                type = "Giữ dáng",
                title = "Bài tập Wall Sit",
                description = "Bài tập Wall Sit",
                videoUrl = "https://www.youtube.com/watch?v=6Li55TURhVg",
                subDescr = "Dựa lưng vào tường, ngồi xuống tạo góc vuông ở gối, giữ nguyên tư thế.\n" +
                        "Hiệp: 3 hiệp × 30–60 giây\n" +
                        "Thời gian: Nghỉ 30 giây | Tổng: 5–6 phút\n" +
                        "Lưu ý: Gối vuông góc, lưng áp sát tường"
            ),
        )
//        // Thêm vào Firestore
//        workoutRepository.insertWorkout(workoutList)
        //Updat vào firebase
        workoutList.forEach { workout ->
            workoutRepository.insertOrUpdateWorkout(workout)
        }
    }
}

