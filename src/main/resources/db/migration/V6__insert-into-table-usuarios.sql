DELETE FROM `foro-hub`.`usuarios`;

INSERT INTO `foro-hub`.`usuarios` (`id`, `nombre`, `correo`, `contrasena`, `rol`)
VALUES ('1', 'Paco administrador', 'paco.administrador@correo.com', '$2a$10$7X09iFWVJeuAhRxInquTN.ynf9KPv/jXkVxdVaSrod9iZd.57fGsu', 'ADMINISTRADOR');
INSERT INTO `foro-hub`.`usuarios` (`id`, `nombre`, `correo`, `contrasena`, `rol`)
VALUES ('2', 'Pedro moderador', 'pedro.moderador@correo.com', '$2a$10$7X09iFWVJeuAhRxInquTN.ynf9KPv/jXkVxdVaSrod9iZd.57fGsu', 'MODERADOR');
INSERT INTO `foro-hub`.`usuarios` (`id`, `nombre`, `correo`, `contrasena`, `rol`)
VALUES ('3', 'Mario usuario', 'mario.usuario@correo.com', '$2a$10$7X09iFWVJeuAhRxInquTN.ynf9KPv/jXkVxdVaSrod9iZd.57fGsu', 'USUARIO');
