import { Employee } from "./employee.model";
import { Department } from "./department.model";
import { PositionEmployee } from "./position-employee.model";

export class DepartmentEmployee {

    id: number;
    employee: Employee;
    positionEmployee: PositionEmployee;
    department: Department;
    startDateInDepartment: Date;
    endDateInDepartment: Date;
    isCurrentDepartment: boolean;
  }