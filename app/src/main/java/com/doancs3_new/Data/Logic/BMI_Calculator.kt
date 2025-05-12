package com.doancs3_new.Data.Logic

// Định nghĩa cấu trúc dữ liệu trả về
data class BMIResult(
    val currentBMI: Double,
    val targetBMI: Double,
    val description: String,
    val goal: String
)

object BMICalculator {
    // Tính BMI
    fun calculateBMI(currentWeight: Int, currentHeight: Int): Double {
        val heightM = currentHeight / 100.0
        return currentWeight / (heightM * heightM)
    }

    // Mô tả trạng thái BMI hiện tại
    fun getBMIDescription(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Thiếu cân"
            bmi < 25 -> "Bình thường"
            bmi < 30 -> "Thừa cân"
            else -> "Béo phì"
        }
    }

    // Xác định mục tiêu tập luyện dựa theo so sánh BMI hiện tại và BMI mong muốn
    fun determineGoalFromBMI(
        currentWeight: Int,
        targetWeight: Int,
        currentHeight: Int
    ): String {
        val currentBMI = calculateBMI(currentWeight, currentHeight)
        val targetBMI = calculateBMI(targetWeight, currentHeight)

        return when {
            targetBMI > currentBMI -> "Tăng cân"
            targetBMI < currentBMI -> "Giảm cân"
            else -> "Giữ dáng"
        }
    }

    // Gói toàn bộ kết quả xử lý vào data class để dễ sử dụng
    fun analyzeBMI(
        currentWeight: Int,
        targetWeight: Int,
        currentHeight: Int
    ): BMIResult {
        val currentBMI = calculateBMI(currentWeight, currentHeight)
        val targetBMI = calculateBMI(targetWeight, currentHeight)
        val goal = determineGoalFromBMI(currentWeight, targetWeight, currentHeight)
        val description = getBMIDescription(currentBMI)

        return BMIResult(
            currentBMI = currentBMI,
            targetBMI = targetBMI,
            description = description,
            goal = goal
        )
    }
}




