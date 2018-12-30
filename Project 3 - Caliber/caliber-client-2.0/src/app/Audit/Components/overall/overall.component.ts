import { Component, OnInit } from '@angular/core';
import { Batch } from 'src/app/Batch/type/batch';

@Component({
  selector: 'app-overall',
  templateUrl: './overall.component.html',
  styleUrls: ['./overall.component.css']
})

export class OverallComponent implements OnInit {
qcStatusTypes = [];
batch: Batch;
qcBatchAssess: number;
  constructor() { }

  ngOnInit() {
  }

  pickOverallStatus(batch, pick) {
this.batch = batch;
this.qcBatchAssess = pick;
  }

}
