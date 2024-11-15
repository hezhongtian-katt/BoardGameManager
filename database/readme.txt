在 PostgreSQL 命令行客?端（如 psql）中?行?个文件：
psql -U postgres -f create_board_game_db.sql
加数据
\COPY board_game(id, name, type, description)
FROM 'C:\xxx\board_game_data.csv'
DELIMITER ',' CSV HEADER;
??
SELECT * FROM board_game;
