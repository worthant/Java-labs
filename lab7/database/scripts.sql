-- How to get all City collections by user_id:
SELECT City.* 
FROM City c
JOIN Creator cr ON c.id = cr.city_id
WHERE cr.user_id = <USER_ID>;