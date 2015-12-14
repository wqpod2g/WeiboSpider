/*
Navicat MySQL Data Transfer

Source Server         : 114.212.82.60
Source Server Version : 50096
Source Host           : 114.212.82.60:3306
Source Database       : weibo

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2015-12-14 19:53:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `weiboaccount`
-- ----------------------------
DROP TABLE IF EXISTS `weiboaccount`;
CREATE TABLE `weiboaccount` (
  `accountName` varchar(20) NOT NULL,
  `accountUrl` varchar(100) NOT NULL,
  `history` int(2) NOT NULL,
  `id` int(10) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weiboaccount
-- ----------------------------
INSERT INTO `weiboaccount` VALUES ('新浪财经', 'finance', '1', '1');
INSERT INTO `weiboaccount` VALUES ('第一财经日报', 'yicairibao', '1', '2');
INSERT INTO `weiboaccount` VALUES ('001期货网', '001qh', '1', '3');
INSERT INTO `weiboaccount` VALUES ('招商证券', 'cmschina', '1', '4');
INSERT INTO `weiboaccount` VALUES ('中国证券英才网', 'stockyc', '1', '5');
INSERT INTO `weiboaccount` VALUES ('新浪证券', 'stocknews88', '1', '6');
INSERT INTO `weiboaccount` VALUES ('金融家智库', 'jrjia', '1', '7');
INSERT INTO `weiboaccount` VALUES ('金融界网站', 'jrjnews', '1', '8');
INSERT INTO `weiboaccount` VALUES ('21世纪经济报道', '21cbh', '1', '9');
INSERT INTO `weiboaccount` VALUES ('华尔街日报中文网', 'chinesewsj', '1', '10');
INSERT INTO `weiboaccount` VALUES ('每日经济新闻', 'mrjjxw', '1', '11');
INSERT INTO `weiboaccount` VALUES ('创业家杂志', 'chuangyejia', '1', '12');
INSERT INTO `weiboaccount` VALUES ('凤凰财经', 'financeifeng', '1', '13');
INSERT INTO `weiboaccount` VALUES ('财经网', 'caijing', '1', '14');
INSERT INTO `weiboaccount` VALUES ('新华网财经频道', 'xinhuacaijing', '1', '15');
INSERT INTO `weiboaccount` VALUES ('央视财经', 'cctvcaijing', '1', '16');
INSERT INTO `weiboaccount` VALUES ('银行微博大观', 'bankchannel', '1', '17');
INSERT INTO `weiboaccount` VALUES ('央视财经资讯', 'cctv2', '1', '18');
INSERT INTO `weiboaccount` VALUES ('凤凰财知道', 'czhidao', '1', '19');
INSERT INTO `weiboaccount` VALUES ('央视财经是真的吗', 'cctv2shizhendema', '1', '20');
INSERT INTO `weiboaccount` VALUES ('手机凤凰网财经', '3gifengfinance', '1', '21');
INSERT INTO `weiboaccount` VALUES ('CCTV_滔滔不绝', 'cctvduishou', '1', '22');
INSERT INTO `weiboaccount` VALUES ('经济信息联播', 'cctv2jjxxlb', '1', '23');
INSERT INTO `weiboaccount` VALUES ('财经频道直击达沃斯', '1923758382', '1', '24');
INSERT INTO `weiboaccount` VALUES ('东方财经频道', 'dongfangcaijing', '1', '25');
INSERT INTO `weiboaccount` VALUES ('BTV财经首都经济报道', 'sdjjbd', '1', '26');
INSERT INTO `weiboaccount` VALUES ('第一财经中国经营者', 'managingchina', '1', '27');
INSERT INTO `weiboaccount` VALUES ('中国雅虎财经频道', '1915258194', '1', '28');
INSERT INTO `weiboaccount` VALUES ('财富好计划', 'gouwujieweibo', '1', '29');
INSERT INTO `weiboaccount` VALUES ('新浪浙江财经', 'zjmoney', '1', '30');
INSERT INTO `weiboaccount` VALUES ('河北财经频道', 'hbcjpd', '1', '31');
INSERT INTO `weiboaccount` VALUES ('香港新浪財經', 'sinahkfin', '1', '32');
INSERT INTO `weiboaccount` VALUES ('保险圈', 'bxhudong', '1', '33');
INSERT INTO `weiboaccount` VALUES ('第一财经新理财宝典', 'cbnlicai', '1', '34');
INSERT INTO `weiboaccount` VALUES ('中国创业榜样', 'cctvyscjzgcyby', '1', '35');
INSERT INTO `weiboaccount` VALUES ('财经网生活', 'caijingwanglifestyle', '1', '36');
INSERT INTO `weiboaccount` VALUES ('青岛新闻网财经频道', 'qingdaocaijing', '1', '37');
INSERT INTO `weiboaccount` VALUES ('财经观察频道', '2551269245', '1', '38');
INSERT INTO `weiboaccount` VALUES ('四川财经资讯', 'scfinance', '1', '39');
INSERT INTO `weiboaccount` VALUES ('商都网财经频道', 'shangducaijing', '1', '40');
INSERT INTO `weiboaccount` VALUES ('合肥热线财经频道', '2542675512', '1', '41');
INSERT INTO `weiboaccount` VALUES ('新闻财经频道', 'xiaoyansqe', '1', '42');
INSERT INTO `weiboaccount` VALUES ('能见派', 'nengjianpai', '1', '43');
INSERT INTO `weiboaccount` VALUES ('大公网-财经', 'takungmoney', '1', '44');
INSERT INTO `weiboaccount` VALUES ('财经网公司观察', 'caijingwangcompany', '1', '45');
INSERT INTO `weiboaccount` VALUES ('FX168许亚鑫', '44697898', '1', '46');
INSERT INTO `weiboaccount` VALUES ('齐俊杰', 'qidane', '1', '47');
INSERT INTO `weiboaccount` VALUES ('李骏评论', '1443935474', '1', '48');
INSERT INTO `weiboaccount` VALUES ('范爷365', '224624519', '1', '49');
INSERT INTO `weiboaccount` VALUES ('林毅夫-慧眼看经济', '2926190472', '1', '50');
INSERT INTO `weiboaccount` VALUES ('财经自媒体', 'carglobal', '1', '51');
INSERT INTO `weiboaccount` VALUES ('财经杂志新媒体', 'caijingnewmedia', '1', '52');
INSERT INTO `weiboaccount` VALUES ('野马财经新媒体平台', 'yamafinance', '1', '53');
INSERT INTO `weiboaccount` VALUES ('新金融观察报', 'xjrgc', '1', '54');
INSERT INTO `weiboaccount` VALUES ('信托圈儿', 'xintuoquaner', '1', '55');
INSERT INTO `weiboaccount` VALUES ('券商资管', '2606391594', '1', '56');
INSERT INTO `weiboaccount` VALUES ('肖磊看市', 'xiaoleikanshi', '1', '57');
INSERT INTO `weiboaccount` VALUES ('CCTV2央视财经评论', 'jinriguancha', '1', '58');
INSERT INTO `weiboaccount` VALUES ('央视财经资讯新闻', 'niusongrui', '1', '59');
INSERT INTO `weiboaccount` VALUES ('财经资讯榜单', '2074033667', '1', '60');
INSERT INTO `weiboaccount` VALUES ('财经股票资讯', 'financeinfo', '1', '61');
INSERT INTO `weiboaccount` VALUES ('财经资讯快报', '1661677461', '1', '62');
INSERT INTO `weiboaccount` VALUES ('财经资讯榜', '1686982000', '1', '63');
INSERT INTO `weiboaccount` VALUES ('全球财经资讯榜', '2014348100', '1', '64');
INSERT INTO `weiboaccount` VALUES ('天下财经资讯榜', '1662579485', '1', '65');
INSERT INTO `weiboaccount` VALUES ('财经资讯直通车', '635249345', '1', '66');
INSERT INTO `weiboaccount` VALUES ('财经资讯观察家', '1653400800', '1', '67');
INSERT INTO `weiboaccount` VALUES ('巨潮资讯网', 'cninfocomcn', '1', '68');
INSERT INTO `weiboaccount` VALUES ('财经资讯抢先报', '1850625441', '1', '69');
INSERT INTO `weiboaccount` VALUES ('环球外汇网官网', 'cnforex8', '1', '70');
INSERT INTO `weiboaccount` VALUES ('美通社财经资讯', 'prnafin', '1', '71');
INSERT INTO `weiboaccount` VALUES ('山西财经资讯', 'xinlangshanxicaijing', '1', '72');
INSERT INTO `weiboaccount` VALUES ('蜀商-品财经', 'pincj', '1', '73');
INSERT INTO `weiboaccount` VALUES ('丰华财经官网', 'jfinfo', '1', '74');
INSERT INTO `weiboaccount` VALUES ('第一财经周刊', 'cbnweekly', '1', '75');
INSERT INTO `weiboaccount` VALUES ('财经杂志', 'caijing19980418', '1', '76');
INSERT INTO `weiboaccount` VALUES ('蓝鲸财经记者工作平台', 'lanjingcaijing', '1', '77');
INSERT INTO `weiboaccount` VALUES ('第一财经', 'diyicaijing', '1', '78');
INSERT INTO `weiboaccount` VALUES ('财经中国', 'cjzgcn', '1', '79');
INSERT INTO `weiboaccount` VALUES ('高顿财经', 'goldenacca', '1', '80');
INSERT INTO `weiboaccount` VALUES ('财经国家周刊', 'caijingguojiazhoukan', '1', '81');
INSERT INTO `weiboaccount` VALUES ('财经郎眼', 'caijinglangyan', '1', '82');
INSERT INTO `weiboaccount` VALUES ('华股财经手机证券', 'huagumobile', '1', '83');
INSERT INTO `weiboaccount` VALUES ('财经评论', '2560432091', '1', '84');
INSERT INTO `weiboaccount` VALUES ('今日投资财经资讯', 'investodaysz', '1', '85');
INSERT INTO `weiboaccount` VALUES ('华讯财经网', '591hx', '1', '86');
INSERT INTO `weiboaccount` VALUES ('财经天下周刊', 'caijingtianxia', '1', '87');
INSERT INTO `weiboaccount` VALUES ('财经女记者部落', 'fnlady', '1', '88');
INSERT INTO `weiboaccount` VALUES ('大智慧通讯社', 'dzhnews', '1', '89');
INSERT INTO `weiboaccount` VALUES ('证券资讯博览', 'cctvzqt', '1', '90');
INSERT INTO `weiboaccount` VALUES ('证券时报网', 'secutimes', '1', '91');
INSERT INTO `weiboaccount` VALUES ('证券市场周刊', 'zqsczk', '1', '92');
INSERT INTO `weiboaccount` VALUES ('证券市场红周刊', 'hzkstock', '1', '93');
INSERT INTO `weiboaccount` VALUES ('中国证券网', 'cnstock', '1', '94');
INSERT INTO `weiboaccount` VALUES ('证券之星官方微博', 'stock666666', '1', '95');
INSERT INTO `weiboaccount` VALUES ('和讯', 'hexunwang', '1', '96');
INSERT INTO `weiboaccount` VALUES ('同花顺官方', 'tonghuashun', '1', '97');
INSERT INTO `weiboaccount` VALUES ('数米基金网', 'fund123', '1', '98');
INSERT INTO `weiboaccount` VALUES ('中国日报-财经', 'chinadailybiz', '1', '99');
INSERT INTO `weiboaccount` VALUES ('财界网', '1280149673', '1', '100');
INSERT INTO `weiboaccount` VALUES ('新华08网', 'cfcxinhua08', '1', '101');
INSERT INTO `weiboaccount` VALUES ('一财网', 'iyicai', '1', '102');
INSERT INTO `weiboaccount` VALUES ('华讯财经网', '591hx', '1', '103');
INSERT INTO `weiboaccount` VALUES ('中国经营网', 'cbnew', '1', '104');
INSERT INTO `weiboaccount` VALUES ('深交所', 'szse', '1', '105');
INSERT INTO `weiboaccount` VALUES ('上交所发布', 'chinasse', '1', '106');
INSERT INTO `weiboaccount` VALUES ('广州证券官微', 'gzsecurities', '1', '107');
INSERT INTO `weiboaccount` VALUES ('期货24小时', 'cifco00996', '1', '108');
INSERT INTO `weiboaccount` VALUES ('中国期货研究院王红英', '1736305611', '1', '109');
INSERT INTO `weiboaccount` VALUES ('香港商報網', 'hkcdCN', '1', '110');
INSERT INTO `weiboaccount` VALUES ('证监会发布', 'csrcfabu ', '1', '111');
INSERT INTO `weiboaccount` VALUES ('巨潮资讯网', 'cninfocomcn ', '1', '112');
INSERT INTO `weiboaccount` VALUES ('第一理财网', 'amoney', '1', '113');
INSERT INTO `weiboaccount` VALUES ('理财周刊', 'moneyweekly ', '1', '114');
INSERT INTO `weiboaccount` VALUES ('财新网', 'caixincn ', '1', '115');
INSERT INTO `weiboaccount` VALUES ('第一金融网', 'afinance ', '1', '116');
INSERT INTO `weiboaccount` VALUES ('中国资本证券网', 'ccstock ', '1', '117');
INSERT INTO `weiboaccount` VALUES ('新营销杂志', 'newmarketing ', '1', '118');
INSERT INTO `weiboaccount` VALUES ('中国银行', 'bankofchina ', '1', '119');
INSERT INTO `weiboaccount` VALUES ('福布斯中文网', 'forbeschina ', '1', '120');
INSERT INTO `weiboaccount` VALUES ('中国金融新闻网', 'zgjrxww', '1', '121');
INSERT INTO `weiboaccount` VALUES ('期货日报网', '1865425867 ', '1', '122');
INSERT INTO `weiboaccount` VALUES ('期货日报', 'qhrb', '1', '123');
INSERT INTO `weiboaccount` VALUES ('环球市场播报', 'usstockroll', '1', '124');
INSERT INTO `weiboaccount` VALUES ('华尔街日报中文网', 'chinesewsj ', '1', '125');
INSERT INTO `weiboaccount` VALUES ('新财富杂志', 'newfortune ', '1', '126');
INSERT INTO `weiboaccount` VALUES ('新财富舆情中心', 'xcfyqjk ', '1', '127');
INSERT INTO `weiboaccount` VALUES ('财华网FinetHK', '577440293', '1', '128');
INSERT INTO `weiboaccount` VALUES ('钱经杂志', 'qianjing ', '1', '129');
INSERT INTO `weiboaccount` VALUES ('贸易救济', 'tremedy ', '1', '130');
INSERT INTO `weiboaccount` VALUES ('大智慧通讯社', 'dzhnews ', '1', '131');
INSERT INTO `weiboaccount` VALUES ('财讯网', 'caixuncom ', '1', '132');
INSERT INTO `weiboaccount` VALUES ('环球财讯网', 'financedaily ', '1', '133');
INSERT INTO `weiboaccount` VALUES ('东方壹周-财讯版', 'theweekfortune', '1', '134');
INSERT INTO `weiboaccount` VALUES ('投资中国网', '2649363891', '1', '135');
INSERT INTO `weiboaccount` VALUES ('香港经济日报', 'hket ', '1', '136');
INSERT INTO `weiboaccount` VALUES ('上海金融报', '/shjrb ', '1', '137');
INSERT INTO `weiboaccount` VALUES ('财经中国', 'cjzgcn ', '1', '138');
INSERT INTO `weiboaccount` VALUES ('中国网财经', 'chncaijing ', '1', '139');
INSERT INTO `weiboaccount` VALUES ('财经观察者江南', 'jiangnanstockstar ', '1', '140');
INSERT INTO `weiboaccount` VALUES ('中国基金报道', 'chinesefunds ', '1', '141');
INSERT INTO `weiboaccount` VALUES ('邓学文博士说股市期市', '1469584602 ', '1', '142');
INSERT INTO `weiboaccount` VALUES ('经济之声', 'cnr2 ', '1', '143');
INSERT INTO `weiboaccount` VALUES ('郎咸平', 'langxianpinghk ', '1', '144');
INSERT INTO `weiboaccount` VALUES ('中国经济网', 'cecn ', '1', '145');
INSERT INTO `weiboaccount` VALUES ('经济参考报', 'jjckb ', '1', '146');
INSERT INTO `weiboaccount` VALUES ('中国经济周刊', 'ceweekly ', '1', '147');
INSERT INTO `weiboaccount` VALUES ('中国经济时报', '/cetwb ', '1', '148');
INSERT INTO `weiboaccount` VALUES ('经济之声天下财经', '1846217753 ', '1', '149');
INSERT INTO `weiboaccount` VALUES ('董登新专栏', 'mypension', '1', '150');
INSERT INTO `weiboaccount` VALUES ('高圆圆', 'gaoyuanyuan', '1', '151');
INSERT INTO `weiboaccount` VALUES ('leetcode', 'leetcode', '1', '152');
