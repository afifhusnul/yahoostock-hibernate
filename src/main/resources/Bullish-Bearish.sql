/**
 * Author:  NUSNAFIF
 * Created: Mar 6, 2016
 */

/*------------------- Strong Uptrend -------------------------
* Always Up for Last 5 Days
*/
/*------------------- Strong Uptrend -------------------------
* Always Up (CLOSE> OPEN) for Last 5 Days
*/
SET @row_number = 0;
DROP TABLE IF EXISTS BAJUL;
CREATE TABLE BAJUL AS (
SELECT dt_trx , (@row_number:=@row_number + 1) AS row_number FROM stock_trx
group by 1
order by 1 desc
LIMIT 200 
);


SELECT A.ID_TICKLER, NM_TICKLER, A.DT_TRX, MA1 AS 'LAST_PRICE-MA1',MA2,MA3,MA4,MA5
FROM (
SELECT A.ID_TICKLER, NM_TICKLER, DT_TRX, OPEN_PRC, CLOSE_PRC AS MA1 FROM stock_trx A
JOIN stock_master b ON a.id_tickler = b.id_tickler
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 1)
and close_prc > open_prc
) A
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA2 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 2)
and close_prc > open_prc
) B
ON A.id_tickler = b.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA3 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 3)
and close_prc > open_prc
) C
ON A.id_tickler = C.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA4 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 4)
and close_prc > open_prc
) D
ON A.id_tickler = D.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA5 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 5)
and close_prc > open_prc
) E
ON A.id_tickler = E.id_tickler


/*------------------- Bearish reverseal -------------------------
* Last price > ma1 <ma2 < ma3 < ma4 < ma5
*/
SET @row_number = 0;
DROP TABLE IF EXISTS BAJUL;
CREATE TABLE BAJUL AS (
SELECT dt_trx , (@row_number:=@row_number + 1) AS row_number FROM stock_trx
group by 1
order by 1 desc
LIMIT 200 );


SELECT A.ID_TICKLER, NM_TICKLER, A.DT_TRX, MA1 AS 'LAST_PRICE-MA1', MA2,MA3,MA4,MA5,VOL
FROM (
SELECT A.ID_TICKLER, NM_TICKLER, DT_TRX, OPEN_PRC, CLOSE_PRC AS MA1, VOL_TRX AS VOL FROM stock_trx A
JOIN stock_master b ON a.id_tickler = b.id_tickler
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 1)
and close_prc > open_prc
) A
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA2 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 2)
) B
ON A.id_tickler = b.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA3 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 3)
) C
ON A.id_tickler = C.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA4 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 4)
) D
ON A.id_tickler = D.id_tickler
JOIN (
SELECT id_tickler, dt_trx, open_prc,close_prc as MA5 FROM stock_trx 
WHERE dt_trx = (SELECT DT_TRX FROM BAJUL WHERE row_number = 5)
) E
ON A.id_tickler = E.id_tickler
WHERE
MA1 > MA2
AND MA2 < MA3
AND MA3 < MA4
AND MA4 < MA5