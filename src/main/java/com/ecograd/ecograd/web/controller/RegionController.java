package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.Region;
import com.ecograd.ecograd.service.LitterService;
import com.ecograd.ecograd.service.RegionService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/statistic")
public class RegionController {
    private final RegionService regionService;
    private final LitterService litterService;

    public RegionController(RegionService regionService, LitterService litterService) {
        this.regionService = regionService;
        this.litterService = litterService;
    }

    @GetMapping
    public String getStatisticPage(Model model){
        List<Region> regionList = regionService.findAll();
        CategoryDataset datasetForAllRegions = createDatasetAllRegions(regionList);
        JFreeChart chart = ChartFactory.createBarChart(
                "By Region",         // chart title
                "Region",             // domain axis label
                "Value",                // range axis label
                datasetForAllRegions,                // data
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        CategoryDataset datasetForDays = createDatasetByDays(litterService.findAll());
        JFreeChart chartDays = ChartFactory.createBarChart(
                "By Days",         // chart title
                "Region",             // domain axis label
                "Value",                // range axis label
                datasetForDays,                // data
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        byte[] chartImageRegion = convertChartToImage(chart);
        byte[] chartImageDays = convertChartToImage(chartDays);
        String chartImageDaysString = encodeChartImage(chartImageDays);
        String chartImageReg = encodeChartImage(chartImageRegion);
        model.addAttribute("chartImageForAllRegions", chartImageReg);
        model.addAttribute("chartImageForDays", chartImageDaysString);
        return "regions";
    }

    private CategoryDataset createDatasetAllRegions(List<Region> regionList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Region r : regionList) {
            List<Litter> litterList = litterService.findAll();
            List<Litter> tmp = new ArrayList<>();
            for (Litter l : litterList){
                if (l.getRegion().equals(r)) tmp.add(l);
            }
            dataset.addValue(tmp.size(), r.getName(), r.getName());
        }
        return dataset;
    }
    private CategoryDataset createDatasetByDays(List<Litter> litterList){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Litter> monday = new ArrayList<>();
        List<Litter> tuesday = new ArrayList<>();
        List<Litter> wednesday = new ArrayList<>();
        List<Litter> thursday = new ArrayList<>();
        List<Litter> friday = new ArrayList<>();
        List<Litter> saturday = new ArrayList<>();
        List<Litter> sunday = new ArrayList<>();
        for (Litter l: litterList){
            if (l.getDateReported().getDayOfWeek().getValue()==1){
                monday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==2){
                tuesday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==3){
                wednesday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==4){
                thursday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==5){
                friday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==6){
                saturday.add(l);
            }
            if (l.getDateReported().getDayOfWeek().getValue()==7){
                sunday.add(l);
            }
        }
        dataset.addValue(monday.size(), "Monday", "Monday");
        dataset.addValue(tuesday.size(), "Tuesday", "Tuesday");
        dataset.addValue(wednesday.size(), "Wednesday", "Wednesday");
        dataset.addValue(thursday.size(), "Thursday", "Thursday");
        dataset.addValue(friday.size(), "Friday", "Friday");
        dataset.addValue(saturday.size(), "Saturday", "Saturday");
        dataset.addValue(sunday.size(), "Sunday", "Sunday");
        return dataset;
    }
    private byte[] convertChartToImage(JFreeChart chart) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ChartUtils.writeChartAsPNG(outputStream, chart, 400, 300);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String encodeChartImage(byte[] chartImage) {
        return new String(Base64.getEncoder().encode(chartImage));
    }
}
