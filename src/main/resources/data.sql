-- Tabla: permissions
INSERT INTO permissions (id, name, description) VALUES
(1, 'READ_PROFILE', 'Permiso para leer perfiles de usuario'),
(2, 'WRITE_PROFILE', 'Permiso para editar perfiles de usuario'),
(3, 'ADMIN_ACCESS', 'Acceso completo a todas las funcionalidades de administrador'),
(4, 'MANAGE_ROUTINES', 'Permiso para crear, editar y eliminar rutinas'),
(5, 'VIEW_REPORTS', 'Permiso para ver reportes de progreso');

-- Tabla: role
INSERT INTO role (id, name, description) VALUES
(1, 'ADMIN', 'Rol de Administrador con todos los permisos'),
(2, 'TRAINER', 'Rol para entrenadores'),
(3, 'USER', 'Rol para usuarios estándar de la aplicación');

-- Tabla: role_permission
-- Rol ADMIN (1) tiene todos los permisos (1, 2, 3, 4, 5)
INSERT INTO role_permission (role_id, permission_id) VALUES
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
INSERT INTO users (id, name, email, password) VALUES
(1, 'Admin User', 'admin@fitness.com', '{noop}adminpass'),
(2, 'Trainer Uno', 'trainer.one@fitness.com', '{noop}trainerpass'),
(3, 'Trainer Dos', 'trainer.two@fitness.com', '{noop}trainerpass'),
(4, 'John Doe', 'john.doe@example.com', '{noop}userpass'),
(5, 'Jane Smith', 'jane.smith@example.com', '{noop}userpass'),
(6, 'Peter Jones', 'peter.jones@example.com', '{noop}userpass'),
(7, 'Mary Williams', 'mary.williams@example.com', '{noop}userpass'),
(8, 'David Brown', 'david.brown@example.com', '{noop}userpass'),
(9, 'Susan Davis', 'susan.davis@example.com', '{noop}userpass'),
(10, 'Robert Miller', 'robert.miller@example.com', '{noop}userpass');

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
INSERT INTO difficulty (id, name) VALUES (1, 'Beginner'), (2, 'Intermediate'), (3, 'Advanced');
INSERT INTO effort (id, name) VALUES (1, 'Low'), (2, 'Medium'), (3, 'High');
INSERT INTO event_status (id, name) VALUES (1, 'Scheduled'), (2, 'Completed'), (3, 'Cancelled');
INSERT INTO exercise_type (id, name) VALUES (1, 'Cardio'), (2, 'Strength'), (3, 'Flexibility');
INSERT INTO notification_type (id, name) VALUES (1, 'System Alert'), (2, 'New Message'), (3, 'Workout Reminder');
INSERT INTO routine_visibility (id, name) VALUES (1, 'Public'), (2, 'Private'), (3, 'FriendsOnly');
INSERT INTO unit (id, name) VALUES (1, 'Repetitions'), (2, 'Seconds'), (3, 'Minutes'), (4, 'Kilograms');
INSERT INTO week (id, name) VALUES (1, 'Monday'), (2, 'Tuesday'), (3, 'Wednesday'), (4, 'Thursday'), (5, 'Friday'), (6, 'Saturday'), (7, 'Sunday');

-- Tabla: exercises
INSERT INTO exercises (id, name, description, duration, difficulty, type, exercise_type_id, difficulty_id) VALUES
(1, 'Push-ups', 'A classic bodyweight exercise for chest, shoulders, and triceps.', '30 seconds', 'Intermediate', 'Strength', 2, 2),
(2, 'Squats', 'A fundamental lower body exercise.', '45 seconds', 'Beginner', 'Strength', 2, 1),
(3, 'Jumping Jacks', 'A full-body cardio exercise.', '60 seconds', 'Beginner', 'Cardio', 1, 1),
(4, 'Plank', 'Core stability exercise.', '60 seconds', 'Intermediate', 'Strength', 2, 2),
(5, 'Hamstring Stretch', 'A key stretch for lower body flexibility.', '30 seconds per leg', 'Beginner', 'Flexibility', 3, 1);

-- Tabla: routines
INSERT INTO routines (id, date) VALUES
(1, '2025-09-15'),
(2, '2025-09-16');

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
INSERT INTO progress (id, repetitions, time, date, exercise_routine_routine_id, exercise_routine_exercise_id, effort_id) VALUES
(1, 15, 0, '2025-09-15', 1, 1, 2); -- 15 reps, Medium effort

-- Tabla: trainer_students
-- Trainer Uno (2) entrena a John Doe (4) y Jane Smith (5)
INSERT INTO trainer_students (trainer_id, student_id) VALUES
(2, 4),
(2, 5);

-- Tabla: messages
INSERT INTO messages (id, title, body, date, is_read) VALUES
(1, 'Welcome!', 'Welcome to the platform, John!', '2025-09-14 10:00:00', false);

-- Tabla: user_messages
-- Mensaje de Admin (1) para John Doe (4)
INSERT INTO user_messages (user_id, message_id) VALUES
(1, 1),
(4, 1);

-- Tabla: notifications
INSERT INTO notifications (id, title, body, date, is_read, notification_type_id) VALUES
(1, 'New Routine Assigned', 'Trainer Uno has assigned you a new routine.', '2025-09-15 09:00:00', false, 3);

-- Tabla: user_notifications
-- Notificación para John Doe (4)
INSERT INTO user_notifications (user_id, notification_id) VALUES
(4, 1);

-- Tabla: schedule
INSERT INTO schedule (id, start_hour, end_hour, week_id) VALUES
(1, '2025-09-15 18:00:00', '2025-09-15 19:00:00', 1); -- Lunes 6-7 PM

-- Tabla: events
INSERT INTO events (id, start_date, end_date, place, type, place_capacity, schedule_id, event_status_id) VALUES
(1, '2025-09-22 18:00:00', '2025-09-22 19:00:00', 'Main Gym', 'Group Cardio', '25', 1, 1);

-- Tabla: event_participation
-- John Doe (4) y Jane Smith (5) participan en el evento 1
INSERT INTO event_participation (student_id, event_id) VALUES
(4, 1),
(5, 1);