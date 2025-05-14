package com.doancs3_new.all_UI.Dashboard

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import android.graphics.Color as AndroidColor

@Composable
fun LineChartView(
    modifier: Modifier = Modifier,
    entries: List<Entry>,
    dateLabels: List<String>,
    targetWeight: Float?,
    context: Context
) {
    AndroidView(
        factory = {
            LineChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                description.isEnabled = false
                axisRight.isEnabled = false
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.granularity = 1f
                xAxis.setLabelCount(entries.size, true)
                xAxis.setAvoidFirstLastClipping(true)
                xAxis.valueFormatter = IndexAxisValueFormatter(dateLabels)

                setTouchEnabled(true)
                setPinchZoom(true)
                animateX(1000)
                val dataSets = mutableListOf<ILineDataSet>()

                // Line 1: actual progress
                val progressSet = LineDataSet(entries, "Cân nặng").apply {
                    color = AndroidColor.parseColor("#4285F4")
                    valueTextColor = AndroidColor.parseColor("#4285F4")
                    lineWidth = 2f
                    circleRadius = 4f
                    setDrawValues(true)
                    setDrawCircles(true)
                }
                dataSets.add(progressSet)

                // Line 2: target weight (ngang)
                targetWeight?.let { weight ->
                    val targetEntries = entries.map { entry ->
                        Entry(entry.x, weight) // giữ x như log
                    }
                    val targetSet = LineDataSet(targetEntries, "Mục tiêu").apply {
                        color = AndroidColor.parseColor("#FF5722")
                        valueTextColor = AndroidColor.parseColor("#FF5722")
                        lineWidth = 2f
                        setDrawCircles(false)
                        setDrawValues(false)
                        enableDashedLine(10f, 10f, 0f) // đường gạch đứt
                    }
                    dataSets.add(targetSet)
                }
                data = LineData(dataSets)
                invalidate()
            }
        },
        modifier = modifier
    )
}




