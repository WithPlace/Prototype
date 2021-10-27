DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Place;
DROP TABLE IF EXISTS LawdPlace;
DROP TABLE IF EXISTS MatchHist;
DROP TABLE IF EXISTS EstPlaceRate;
DROP TABLE IF EXISTS EstUserRate;
DROP TABLE IF EXISTS EstMatchRate;
DROP TABLE IF EXISTS RECOMMAND;

CREATE TABLE `User` (
    `seq` int PRIMARY KEY,
    `id` varchar(100),
    `regDate` datetime,
    `uptDate` datetime
);

CREATE TABLE `Place` (
     `seq` int PRIMARY KEY,
     `addressName` varchar(100),
     `categoryGroupCode` varchar(100),
     `categoryGroupName` varchar(100),
     `categoryName` varchar(100),
     `distance` varchar(100),
     `id` varchar(100),
     `phone` varchar(100),
     `placeName` varchar(100),
     `placeUrl` varchar(100),
     `roadAddressName` varchar(100),
     `x` varchar(100),
     `y` varchar(100),
     `regDate` datetime,
     `uptDate` datetime
);

CREATE TABLE `LawdPlace` (
     `lawdId` int PRIMARY KEY,
     `lawdCd` varchar(1),
     `lawdDong` varchar(100),
     `exist` varchar(1),
     `regDate` datetime,
     `uptDate` datetime
);

CREATE TABLE `MatchHist` (
     `seq` int,
     `placeSeq` int,
     `lawdId` int,
     `matchDate` varchar(8),
     `matchTime` varchar(8),
     `status` char(1),
     `weather` varchar(10),
     `regDate` datetime,
     `uptDate` datetime,
     PRIMARY KEY (`seq`, `placeSeq`, `lawdId`)
);

CREATE TABLE `EstPlaceRate` (
    `seq` int PRIMARY KEY,
    `matchSeq` int,
    `placeSeq` int,
    `score` int,
    `comment` varchar(500),
    `userSeq` int,
    `depth1` varchar(10),
    `depth2` varchar(10),
    `depth3` varchar(10),
    `regDate` datetime,
    `uptDate` datetime
);

CREATE TABLE `EstUserRate` (
   `seq` int PRIMARY KEY,
   `matchSeq` int,
   `userSeq` int,
   `estUserSeq` int,
   `score` int,
   `comment` varchar(500),
   `regDate` datetime,
   `uptDate` datetime
);

CREATE TABLE `EstMatchRate` (
    `seq` int PRIMARY KEY ,
    `matchSeq` int,
    `score` int,
    `like` varchar(3),
    `dislike` varchar(3),
    `regDate` datetime,
    `uptDate` datetime
);

CREATE TABLE `RECOMMAND` (
    `id` int PRIMARY KEY
);

## 별도로 추가한 값(queue나 redis를 안쓰고 DB처리할때 해당부분이 필요, 대상자의 매칭요청에따라 raw가 생김)
CREATE TABLE 'MATCH_REQUEST'(
    `seq` int PRIMARY KEY,
    `userId` varchar(20),
    `stats` char(1),
    `regDate` datetime,
    `uptDate` datetime
)