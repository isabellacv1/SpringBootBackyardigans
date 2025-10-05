-- Tabla: permissions
INSERT INTO permissions (name, description) VALUES
('READ_PROFILE', 'Permiso para leer perfiles de usuario'),
('WRITE_PROFILE', 'Permiso para editar perfiles de usuario'),
('ADMIN_ACCESS', 'Acceso completo a todas las funcionalidades de administrador'),
('MANAGE_ROUTINES', 'Permiso para crear, editar y eliminar rutinas'),
('VIEW_REPORTS', 'Permiso para ver reportes de progreso');

-- Tabla: roles
INSERT INTO roles (name, description) VALUES
('ADMIN', 'Rol de Administrador con todos los permisos'),
('TRAINER', 'Rol para entrenadores'),
('USER', 'Rol para usuarios estándar de la aplicación');

-- Tabla: role_permission
-- Rol ADMIN (1) tiene todos los permisos (1, 2, 3, 4, 5)
INSERT INTO role_permissions (role_id, permission_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
-- Rol TRAINER (2) tiene permisos para gestionar perfiles y rutinas (1, 2, 4)
(2, 1),
(2, 2),
(2, 4),
-- Rol USER (3) solo tiene permiso para leer perfiles (1)
(3, 1);

-- Tabla: users
INSERT INTO users (name, email, password) VALUES
('Admin User', 'admin@fitness.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('Trainer Uno', 'trainer.one@fitness.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('Trainer Dos', 'trainer.two@fitness.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('John Doe', 'john.doe@example.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('Jane Smith', 'jane.smith@example.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('Peter Jones', 'peter.jones@example.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('Mary Williams', 'mary.williams@example.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('David Brown', 'david.brown@example.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('Susan Davis', 'susan.davis@example.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW'),
('Robert Miller', 'robert.miller@example.com', '$2b$10$5ysgXZUJi7MkJWhEhFcZTObGe18G1G.0rnXkewEtXq6ebVx1qpjYW');

-- Tabla: users_roles
-- Admin User (1) es ADMIN (1) y TRAINER (2)
INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),
(1, 2),
-- Trainers (2, 3) son TRAINER (2)
(2, 2),
(3, 2),
-- El resto de usuarios (4-10) son USER (3)
(4, 3),
(5, 3),
(6, 3),
(7, 3),
(8, 3),
(9, 3),
(10, 3);

-- Tablas de catálogo
INSERT INTO difficulties (name) VALUES ('Beginner'), ('Intermediate'), ('Advanced');
INSERT INTO efforts (name) VALUES ('Low'), ('Medium'), ('High');
INSERT INTO event_status (name) VALUES ('Scheduled'), ('Completed'), ('Cancelled');
INSERT INTO exercise_types (name) VALUES ('Cardio'), ('Strength'), ('Flexibility');
INSERT INTO notification_types (name) VALUES ('System Alert'), ('New Message'), ('Workout Reminder');
INSERT INTO routine_visibilities (name) VALUES ('Public'), ('Private'), ('FriendsOnly');
INSERT INTO units (name) VALUES ('Repetitions'), ('Seconds'), ('Minutes'), ('Kilograms');
INSERT INTO weeks (name) VALUES ('Monday'), ('Tuesday'), ('Wednesday'), ('Thursday'), ('Friday'), ('Saturday'), ('Sunday');

-- Tabla: exercises
INSERT INTO exercises (name, description, duration, exercise_type_id, difficulty_id) VALUES
('Push-ups', 'A classic bodyweight exercise for chest, shoulders, and triceps.', '30 seconds', 2, 2),
('Squats', 'A fundamental lower body exercise.', '45 seconds', 2, 1),
('Jumping Jacks', 'A full-body cardio exercise.', '60 seconds', 1, 1),
('Plank', 'Core stability exercise.', '60 seconds', 2, 2),
('Hamstring Stretch', 'A key stretch for lower body flexibility.', '30 seconds per leg', 3, 1);

-- Tabla: routines
INSERT INTO routines (date) VALUES
('2025-09-15'),
('2025-09-16');

-- Tabla: users_routines (Asignar rutinas a usuarios)
-- Trainer Uno (2) crea la rutina 1 (pública)
INSERT INTO users_routines (user_id, routine_id, routine_visibility_id) VALUES (2, 1, 1);
-- John Doe (4) tiene la rutina 2 (privada)
INSERT INTO users_routines (user_id, routine_id, routine_visibility_id) VALUES (4, 2, 2);

-- Tabla: exercises_routines (Componer rutinas con ejercicios)
-- Rutina 1: Push-ups y Squats
INSERT INTO exercises_routines (routine_id, exercise_id, unit_id) VALUES
(1, 1, 1), -- Push-ups, Reps
(1, 2, 1); -- Squats, Reps
-- Rutina 2: Jumping Jacks y Plank
INSERT INTO exercises_routines (routine_id, exercise_id, unit_id) VALUES
(2, 3, 2), -- Jumping Jacks, Seconds
(2, 4, 2); -- Plank, Seconds

-- Tabla: progress
-- John Doe (4) registra progreso en Push-ups (ejercicio 1) de la rutina 1
INSERT INTO progresses (repetitions, time, date, exercise_routine_routine_id, exercise_routine_exercise_id, effort_id) VALUES
(15, 0, '2025-09-15', 1, 1, 2); -- 15 reps, Medium effort

-- Tabla: trainer_students
-- Trainer Uno (2) entrena a John Doe (4) y Jane Smith (5)
INSERT INTO trainer_students (trainer_id, student_id) VALUES
(2, 4),
(2, 5);

-- Tabla: messages
INSERT INTO messages (title, body, date, is_read) VALUES
('Welcome!', 'Welcome to the platform, John!', '2025-09-14 10:00:00', false);

-- Tabla: user_messages
-- Mensaje de Admin (1) para John Doe (4)
INSERT INTO user_messages (user_id, message_id) VALUES
(1, 1),
(4, 1);

-- Tabla: notifications
INSERT INTO notifications (title, body, date, is_read, notification_type_id) VALUES
('New Routine Assigned', 'Trainer Uno has assigned you a new routine.', '2025-09-15 09:00:00', false, 3);

-- Tabla: user_notifications
-- Notificación para John Doe (4)
INSERT INTO user_notifications (user_id, notification_id) VALUES
(4, 1);

-- Tabla: schedule
INSERT INTO schedules (start_hour, end_hour, week_id) VALUES
('2025-09-15 18:00:00', '2025-09-15 19:00:00', 1); -- Lunes 6-7 PM

-- Tabla: events
INSERT INTO events (start_date, end_date, place, type, place_capacity, schedule_id, event_status_id) VALUES
('2025-09-22 18:00:00', '2025-09-22 19:00:00', 'Main Gym', 'Group Cardio', '25', 1, 1);

-- Tabla: event_participation
-- John Doe (4) y Jane Smith (5) participan en el evento 1
INSERT INTO event_participations (student_id, event_id) VALUES
(4, 1),
(5, 1);