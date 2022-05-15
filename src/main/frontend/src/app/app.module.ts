import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { VendingMachineComponent } from './components/vending-machine/vending-machine.component';
import { ProductShellComponent } from './components/product-shell/product-shell.component';
import { ProductComponent } from './components/product/product.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from './componenets/header/header.component';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    VendingMachineComponent,
    ProductShellComponent,
    ProductComponent,
    DashboardComponent,
    FileUploadComponent,
    HeaderComponent,
    NgbModule
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
