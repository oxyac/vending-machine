import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent{

  apiRequest = this.fb.group({
    config: this.fb.group({
      rows: ['', Validators.required],
      columns: ['', Validators.required],
    }),
    items: this.fb.array([
      this.fb.group({
        name: ['', Validators.required],
        amount: ['', Validators.required],
        price: ['', Validators.required]
      })
    ], Validators.required)
  });

  get items() {
    return this.apiRequest.get('items') as FormArray;
  }

  addItem() {
    this.items.push(this.fb.group({
      name: ['', Validators.required],
      amount: ['', Validators.required],
      price: ['', Validators.required]
    }));
  }

  constructor(private fb: FormBuilder) {
    this.updateConfig();
  }


  onSubmit() {
    // TODO
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
}
