import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AppComponent} from "./app.component";
import { ProductShellComponent } from './components/product-shell/product-shell.component';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import { VendingMachineComponent } from './components/vending-machine/vending-machine.component';

const routes: Routes = [
  {
    path: '',
    component: VendingMachineComponent
  },
  {
    path: 'load',
    component: FileUploadComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
