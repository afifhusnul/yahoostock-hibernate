/**
 * Author:  NUSNAFIF
 * Created: Mar 5, 2016
 */

CREATE TABLE `stock_holiday` (
  `dt_holiday` date NOT NULL,
  `desc_holiday` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`dt_holiday`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1


INSERT INTO stock_holiday VALUES 
('2013-01-01','Tahun Baru 2013'),
('2013-01-24','Maulid Nabi Muhammad SAW'),
('2013-03-12','	Hari Raya Nyepi Tahun Baru Saka 1935'),
('2013-03-29','Wafat Yesus Kristus'),
('2013-05-09','Kenaikan Yesus Kristus'),
('2013-06-06','Isra Mi’raj Nabi Muhammad SAW'),
('2013-08-05','Cuti Bersama Idul Fitri 1434 Hijriyah'),
('2013-08-06','Cuti Bersama Idul Fitri 1434 Hijriyah'),
('2013-08-07','Cuti Bersama Idul Fitri 1434 Hijriyah'),
('2013-08-08','Hari Raya Idul Fitri 1434 Hijriyah'),
('2013-08-09','Hari Raya Idul Fitri 1434 Hijriyah'),
('2013-10-14','Cuti Bersama Hari Raya Idul Adha 1434 Hijriyah'),
('2013-10-15','Hari Raya Idul Adha 1434 Hijriyah'),
('2013-11-05','Tahun Baru 1435 Hijriyah'),
('2013-12-25','Raya Natal'),
('2013-12-26','Cuti Bersama Hari Raya Natal'),
('2013-12-31','Hari Libur Bursa');

CREATE TABLE `stock_master` (
  `id_tickler` varchar(255) NOT NULL,
  `nm_tickler` varchar(255) NOT NULL,
  PRIMARY KEY (`id_tickler`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO stock_master VALUES 
('AALI.JK','AALI.JK'),
('ABBA.JK','ABBA.JK'),
('ABDA.JK','ABDA.JK'),
('ABMM.JK','ABMM.JK'),
('ACES.JK','ACES.JK'),
('ACST.JK','ACST.JK'),
('ADES.JK','ADES.JK'),
('ADHI.JK','ADHI.JK');


DROP TABLE stock_trx;
CREATE TABLE IF NOT EXISTS `stock_trx` (
  `id_tickler` varchar(8) NOT NULL,
  `dt_trx` date NOT NULL,
  `open_prc` float DEFAULT NULL,
  `high_prc` float DEFAULT NULL,
  `low_prc` float DEFAULT NULL,
  `close_prc` float DEFAULT NULL,
  `vol_trx` float DEFAULT NULL,
  `openint` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1
PARTITION BY RANGE ( YEAR(dt_trx))
(PARTITION p0 VALUES LESS THAN (2010) ENGINE = InnoDB,
 PARTITION p1 VALUES LESS THAN (2011) ENGINE = InnoDB,
 PARTITION p2 VALUES LESS THAN (2012) ENGINE = InnoDB,
 PARTITION p3 VALUES LESS THAN (2013) ENGINE = InnoDB,
 PARTITION p4 VALUES LESS THAN (2014) ENGINE = InnoDB,
 PARTITION p5 VALUES LESS THAN (2015) ENGINE = InnoDB,
 PARTITION pMax VALUES LESS THAN MAXVALUE ENGINE = InnoDB);
 
ALTER TABLE `stock_trx` ADD PRIMARY KEY (`id_tickler`,`dt_trx`);
ALTER TABLE `stock_trx` ADD INDEX (`id_tickler`,`dt_trx`);