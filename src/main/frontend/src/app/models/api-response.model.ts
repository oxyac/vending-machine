import { LoadStock } from './load-stock.model';
import { Snack } from './snack.model';

export interface ApiResponse {
  welcomeMessage: string;

  availableRoutes: Map<string, string>;

  serialNumber: string;

  items: Snack[];
}
