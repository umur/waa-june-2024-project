package universityconnect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.ReportDTO;
import universityconnect.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO){
        ReportDTO report = reportService.createReport(reportDTO);
        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAllReports(){
        List<ReportDTO> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getReportById(@PathVariable long id){
        ReportDTO report = reportService.getReportById(id);
        return ResponseEntity.ok(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDTO> updateReport(@PathVariable long id,@RequestBody ReportDTO reportDTO){
        ReportDTO report = reportService.updateReport(id,reportDTO);
        return ResponseEntity.ok(report);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable long id){
        reportService.deleteReport(id);
    }

}
