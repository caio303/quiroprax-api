CREATE TABLE `usuario` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `login` VARCHAR(80) NOT NULL,
  `senha` VARCHAR(80) UNIQUE NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE `paciente` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `email` VARCHAR(80) UNIQUE NOT NULL,
  `telefone` VARCHAR(40) UNIQUE NOT NULL,
  `cpf` VARCHAR(11) UNIQUE NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE `horario_disponivel` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `data` VARCHAR(20) NOT NULL COMMENT 'formatação: ''25/04/2004''',
  `hora` VARCHAR(8) NOT NULL COMMENT 'formatação: ''17:30''',
  `status` INT NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE `agendamento` (
  `id` BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `paciente_id` BIGINT NOT NULL,
  `horario_disponivel_id` BIGINT NOT NULL,
  `status` INT NOT NULL
);

ALTER TABLE `agendamento` ADD FOREIGN KEY (`paciente_id`) REFERENCES `paciente` (`id`);

ALTER TABLE `agendamento` ADD FOREIGN KEY (`horario_disponivel_id`) REFERENCES `horario_disponivel` (`id`);
