package com.testdemo.apps;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.testdemo.apps.databinding.ActivityChartBinding;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    private String TAG="ChartActivity";
private ActivityChartBinding binding;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lineChart=binding.chart1;
       /* setupLineChart(); lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);*/

        // Remove description label
     /*   Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);*/

        // Customize legend
        /*Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextColor(Color.BLACK);
        loadChartData();*/


        binding.etName.setLongClickable(false);


    }


    private void setupLineChart() {
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);

        // Remove background grid lines
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        // Remove description label
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);

        // Customize legend
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextColor(Color.BLACK);


        // Disable zooming
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDoubleTapToZoomEnabled(false);
    }
    private void loadChartData() {
        List<Entry> entries = new ArrayList<>();

        // Sample data
        entries.add(new Entry(0, 1));
        entries.add(new Entry(1, 2));
        entries.add(new Entry(2, 0));
        entries.add(new Entry(3, 4));
        entries.add(new Entry(4, 3));

        LineDataSet dataSet = new LineDataSet(entries, "Sample Data");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setDrawValues(true);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh the chart
    }
}