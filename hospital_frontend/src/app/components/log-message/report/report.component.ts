import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Report } from 'src/app/models/report';
import { ReportService } from 'src/app/services/report.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  constructor(
    private reportService: ReportService,
    private formBuilder: FormBuilder
  ) { }

  report: Report;
  pending = true;
  searchForm = this.formBuilder.group({
    start: [''],
    end: ['']
  });

  getReport(): void{
    this.pending = true;
    // tslint:disable-next-line: deprecation
    this.reportService.report(this.searchForm.value.start, this.searchForm.value.end).subscribe(
      (report: Report) => {
        this.pending = false;
        this.report = report;
      }
    );
  }

  ngOnInit(): void {
    this.getReport();
  }

}
