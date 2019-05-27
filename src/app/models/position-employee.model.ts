import { Employee } from "./employee.model";
import { Position } from "./position.model";

export class PositionEmployee {

    id: number;
    employee: Employee;
    position: Position;
    startDateForPosition: Date;
    endDateForPosition: Date;
  }