-- Insert into options table

-- Insert into questions table
INSERT INTO questions (id, question_text, question_type, step) VALUES
                                                                   (1, 'How long have you been carrying this burden?', 'multiple_choice', 'self_discovery_1'),
                                                                   (2, 'Are you able to put this burden down sometimes?', 'multiple_choice', 'self_discovery_1'),
                                                                   (3, 'Do you often feel overwhelmed?', 'multiple_choice', 'self_discovery_1'),
                                                                   (4, 'Has this feeling affected your daily life?', 'multiple_choice', 'self_discovery_1');

INSERT INTO options (id, option_text, question_id) VALUES
                                                       (1, 'A week', 1), (2, 'Few weeks', 1), (3, 'Few months', 1),
                                                       (4, 'A year', 1), (5, 'More than a year', 1), (6, 'Rarely', 2),
                                                       (7, 'Sometimes', 2), (8, 'Often', 2), (9, 'Very often', 2),
                                                       (10, 'Never', 3), (11, 'Occasionally', 3), (12, 'Frequently', 3),
                                                       (13, 'Always', 3), (14, 'No', 4), (15, 'Slightly', 4),
                                                       (16, 'Moderately', 4), (17, 'Severely', 4);

-- Insert into reports table
INSERT INTO reports (id, question_id, report_text, selected_option) VALUES
                                                                        (1, 1, 'It seems this is a recent issue. Let’s explore it further.', 'A week'),
                                                                        (2, 1, 'You have been dealing with this for some time. Seeking guidance could help.', 'Few weeks'),
                                                                        (3, 1, 'This issue has been persistent. Understanding its impact is crucial.', 'Few months'),
                                                                        (4, 1, 'This has been affecting you for a long time. Professional help may be useful.', 'A year'),
                                                                        (5, 1, 'This has been a long-term concern. Consider seeking structured support.', 'More than a year');

