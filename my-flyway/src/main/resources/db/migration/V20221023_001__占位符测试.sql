-- 测试占位符通过创建视图
create or replace view $${biz_view_name} as
  select id as v_id, name as v_name from $${biz_table_name};
