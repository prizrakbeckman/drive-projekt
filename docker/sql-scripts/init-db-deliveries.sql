-- Create Timeslots table
CREATE TABLE IF NOT EXISTS Timeslots (
                                         id SERIAL PRIMARY KEY,
                                         slot_time TIMESTAMP NOT NULL,
                                         is_booked BOOLEAN DEFAULT FALSE
);

-- Create Deliveries table
CREATE TABLE IF NOT EXISTS Deliveries (
                                          id SERIAL PRIMARY KEY,
                                          timeslot_id INTEGER REFERENCES Timeslots(id),
    delivery_address TEXT NOT NULL,
    status TEXT NOT NULL
    );

-- Insert sample data into Timeslots
INSERT INTO Timeslots (slot_time, is_booked) VALUES
                                                 ('2024-07-16 09:00:00', FALSE),
                                                 ('2024-07-16 10:00:00', FALSE),
                                                 ('2024-07-16 11:00:00', FALSE),
                                                 ('2024-07-16 12:00:00', FALSE),
                                                 ('2024-07-16 13:00:00', FALSE),
                                                 ('2024-07-16 14:00:00', FALSE),
                                                 ('2024-07-16 15:00:00', FALSE),
                                                 ('2024-07-16 16:00:00', FALSE),
                                                 ('2024-07-16 17:00:00', FALSE),
                                                 ('2024-07-16 18:00:00', FALSE);