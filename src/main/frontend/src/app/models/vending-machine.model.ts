import { Snack } from './snack.model';

export class VendingMachine{

  private _machine_id: string;

  private _items: Snack[];

  private _deposited?: number;


  constructor(machine_id: string, stock: Snack[]) {
    this._machine_id = machine_id;
    this._items = stock;
  }


  get machine_id(): string {
    return this._machine_id;
  }

  set machine_id(value: string) {
    this._machine_id = value;
  }

  get items(): Snack[] {
    return this._items;
  }

  set items(value: Snack[]) {
    this._items = value;
  }

  get deposited(): number {
    return <number>this._deposited;
  }

  set deposited(value: number) {
    this._deposited = value;
  }
}
