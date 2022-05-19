import { Snack } from './snack.model';

export class VendingMachine{

  private _machine_id: string;

  private _stock: Snack[];

  private _deposited?: number;


  constructor(machine_id: string, stock: Snack[]) {
    this._machine_id = machine_id;
    this._stock = stock;
  }


  get machine_id(): string {
    return this._machine_id;
  }

  set machine_id(value: string) {
    this._machine_id = value;
  }

  get stock(): Snack[] {
    return this._stock;
  }

  set stock(value: Snack[]) {
    this._stock = value;
  }

  get deposited(): number {
    return <number>this._deposited;
  }

  set deposited(value: number) {
    this._deposited = value;
  }
}
