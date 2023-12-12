CREATE TABLE exame_funcionario (
    rowid bigint primary key auto_increment,
    realizado_em datetime not null,
    exame_id bigint not null,
    funcionario_id bigint not null ,
    foreign key (exame_id) references exame(rowid),
    foreign key (funcionario_id) references funcionario(rowid)
);

INSERT INTO exame_funcionario (realizado_em, exame_id, funcionario_id)
VALUES ('2023-12-08', 1, 1),
       ('2023-12-07', 1, 2),
       ('2023-12-09', 1, 3),
       ('2023-12-10', 1, 4),
       ('2023-12-08', 2, 1),
       ('2023-12-07', 2, 2),
       ('2023-12-09', 2, 3),
       ('2023-12-10', 2, 4),
       ('2023-12-08', 3, 1),
       ('2023-12-07', 3, 2),
       ('2023-12-09', 4, 3),
       ('2023-12-10', 4, 4);