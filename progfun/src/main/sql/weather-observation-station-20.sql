--https://www.hackerrank.com/challenges/weather-observation-station-20.sql
select count(*) from station into @cnt;
select TRUNCATE(@cnt / 2,0) into @half_cnt;
select @cnt % 2 into @mod_cnt;
select
  ROUND(lat_n, 4)
from (
       select
         station.*,
         @rownum := @rownum + 1 as rank
       from
         station,
         (select @rownum := 0) r
       order by lat_n
     ) d
where
  (@mod_cnt = 1 AND rank = @half_cnt + 1)
  OR
  (@mod_cnt = 0 AND (rank = @half_cnt OR rank = @half_cnt + 1));