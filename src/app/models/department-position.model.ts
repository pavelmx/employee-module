import { Employee } from "./employee.model";
import { Position } from "./position.model";
import { Department } from "./department.model";
import { PositionEmployee } from "./position-employee.model";

export class DepartmentPosition {
    id: number;
    position: Position;
    department: Department;
    startDate: Date;
    endDate: Date;
  }