CREATE TABLE employee_exam (
    rowid BIGINT PRIMARY KEY AUTO_INCREMENT,
    accomplished_at DATE NOT NULL,
    employee_id BIGINT NOT NULL ,
    exam_id BIGINT NOT NULL,
    FOREIGN KEY (employee_id) references employee(rowid),
    FOREIGN KEY (exam_id) references exam(rowid)
);

