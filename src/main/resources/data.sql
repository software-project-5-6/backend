-- Insert role records
INSERT INTO roles (id, role_name, description) VALUES
    (RANDOM_UUID(), 'ADMIN', 'Has full control over the project and users.'),
    (RANDOM_UUID(), 'PROJECT_MANAGER', 'Responsible for managing project progress and team tasks.'),
    (RANDOM_UUID(), 'BUSINESS_ANALYST', 'Analyzes requirements and ensures business needs are met.'),
    (RANDOM_UUID(), 'DEVELOPER', 'Develops and maintains the project codebase.');
