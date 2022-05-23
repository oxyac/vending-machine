import { Component, OnInit } from '@angular/core';
import { VendingMachineService } from '../../services/vending-machine.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(public vmService: VendingMachineService) {
  }

  ngOnInit(): void {
    this.vmService.refreshState();
  }

  deposit(amount: number) {
    this.vmService.deposit(amount).subscribe((r) => {
      this.vmService.refreshState();
    });
  }
}
