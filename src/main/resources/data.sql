INSERT INTO television (id, type, brand, name, price, available_size, refresh_Rate, screen_Type, screen_quality, smart_Tv, wifi, voice_Control, hdr, bluetooth, ambi_Light, original_Stock ,sold)
VALUES
    (2001,'Fancy', 'Sony', 'XH4K', 1100, 43, 144, 'IPS', '4k', true, true, false, true, false, false, 10, 8),
    (2002,'Super Fancy', 'Philips', 'XH6K', 10400, 55, 60, 'OLED', '9k', true, true, false, true, false, false, 10, 8),
    (2003,'Eh', 'Sony', 'Bravia', 100, 34, 30, 'VA', '720p', false, false, false, false, false, false, 100, 80),
    (2004,'Is okay', 'LG', 'Choc', 500, 52, 240, 'IPSQ', '1080p', true, true, false, true, false, false, 5, 3);

INSERT INTO remote_control (id, compatible_With, battery_Type, name, brand, price, original_Stock)
VALUES
    (3001, 'XH4K', 'AA', 'XH4KR', 'Sony', 10, 8),
    (3002, 'XH6K', 'AAA', 'XH6KR', 'Philips', 10, 8),
    (3003, 'Bravia', 'AA', 'BraviaR', 'Sony', 10, 8),
    (3004, 'Choc', 'AAA', 'ChocR', 'LG', 10, 8);

INSERT INTO cimodule (id, name, type, price)
VALUES
    (4001, 'CiPro', 'Premium', 110),
    (4002, 'CiMid', 'Mid-spec', 80),
    (4003, 'CiBase', 'Entry', 30);

INSERT INTO wall_bracket (id, size, adjustable, name, price)
VALUES
    (5001, '40 inch', true, 'WallPro', 300),
    (5002, '30 inch', false, 'WallBase', 100),
    (5003, '55 inch', true, 'WallPremium', 500);


