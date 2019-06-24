insert into employee_schema.position (id, name, is_active) values (1, 'Java developer', true) on conflict do nothing;
insert into employee_schema.position (id, name, is_active) values (2, 'C++ developer', true) on conflict do nothing;
insert into employee_schema.position (id, name, is_active) values (3, 'Vue.js developer', true) on conflict do nothing;

insert into employee_schema.personal_info (id, first_name, last_name, adress, email, skype, phone_number, description)
values (1, 'pavel', 'marchuk', 'adress', 'email', 'skype', '+544484984897', 'description') on conflict do nothing;

insert into employee_schema.employee (id, personal_info_id, is_active) values (1, 1, true) on conflict do nothing;

insert into employee_schema.department (id, name, manager_id) values (1, 'Development', 1) on conflict do nothing;
insert into employee_schema.department (id, name, manager_id) values (2, 'Testing', 1) on conflict do nothing;

insert into employee_schema.position_employee (id, employee_id, position_id, start_date_for_position)
values (1, 1, 2, '2019-05-01') on conflict do nothing;

insert into employee_schema.department_employee (id, employee_id, department_id, start_date_in_department, is_current_department)
values (1, 1, 1, '2019-05-01', true) on conflict do nothing;

insert into employee_schema.hiring_employee_info (id, employee_id, date_of_hiring)
values (1, 1, '2019-05-01') on conflict do nothing;