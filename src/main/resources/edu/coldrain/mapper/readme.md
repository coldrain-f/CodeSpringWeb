ql

-- 데이터가 섞여있다.
SELECT TBL_BOARD.* FROM TBL_BOARD;

-- 섞여있는 상태로 ROWNUM을 주면? ( 섞인 상태로 부여된다. )
SELECT ROWNUM RN, TBL_BOARD.* FROM TBL_BOARD;

-- ORDER BY로 내림차순 정렬 
SELECT TBL_BOARD.* FROM TBL_BOARD ORDER BY BNO DESC;

-- ORDER BY로 내림차순 정렬하고 ROWNUM을 주면? ( BNO가 PK이므로 ORDER BY이지만 INDEX_DESC를 수행한다. )
SELECT ROWNUM RN, TBL_BOARD.* FROM TBL_BOARD ORDER BY BNO DESC;

-- INDEX_DESC로 내림차순 조회
SELECT /*+ INDEX_DESC(TBL_BOARD PK_BOARD)*/TBL_BOARD.* FROM TBL_BOARD;

-- INDEX DESC로 내림차순 조회하고 ROWNUM 부여 ( 정상적으로 ROWNUM이 부여된다. )
SELECT /*+ INDEX_DESC(TBL_BOARD PK_BOARD)*/ROWNUM RN, TBL_BOARD.* FROM TBL_BOARD;

-- 1페이지 정보를 보려면? 
SELECT /*+ INDEX_DESC(TBL_BOARD PK_BOARD)*/ROWNUM RN, TBL_BOARD.* FROM TBL_BOARD
WHERE ROWNUM > 0 AND ROWNUM <= 10;

-- 2페이지 정보를 보려면? ( ROWNUM은 1을 포함해야 하므로 아무것도 조회되지 않는다. 필터에 걸린다. )
SELECT /*+ INDEX_DESC(TBL_BOARD PK_BOARD)*/ROWNUM RN, TBL_BOARD.* FROM TBL_BOARD
WHERE ROWNUM > 10 AND ROWNUM <= 20;

-- 그렇다면 2페이지 정보를 보려면 어떻게? ( 인라인뷰 사용 )
SELECT N.* FROM (
SELECT /*+ INDEX_DESC(TBL_BOARD PK_BOARD)*/ROWNUM RN, TBL_BOARD.* FROM TBL_BOARD
WHERE ROWNUM <= 20
) N
WHERE RN > 10;

-- 사용자가 3페이지를 조회하고 싶고 10개씩 보고싶다면?
SELECT N.* FROM (
SELECT /*+ INDEX_DESC(TBL_BOARD PK_BOARD)*/ROWNUM RN, TBL_BOARD.* FROM TBL_BOARD
WHERE ROWNUM <= 3 * 10 -- pageNum * amount
) N
WHERE RN > (3 - 1) * 10; -- (pageNum - 1) * amount
 ```