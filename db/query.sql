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

select *
from question
         inner join teach on question.cur_name = t.cur_name
where teach.te_name = ?;