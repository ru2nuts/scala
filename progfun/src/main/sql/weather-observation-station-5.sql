--https://www.hackerrank.com/challenges/weather-observation-station-5/problem
SELECT
  MIN(city),
  ln
FROM
  (
    SELECT
      city,
      LENGTH(city) AS ln
    FROM station) agg
WHERE
  agg.ln = (SELECT MAX(length(CITY))
            FROM station) OR
  agg.ln = (SELECT MIN(length(CITY))
            FROM station)
GROUP BY ln;
