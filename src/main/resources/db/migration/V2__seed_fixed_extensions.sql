INSERT INTO fixed_extension (extension, blocked) VALUES
    ('exe', 0),
    ('sh', 0),
    ('bat', 0),
    ('cmd', 0),
    ('com', 0),
    ('cpl', 0),
    ('scr', 0),
    ('js', 0),
    ('jar', 0)
    ON DUPLICATE KEY UPDATE blocked = VALUES(blocked);
