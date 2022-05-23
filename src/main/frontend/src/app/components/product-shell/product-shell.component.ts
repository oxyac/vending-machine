import { Component, OnInit } from '@angular/core';
import { VendingMachineService } from '../../services/vending-machine.service';
import { Snack } from '../../models/snack.model';

@Component({
  selector: 'app-product-shell',
  templateUrl: './product-shell.component.html',
  styleUrls: ['./product-shell.component.css']
})
export class ProductShellComponent implements OnInit {

  constructor(public vmService: VendingMachineService) {
  }

  ngOnInit(): void {
    this.vmService.refreshState();
  }

  buy(snack: Snack) {
    this.vmService.buy(snack).subscribe((r) => {
      this.vmService.refreshState();
    })
  }
}
