-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-02-2025 a las 07:57:20
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `elorbase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciclos`
--

CREATE TABLE `ciclos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciclos`
--

INSERT INTO `ciclos` (`id`, `nombre`) VALUES
(0, 'CICLO'),
(1, 'DAM'),
(2, 'DAW'),
(3, 'OTROS'),
(4, 'ASIR'),
(5, 'SMR'),
(6, 'CICLO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarios`
--

CREATE TABLE `horarios` (
  `dia` enum('L/A','M/A','X','J/O','V/O') NOT NULL,
  `hora` enum('1','2','3','4','5','6') NOT NULL,
  `profe_id` int(11) NOT NULL,
  `modulo_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horarios`
--

INSERT INTO `horarios` (`dia`, `hora`, `profe_id`, `modulo_id`) VALUES
('L/A', '1', 1, 9),
('L/A', '1', 2, 9),
('L/A', '1', 4, 5),
('L/A', '1', 5, 6),
('L/A', '1', 6, 5),
('L/A', '1', 8, 6),
('L/A', '2', 1, 9),
('L/A', '2', 2, 9),
('L/A', '2', 4, 5),
('L/A', '2', 5, 6),
('L/A', '2', 6, 5),
('L/A', '2', 8, 6),
('L/A', '3', 1, 9),
('L/A', '3', 2, 9),
('L/A', '3', 4, 5),
('L/A', '3', 5, 2),
('L/A', '3', 6, 5),
('L/A', '3', 8, 2),
('L/A', '4', 4, 4),
('L/A', '4', 6, 4),
('L/A', '4', 7, 13),
('L/A', '4', 9, 13),
('L/A', '5', 4, 4),
('L/A', '5', 6, 4),
('L/A', '5', 7, 13),
('L/A', '5', 9, 13),
('M/A', '1', 1, 3),
('M/A', '1', 2, 3),
('M/A', '1', 4, 10),
('M/A', '1', 6, 10),
('M/A', '2', 1, 3),
('M/A', '2', 2, 3),
('M/A', '2', 4, 10),
('M/A', '2', 5, 1),
('M/A', '2', 6, 10),
('M/A', '2', 7, 1),
('M/A', '2', 8, 1),
('M/A', '2', 9, 1),
('M/A', '3', 1, 3),
('M/A', '3', 2, 3),
('M/A', '3', 4, 4),
('M/A', '3', 5, 2),
('M/A', '3', 6, 4),
('M/A', '3', 7, 13),
('M/A', '3', 8, 2),
('M/A', '3', 9, 13),
('M/A', '4', 1, 12),
('M/A', '4', 2, 12),
('M/A', '4', 4, 4),
('M/A', '4', 6, 4),
('M/A', '5', 1, 12),
('M/A', '5', 2, 12),
('M/A', '5', 4, 2),
('M/A', '5', 6, 2),
('X', '1', 4, 4),
('X', '1', 5, 8),
('X', '1', 6, 4),
('X', '1', 8, 8),
('X', '2', 1, 1),
('X', '2', 2, 1),
('X', '2', 4, 4),
('X', '2', 5, 8),
('X', '2', 6, 4),
('X', '2', 7, 2),
('X', '2', 8, 8),
('X', '2', 9, 2),
('X', '3', 1, 9),
('X', '3', 2, 9),
('X', '3', 4, 5),
('X', '3', 6, 5),
('X', '4', 1, 9),
('X', '4', 2, 9),
('X', '4', 4, 5),
('X', '4', 6, 5),
('X', '5', 1, 9),
('X', '5', 2, 9),
('X', '5', 4, 5),
('X', '5', 6, 5),
('J/O', '1', 5, 8),
('J/O', '1', 8, 8),
('J/O', '2', 1, 2),
('J/O', '2', 2, 2),
('J/O', '2', 4, 1),
('J/O', '2', 5, 8),
('J/O', '2', 6, 1),
('J/O', '2', 7, 2),
('J/O', '2', 8, 8),
('J/O', '2', 9, 2),
('J/O', '3', 1, 3),
('J/O', '3', 2, 3),
('J/O', '3', 4, 2),
('J/O', '3', 5, 8),
('J/O', '3', 6, 2),
('J/O', '3', 8, 8),
('J/O', '4', 1, 3),
('J/O', '4', 2, 3),
('J/O', '4', 4, 10),
('J/O', '4', 6, 10),
('J/O', '5', 1, 3),
('J/O', '5', 2, 3),
('J/O', '5', 4, 10),
('J/O', '5', 6, 10),
('V/O', '1', 5, 6),
('V/O', '1', 7, 11),
('V/O', '1', 8, 6),
('V/O', '1', 9, 11),
('V/O', '2', 5, 6),
('V/O', '2', 7, 11),
('V/O', '2', 8, 6),
('V/O', '2', 9, 11),
('V/O', '3', 1, 2),
('V/O', '3', 2, 2),
('V/O', '3', 4, 2),
('V/O', '3', 6, 2),
('V/O', '3', 7, 11),
('V/O', '3', 9, 11),
('V/O', '4', 1, 12),
('V/O', '4', 2, 12),
('V/O', '4', 4, 5),
('V/O', '4', 6, 5),
('V/O', '5', 1, 12),
('V/O', '5', 2, 12),
('V/O', '5', 4, 5),
('V/O', '5', 6, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculaciones`
--

CREATE TABLE `matriculaciones` (
  `alum_id` int(11) NOT NULL,
  `ciclo_id` int(11) NOT NULL,
  `curso` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matriculaciones`
--

INSERT INTO `matriculaciones` (`alum_id`, `ciclo_id`, `curso`, `fecha`) VALUES
(3, 1, 1, '2024-09-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulos`
--

CREATE TABLE `modulos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `nombre_eus` varchar(200) DEFAULT NULL,
  `horas` int(11) NOT NULL,
  `ciclo_id` int(11) DEFAULT NULL,
  `curso` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `modulos`
--

INSERT INTO `modulos` (`id`, `nombre`, `nombre_eus`, `horas`, `ciclo_id`, `curso`) VALUES
(1, 'Tutoria', 'Tutoretza', 1, 0, 0),
(2, 'Guardia', 'Zaintza', 1, 0, 0),
(3, 'Sistemas Informaticos', 'Informatika-sistemak', 165, 1, 1),
(4, 'Bases de datos', 'Datu-baseak', 198, 1, 1),
(5, 'Programación', 'Programazioa ', 264, 1, 1),
(6, 'Lenguajes de marcas', 'Markatzeko lengoaiak', 132, 1, 1),
(7, 'Entornos de desarrollo', 'Garapen-inguruneak', 99, 1, 1),
(8, 'Acceso a datos', 'Datu-atzipena', 120, 1, 2),
(9, 'Desarrollo de interfaces', 'Interfazeen garapena', 140, 1, 2),
(10, 'Programación multimedia y dispositivos móviles', 'Multimedia-programazioa eta gailu mugikorrak', 100, 1, 2),
(11, 'Programación de servicios y procesos', 'Zerbitzu eta prozesuen programazioa', 80, 1, 2),
(12, 'Sistemas de gestión empresarial', 'Enpresa-kudeaketako sistemak', 100, 1, 2),
(13, 'Empresa e Iniciativa Emprendedora', 'Enpresa eta ekimen sortzailea ', 60, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reuniones`
--

CREATE TABLE `reuniones` (
  `id_reunion` int(11) NOT NULL,
  `estado` enum('pendiente','aceptada','denegada','conflicto') DEFAULT NULL,
  `estado_eus` enum('onartzeke','onartuta','ezeztatuta','gatazka') DEFAULT NULL,
  `profesor_id` int(11) NOT NULL,
  `alumno_id` int(11) NOT NULL,
  `id_centro` varchar(6) DEFAULT '15112',
  `titulo` varchar(150) DEFAULT NULL,
  `asunto` varchar(200) DEFAULT NULL,
  `aula` varchar(10) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reuniones`
--

INSERT INTO `reuniones` (`id_reunion`, `estado`, `estado_eus`, `profesor_id`, `alumno_id`, `id_centro`, `titulo`, `asunto`, `aula`, `fecha`) VALUES
(1, 'pendiente', NULL, 1, 3, '15112', 'Seguimiento reto', 'Sprint 3', '5.105', '2025-01-13 11:30:00'),
(2, 'conflicto', NULL, 5, 3, '15112', 'prueba conflicto', 'Prueba', '5.105', '2025-02-13 07:53:38'),
(3, 'pendiente', NULL, 5, 3, '15112', 'prueba aceptado', 'Sprint 3', '5.105', '2025-01-14 11:30:00'),
(12, 'denegada', NULL, 5, 3, '15112', 'prueba', 'Prueba', '5.105', '2025-02-14 07:53:38'),
(13, 'conflicto', NULL, 5, 3, '15112', 'prueba conflicto', 'Prueba', '5.105', '2025-02-13 07:53:38'),
(15, 'conflicto', NULL, 6, 3, '15112', 'prueba asier', 'Prueba', '5.105', '2025-02-13 07:53:38'),
(17, 'aceptada', NULL, 6, 3, '15112', 'prueba', 'Prueba', '5.105', '2025-02-11 07:53:38'),
(20, 'aceptada', NULL, 5, 3, '15112', 'Seguimiento reto', 'Sprint 3', '5.105', '2025-01-14 11:30:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE `tipos` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `name_eus` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`id`, `name`, `name_eus`) VALUES
(1, 'god', 'jainkoa'),
(2, 'administrador', 'administratzailea'),
(3, 'profesor', 'irakaslea'),
(4, 'alumno', 'ikaslea');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono1` int(11) DEFAULT NULL,
  `telefono2` int(11) DEFAULT NULL,
  `tipo_id` int(11) NOT NULL,
  `argazkia` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `email`, `username`, `password`, `nombre`, `apellidos`, `dni`, `direccion`, `telefono1`, `telefono2`, `tipo_id`, `argazkia`) VALUES
(1, 'jorge@elorrieta-errekamari.com', 'jorge', '123456', 'jorge', 'gonzalez', '123456789', 'example 10', 666, NULL, 1, NULL),
(2, 'itziar@elorrieta-errekamari.com', 'itziar', '123456', 'itziar', 'regidor', '123456', 'example 10', 666, NULL, 2, NULL),
(3, 'alumno@elorrieta-errekamari.com', 'alumno1', '123', 'alumno', 'ejemplo', 'as', 'as', 666, NULL, 4, NULL),
(4, 'maitane@elorrieta-errekamari.com', 'maitane', '1234', 'maitane', 'ejemplo', '1234', 'as', NULL, NULL, 3, NULL),
(5, 'iker@elorrieta-errekamari.com', 'iker', '1234', 'iker', 'ejemplo', '1234', 'as', NULL, NULL, 3, NULL),
(6, 'asier@elorrieta-errekamari.com', 'asier', '1234', 'asier', 'ejemplo', '1234', 'as', NULL, NULL, 3, NULL),
(7, 'unai@elorrieta-errekamari.com', 'unai', '1234', 'unai', 'ejemplo', '1234', 'as', NULL, NULL, 3, NULL),
(8, 'roman@elorrieta-errekamari.com', 'roman', '1234', 'roman', 'ejemplo', '1234', 'as', NULL, NULL, 3, NULL),
(9, 'oscar@elorrieta-errekamari.com', 'oscar', '1234', 'oscar', 'ejemplo', '1234', 'as', NULL, NULL, 3, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciclos`
--
ALTER TABLE `ciclos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD PRIMARY KEY (`dia`,`hora`,`profe_id`,`modulo_id`),
  ADD KEY `profe_id` (`profe_id`),
  ADD KEY `modulo_id` (`modulo_id`);

--
-- Indices de la tabla `matriculaciones`
--
ALTER TABLE `matriculaciones`
  ADD PRIMARY KEY (`alum_id`,`ciclo_id`,`curso`,`fecha`),
  ADD KEY `ciclo_id` (`ciclo_id`);

--
-- Indices de la tabla `modulos`
--
ALTER TABLE `modulos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ciclo_id` (`ciclo_id`);

--
-- Indices de la tabla `reuniones`
--
ALTER TABLE `reuniones`
  ADD PRIMARY KEY (`id_reunion`),
  ADD KEY `profesor_id` (`profesor_id`),
  ADD KEY `alumno_id` (`alumno_id`);

--
-- Indices de la tabla `tipos`
--
ALTER TABLE `tipos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tipo_id` (`tipo_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `reuniones`
--
ALTER TABLE `reuniones`
  MODIFY `id_reunion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `horarios`
--
ALTER TABLE `horarios`
  ADD CONSTRAINT `horarios_ibfk_1` FOREIGN KEY (`profe_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `horarios_ibfk_2` FOREIGN KEY (`modulo_id`) REFERENCES `modulos` (`id`);

--
-- Filtros para la tabla `matriculaciones`
--
ALTER TABLE `matriculaciones`
  ADD CONSTRAINT `matriculaciones_ibfk_1` FOREIGN KEY (`alum_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `matriculaciones_ibfk_2` FOREIGN KEY (`ciclo_id`) REFERENCES `ciclos` (`id`);

--
-- Filtros para la tabla `modulos`
--
ALTER TABLE `modulos`
  ADD CONSTRAINT `modulos_ibfk_1` FOREIGN KEY (`ciclo_id`) REFERENCES `ciclos` (`id`);

--
-- Filtros para la tabla `reuniones`
--
ALTER TABLE `reuniones`
  ADD CONSTRAINT `reuniones_ibfk_1` FOREIGN KEY (`profesor_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `reuniones_ibfk_2` FOREIGN KEY (`alumno_id`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`tipo_id`) REFERENCES `tipos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
