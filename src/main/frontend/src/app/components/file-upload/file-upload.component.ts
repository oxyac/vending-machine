import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { VendingMachineService } from '../../services/vending-machine.service';
import { ApiRequest } from '../../models/api-request.model';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent{

  numberRegEx = /\-?\d*\.?\d{1,2}/;

  apiRequest = this.fb.group({
    config: this.fb.group({
      rows: ['', [Validators.required, Validators.pattern(this.numberRegEx), Validators.max(20), Validators.min(1)]],
      columns: ['', [Validators.required, Validators.pattern(this.numberRegEx), Validators.max(20), Validators.min(1)]],
    }),
    items: this.fb.array([
      this.fb.group({
        name: ['Snickers', Validators.required],
        amount: [0, Validators.required],
        price: [0.11, [Validators.required,  Validators.max(1000), Validators.min(0.10)]]
      })
    ], Validators.required)
  });

  get rows() {
    return this.apiRequest.get('config.rows');
  }

  get columns() {
    return this.apiRequest.get('config.columns');
  }

  get items() {
    return this.apiRequest.get('items') as FormArray;
  }

  get name(){
    return this.apiRequest.get('items')?.get('name') as FormControl;
  }

  addItem() {
    this.items.push(this.fb.group({
      name: ['', Validators.required],
      amount: ['', Validators.required],
      price: ['', Validators.required]
    }));
  }

  constructor(private fb: FormBuilder,
              private vmService : VendingMachineService) {
    this.updateConfig();

  }


  onSubmit() {
    this.vmService.loadStock(<ApiRequest>this.apiRequest.value);
  }

  updateConfig() {
    this.apiRequest.patchValue({
        config: {
          rows: 4,
          columns: "8"
        }
      }
    );
  }

  removeItem(pos : number) {
    this.items.removeAt(pos);
  }
}
