with T as
         (
             select *
             from question
                      inner join curriculum c on question.cur_name = c.cur_name
         )
select *
from T
         inner join teach on T.cur_name = teach.cur_name
where te_name = ?;