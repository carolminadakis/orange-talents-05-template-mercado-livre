
INSERT INTO USUARIO(email,senha,horario_criacao) VALUES ('ana@gmail.com','$2y$12$jvryY9aUBk7bqlJ0/Hiof.Eng1qcbV.HpnllUPULanokRW/V9JMXK','2019-01-21T05:47:08.644');
INSERT INTO USUARIO(email,senha,horario_criacao) VALUES ('leandro@gmail.com','$2y$12$jvryY9aUBk7bqlJ0/Hiof.Eng1qcbV.HpnllUPULanokRW/V9JMXK','2019-01-21T05:47:08.644');

INSERT INTO CATEGORIA(nome) VALUES ('Eletrônicos');
INSERT INTO CATEGORIA(nome, categoria_mae_id) VALUES ('TV ',1);
INSERT INTO CATEGORIA(nome, categoria_mae_id) VALUES ('Celulares',1);
INSERT INTO CATEGORIA(nome, categoria_mae_id) VALUES ('Computadores',1);