package com.doancs3_new.Data.Logic

import com.doancs3_new.Data.Model.ProgressLog
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChangeCheckpoint(private val firestore: FirebaseFirestore) {

    /**
     * Cập nhật checkpoint mỗi khi người dùng hoàn thành 3 ngày tập.
     * @param uid: UID của người dùng
     * @param currentWeight: Cân nặng hiện tại của người dùng
     * @param currentHeight: Chiều cao của người dùng
     * @param targetWeight: Cân nặng mục tiêu
     * @param currentDay: Ngày hiện tại
     */
    fun updateCheckpoint(
        uid: String,
        currentWeight: Int,
        currentHeight: Int,
        targetWeight: Int,
        currentDay: Int
    ) {
        // Lấy kết quả BMI cho hiện tại và mục tiêu
        val bmiResult = BMICalculator.analyzeBMI(currentWeight, targetWeight, currentHeight)
        val currentDate = Date()

        // Định dạng ngày tháng theo kiểu "dd/MM - HH:mm"
        val dateFormat = SimpleDateFormat("dd/MM - HH:mm", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)

        // Lưu log thay đổi vào Firestore
        val log = ProgressLog(
            uid = uid,
            weight = currentWeight,
            bmi = bmiResult.currentBMI,
            date = formattedDate,
            day = currentDay,
            description = bmiResult.description
        )

        firestore.collection("progressLogs")
            .add(log)
            .addOnSuccessListener {
                println("Checkpoint cập nhật thành công for UID: $uid at Day $currentDay with BMI: ${bmiResult.currentBMI}")
            }
            .addOnFailureListener {
                println("Lỗi update checkpoint: ${it.message}")
            }

        // Kiểm tra sự thay đổi so với mục tiêu
        checkProgress(uid, currentWeight, targetWeight, currentDay, bmiResult)
    }

    /**
     * Kiểm tra sự tiến bộ của người dùng và đưa ra thông báo (tăng cân, giảm cân, giữ dáng)
     * @param uid: UID của người dùng
     * @param currentWeight: Cân nặng hiện tại của người dùng
     * @param targetWeight: Cân nặng mục tiêu
     * @param currentDay: Ngày hiện tại (mỗi 3 ngày)
     * @param bmiResult: Kết quả BMI hiện tại và mục tiêu
     */
    private fun checkProgress(
        uid: String,
        currentWeight: Int,
        targetWeight: Int,
        currentDay: Int,
        bmiResult: BMIResult
    ) {
        // Tính toán mức độ thay đổi cân nặng
        val weightDifference = currentWeight - targetWeight

        // So sánh với mục tiêu của người dùng
        when {
            weightDifference > 0 -> {
                println("User $uid: cần phải giảm cân. Current BMI: ${bmiResult.currentBMI}")
            }
            weightDifference < 0 -> {
                println("User $uid: cần phải tăng cân. Current BMI: ${bmiResult.currentBMI}")
            }
            else -> {
                println("User $uid: đang giữ dáng! Current BMI: ${bmiResult.currentBMI}")
            }
        }

        // Logic theo dõi quá trình thay đổi sau mỗi 3 ngày
        if (currentDay % 3 == 0) {
            println("User $uid: Updated progress on Day $currentDay with current BMI: ${bmiResult.currentBMI}")
        }
    }
}
