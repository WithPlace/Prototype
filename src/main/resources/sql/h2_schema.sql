DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Place;
DROP TABLE IF EXISTS LawdPlace;
DROP TABLE IF EXISTS MatchHist;
DROP TABLE IF EXISTS EstPlaceRate;
DROP TABLE IF EXISTS EstUserRate;
DROP TABLE IF EXISTS EstMatchRate;
DROP TABLE IF EXISTS RECOMMAND;
DROP TABLE IF EXISTS MATCH_REQUEST;

CREATE TABLE `User` (
    `seq` int PRIMARY KEY,
    `id` varchar(100),
    `reg_date` date,
    `upt_Date` date
);

CREATE TABLE `Place` (
     `seq` int PRIMARY KEY,
     `address_name` varchar(100),
     `category_group_code` varchar(100),
     `category_group_name` varchar(100),
     `category_name` varchar(100),
     `distance` varchar(100),
     `id` varchar(100),
     `phone` varchar(100),
     `place_name` varchar(100),
     `place_url` varchar(100),
     `road_address_name` varchar(100),
     `x` varchar(100),
     `y` varchar(100),
     `reg_date` date,
     `upt_Date` date
);

CREATE TABLE `LawdPlace` (
     `lawd_id` int PRIMARY KEY,
     `lawd_cd` varchar(10),
     `lawd_dong` varchar(100),
     `exist` varchar(1),
     `reg_date` date,
     `upt_Date` date
);

CREATE TABLE `MatchHist` (
     `seq` int,
     `place_seq` int,
     `lawd_id` int,
     `match_date` varchar(8),
     `match_time` varchar(8),
     `status` char(1),
     `weather` varchar(10),
     `reg_date` date,
     `upt_date` date,
     PRIMARY KEY (`seq`, `place_seq`, `lawd_id`)
);

CREATE TABLE `EstPlaceRate` (
    `seq` int PRIMARY KEY,
    `match_seq` int,
    `place_seq` int,
    `score` int,
    `comment` varchar(500),
    `userSeq` int,
    `depth1` varchar(10),
    `depth2` varchar(10),
    `depth3` varchar(10),
    `reg_date` date,
    `upt_Date` date
);

CREATE TABLE `EstUserRate` (
   `seq` int PRIMARY KEY,
   `match_seq` int,
   `user_seq` int,
   `est_user_seq` int,
   `score` int,
   `comment` varchar(500),
   `reg_date` date,
   `upt_Date` date
);

CREATE TABLE `EstMatchRate` (
    `seq` int PRIMARY KEY ,
    `match_seq` int,
    `score` int,
    `like` varchar(3),
    `dislike` varchar(3),
    `reg_date` date,
    `upt_Date` date
);

CREATE TABLE `RECOMMAND` (
    `id` int PRIMARY KEY
);

CREATE TABLE `MATCH_REQUEST`(
    `seq` int PRIMARY KEY,
    `user_id` varchar(20),
    `siteX` varchar(20),
    `siteY` varchar(20),
    `status` char(1),
    `request_date` varchar(20),
    `reg_date` date,
    `upt_Date` date,
    `addressCode` varchar(10)
)