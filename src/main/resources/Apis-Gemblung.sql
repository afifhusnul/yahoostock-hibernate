DROP TABLE IF EXISTS BAJUL;
CREATE TABLE BAJUL AS (
SELECT dt_trx, @curRow := @curRow + 1 AS row_number
FROM stock_trx
JOIN    (SELECT @curRow := 0) r
WHERE
(dt_trx BETWEEN (SELECT DATE_SUB(MAX(dt_trx), INTERVAL 69 DAY) AS DT FROM stock_trx) and (SELECT MAX(dt_trx) FROM stock_trx)  ) AND
DAYOFWEEK(dt_trx) NOT IN (1,7)
group by 1
);

SELECT A.ID_TICKLER, NM_TICKLER, A.DT_TRX, MA1 AS 'LAST_PRICE-MA1', MA5,MA10,MA20,MA50, Vol
FROM (
SELECT A.ID_TICKLER, NM_TICKLER, DT_TRX, OPEN_PRC, CLOSE_PRC AS MA1, FORMAT(vol_trx,0) AS Vol FROM stock_trx A
JOIN stock_master b ON a.id_tickler = b.id_tickler
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 50)
/*and close_prc > open_prc*/
) A
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA5 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 46)
/*and close_prc > open_prc*/
) B
ON A.id_tickler = b.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA10 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 41)
/*and close_prc > open_prc*/
) C
ON A.id_tickler = C.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA20 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 21)
/*and close_prc > open_prc*/
) D
ON A.id_tickler = D.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA50 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 1)
/*and close_prc > open_prc*/
) E
ON A.id_tickler = E.id_tickler
WHERE
MA1 > MA5
AND MA5 < MA10
AND MA10 < MA20
AND MA20 < MA50