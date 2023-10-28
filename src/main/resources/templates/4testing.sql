SELECT * 
FROM students 
INNER JOIN absen 
ON students.id = absen.student_id;

SELECT * 
FROM students 
LEFT JOIN absen 
ON students.id = absen.student_id;

SELECT * 
FROM students 
INNER JOIN absen 
ON students.id = absen.student_id
ORDER BY students.id DESC
LIMIT 10; 

SELECT * 
FROM students 
LEFT JOIN absen 
ON students.id = absen.student_id
ORDER BY students.id DESC
LIMIT 10;

SELECT * 
FROM students
CROSS JOIN absen
ON students.id = absen.student_id
ORDER BY students.id DESC
LIMIT 10 ;