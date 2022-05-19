import { StockConfig } from './stock-config.model';

export interface ApiRequest {
  config: StockConfig;

  items: {
    name: string;
    amount: number;
    price: string;
  }
}
