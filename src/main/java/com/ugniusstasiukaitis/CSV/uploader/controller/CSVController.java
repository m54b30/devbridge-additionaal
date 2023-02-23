package com.ugniusstasiukaitis.CSV.uploader.controller;

import com.ugniusstasiukaitis.CSV.uploader.entity.EmployeeData;
import com.ugniusstasiukaitis.CSV.uploader.entity.EmployeeRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CSVController {

    @Autowired
    EmployeeRepository service;
    @PostMapping("/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception{
        List<EmployeeData> employeeList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);
        parser.parseAllRecords(inputStream);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            EmployeeData employee = new EmployeeData();
            employee.setId(Integer.parseInt(record.getString("id")));
            employee.setName(record.getString("name"));
            employee.setEmail(record.getString("email"));
            employee.setNumber(record.getString("number"));
            employeeList.add(employee);
        });
        service.saveAll(employeeList);
        return "Upload success";


    }
}
