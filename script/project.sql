/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - project
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`project` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `project`;

/*Table structure for table `t_about_us` */

DROP TABLE IF EXISTS `t_about_us`;

CREATE TABLE `t_about_us` (
  `id` varchar(64) NOT NULL,
  `img_id` varchar(64) DEFAULT NULL COMMENT '封面图片',
  `content` text COMMENT '内容',
  `create_user_id` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `edit_user_id` varchar(64) DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_about_us` */

LOCK TABLES `t_about_us` WRITE;

insert  into `t_about_us`(`id`,`img_id`,`content`,`create_user_id`,`create_date`,`edit_user_id`,`edit_date`) values ('d2d3879cf72b4d09b03b05970b9a265e','cb2d4040e9b0408c8e96cb81bc07ccfb','<p>某某食品有限公司成立于2011年1月，位于兰陵县经济开发区，总投资3000万元，注册资台800万元。占地面积l3000平方米，其中建筑面积10000平方米。拥有恒温库四座，储藏能力4000吨，其中加工车间6000平方米，该车间主要进行出口保鲜大蒜、保鲜蒜米的加工业务，成品主要出口到东南亚和美国等，年生产加工能力达l8000吨......</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 01:10:52','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 11:36:23');

UNLOCK TABLES;

/*Table structure for table `t_article_class` */

DROP TABLE IF EXISTS `t_article_class`;

CREATE TABLE `t_article_class` (
  `id` varchar(60) NOT NULL COMMENT 'uuid',
  `class_name` varchar(36) DEFAULT NULL COMMENT '类型名称',
  `class_short` int(11) DEFAULT NULL COMMENT '排序',
  `create_user_id` varchar(60) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_user_id` varchar(60) DEFAULT NULL COMMENT '修改者id',
  `edit_date` datetime DEFAULT NULL COMMENT '修改时间',
  `data_type` varchar(1) DEFAULT NULL COMMENT '1新闻2产品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章分类表';

/*Data for the table `t_article_class` */

LOCK TABLES `t_article_class` WRITE;

insert  into `t_article_class`(`id`,`class_name`,`class_short`,`create_user_id`,`create_date`,`edit_user_id`,`edit_date`,`data_type`) values ('2bb638205d0d405388174e38fc6c52a0','公司新闻',2,'0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:22:05','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:22:05','1'),('6b73df2ce98f45fe9f8cf85a8816ee8f','行业新闻',1,'0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 12:00:49','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 12:00:49','1'),('855087a95abf4ca3b5eed0ef98ee7a09','正宗四六瓣蒜米系列',2,'0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:15:22','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:15:22','2'),('f3a9891b90e94aaa8e732a0b7c5598b6','大蒜',1,'0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 12:00:12','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 12:00:12','2');

UNLOCK TABLES;

/*Table structure for table `t_article_info` */

DROP TABLE IF EXISTS `t_article_info`;

CREATE TABLE `t_article_info` (
  `id` varchar(64) NOT NULL COMMENT 'uuid',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `is_top` int(1) DEFAULT NULL COMMENT '是否置顶 1.是 0.否',
  `class_id` varchar(64) DEFAULT NULL COMMENT '类型id',
  `img_id` varchar(64) DEFAULT NULL COMMENT '封面图片id',
  `edit_user_id` varchar(64) DEFAULT NULL COMMENT '修改者id',
  `edit_date` datetime DEFAULT NULL COMMENT '修改日期',
  `data_type` varchar(1) DEFAULT NULL COMMENT '1新闻2产品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

/*Data for the table `t_article_info` */

LOCK TABLES `t_article_info` WRITE;

insert  into `t_article_info`(`id`,`title`,`content`,`create_user_id`,`create_date`,`is_top`,`class_id`,`img_id`,`edit_user_id`,`edit_date`,`data_type`) values ('0db14dc6f54447e984692ed5cf5d8fb8','大蒜','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:18:09',1,'f3a9891b90e94aaa8e732a0b7c5598b6','b54dd937a4f94338b8a409dfd9448eb9','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:18:09','2'),('23e539f5707d4d86842dcd6449829910','大蒜养生知识','<p>大蒜，又称为胡蒜。性温味辛、醇香可口。《本草纲目》指出：蒜：“其气熏烈，能通五脏，达诸窍，去寒湿，辟邪恶，消痈肿，化积肉食，此其功也。”又称：“廉价药膳”。现代医学指出：大蒜含蛋白质、脂肪、糖类、维生素B族、C和钙、磷、铁等。</p><p></p><p>发酵大蒜是日本三重县2003年研发成果。日本著名医学家、弘前大学医学部教授佐佐木甚一通过白鼠试验证明，大蒜的有效成分可提高免疫力，对癌细胞具有强烈的抑制作用。他还预言，大蒜产业会成为“新产业的导火索”。日本著名分子细胞生物学专家、三重大学教授田口宽的研究结果表明，在具有抗氧化损伤功能的300多种食品对比中，大蒜的抗氧化能力名列前茅。日本的研究结果表明，大蒜具有降血压、降血脂、降血糖、防血栓、防癌症、降胆固醇、抗衰老、增强免役力的明显功效。大蒜的这些功效已得到日本科研界和大蒜服用者的充分认可。日本各大媒体争先报导大蒜的神奇功效，日本国内出现抢购热潮，大蒜在日本长期处于供不应求的状态。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:33:35',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','dc220643e4944df7a19b4d1c88f93f7f','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:33:35','1'),('25d6f8d87853473ba93f7a4548ccc620','大蒜','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:16:36',1,'f3a9891b90e94aaa8e732a0b7c5598b6','0356423ecc7a44ffa91e47ec68fc130e','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:16:36','2'),('3580c14aa291493a91b4f1d4f0cf746d','大蒜可预防便秘，你知道吗?','<p>最新研究发现大蒜当中含有维生素B1成分可以帮助消化果糖成分改善胃肠道功能可以润肠通便，膳食纤维成分，促进消化和排泄固体废物，促进肠道蠕动，防止便秘。可以有效预防大肠癌，降低血液中多余胆固醇和糖分成分，改善肠道，迅速解决便秘问题。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:34:00',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','d84871e9e2ce43c88a4cd332281189eb','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:34:00','1'),('4a6db3ab19d9420f80d01c575a554ac3','大蒜','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:17:47',1,'f3a9891b90e94aaa8e732a0b7c5598b6','31ec73321a3d4d2a88b8c5aeaf92c070','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:17:47','2'),('598c7aa6acae4b92b8a143553bb5ab18','看重大蒜能杀菌 \"蒜家军\"销量大涨','<p>季节交替之际，预防各类流感病毒也十分必要。由于看重了大蒜能杀菌消炎的功效，最近半个月来，市场上凡是带着“蒜”字头的蔬菜销量都出现了不小的增长。这不，根据市商务委蔬菜批发行情监测数据显示，近两周大蒜、蒜苗的批发价格较3月上涨了近四成，而记者在菜市场里发现，不少买菜市民的菜篮子里最近也常见蒜苗的身影。</p><p>今天上午，记者走访了市中心的一些标准化菜市场，发现大蒜每500克的零售价在3元左右，而蒜苗每500克地零售价在6元上下，较上个月有了一定幅度的上涨。而市商务委蔬菜批发行情监数据也显示，近期大蒜、蒜苗的批发价也有近四成的涨幅。 虽然价格涨了，不过最近蒜苗和大蒜的销量并不差。 一些市民告诉记者，考虑到大蒜可以杀菌消炎，日常生活中还是调料，所以最近上餐桌的频率有所提高。 不过任何事物都有其两面性，大蒜也有其“禁忌人群”。</p><p></p><p>医生介绍，肝病患者和眼病患者就不宜多吃大蒜，因为大蒜的某些成为对胃肠道有刺激作用，可抑制肠道消化液的分泌，影响食物的消化。此外，对于气血虚弱的眼病患者来说，吃大蒜也是有害无益。而对于大家来说，吃蒜最容易造成的就是口腔异味。对已，建议大家在食用大蒜时可以和蛋白质较丰富的食物一起吃，吃完大蒜后及时刷牙漱口。 另外饭后还可以喝一些牛奶、咖啡、茶或者水果，这些都可以有效减少吃大蒜后带来的口腔异味。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:35:22',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','993009742c004aca975fa3f992066ba5','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:35:22','1'),('64e857ebe36945a289a781179cc9ee9b','清华大学校友总会秘书长唐杰一行莅临公司考察调研','<p>3月24日下午，清华大学校友总会秘书长唐杰率领22人专家团莅临青峰药业创新天然药物与中药注射剂国家重点实验室考察调研。市委常委、区委书记胡雪梅等市区领导陪同调研。在实验室现场，集团副总裁杨小玲向专家团详细介绍了青峰创新平台建设、药品研发、人才队伍等情况。了解后，专家团对青峰的科技创新能力给予了肯定。</p><p><br></p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:14:22',1,'2bb638205d0d405388174e38fc6c52a0','039a14769dae4be1b15534de1f7ff93b','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:14:22','1'),('7a2a4028ea11414baf8ac10e3a2f5288','正确吃大蒜，吃出好身体','<p>经常食用大蒜对人体健康的好处已是众所周知。这些好处都得益于大蒜中的一种叫做大蒜素的东西。大蒜素的功效很多，它是一种广谱抗菌药，具有消炎、降血压、降血脂、抑制血小板凝集、防癌、抗病毒等多种生物学功能，还能明显提高机体免疫力。</p><p>然而，吃大蒜是很有讲究的，不是随便怎么吃都能使大蒜素的这些药理效应发挥作用。如果不能科学地吃，正确地吃，就算吃再多的大蒜也会无济于事。因为大蒜素的产生需要一定的过程，需要创造一些必要条件，使大蒜素产生效应后，才能起到有益人体健康的作用。</p><p></p><p>首先，大蒜素原本是以大蒜氨酸的形式存在于大蒜中，遇氧气后，在大蒜酶作用下转化而产生。所以，为了让大蒜产生大蒜素，在吃大蒜的时候，第一是生吃，如果没有胃部不适的话，尽量生吃大蒜，以免大蒜素在热环境中失去药理活性；第二是捣碎或切成薄片，并暴露在空气中15~20分钟后再吃，目的是让大蒜充分氧化，在有氧环境中与大蒜酶起反应，产生大蒜素。每天也不用吃很多，吃个三五瓣就够了。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:34:32',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','58cf99105b82473d972ee340533391cd','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:34:32','1'),('82c180e6f3af42ec8665d6349b330b88','大蒜的保存方法','<p>买到家的大蒜一时吃不了，存放时极易生芽，这是很令人烦恼的事，怎么解决这一问题呢？办法很简单，只要将大蒜装在塑料袋里，再把袋口封严就可以了。因为这样可使袋内大蒜释出的二氧化碳气体散发不出去，相对减少了袋内的氧气，同时也阻隔了水分的进入，从而使大蒜处于一种休眠状态。 准备贮藏的大蒜应在叶片开始变黄时选晴天适时采收。收获时，先将大蒜下面的土壤刨松，以便植株易从土中拔出。收获后，将大蒜堆成堆，以蒜叶覆盖蒜头晒2--3天，待蒜秸开始萎蔫时，修剪根瓣，剪去叶茎，然后将蒜头放入筐内。最好将蒜头暴晒或人工干燥。人工干燥以30℃的温度，50％左右的空气相对湿度为宜，这有利于加速蒜头干燥，促进休眠，延长贮藏期。</p><p>1．低温贮藏：贮藏前应将蒜头晾干，如果未晾干，蒜头会因湿度过高而导致腐烂。大蒜一般应贮藏在温度0.6--0℃，空气相对湿度65％--70％，通风、干燥、无烟火的菜窖或房子里。此法可贮藏6--7个月。</p><p>2．高温贮藏：大蒜经历一个低温休眠阶段后，在5--18℃下会迅速发芽。将采收后的大蒜一直放在30--34℃高温环境中贮藏，可保鲜一年以上。此外，在相对湿度40％--50％的条件下，也可抑制蒜头的呼吸代谢，延迟发芽。低氧和高二氧化碳同样可抑制蒜头的呼吸和发芽。 留种大蒜宜采取高温贮藏，以温度为15℃左右、相对湿度不超过70％的条件为宜，这样有利于提高种性。如果冬季能确保温度稳定在-1--3℃，在播前40天左右改为18--20℃高温处理，也能收到同样的保鲜效果。</p><p>3．串挂贮藏：采收时将大蒜植株连根拔起，剔除过大、过小、腐烂或机械损伤和雨淋受潮的蒜头，再用刀沿鳞茎底盘将根须及夹带的一部分茎盘削去，只留下10--15cm假茎，注意削平茎盘。经暴晒或人工干燥后，将蒜头假茎用镀锌铁丝串起来，悬挂在房屋前后的屋檐下，或先将大蒜每8--10只扎成一把，再一排排串挂在屋檐下的铁丝或尼龙绳上自然风干。夏秋季可悬挂在临时凉棚、冷凉室内或通风贮藏库内；冬季最好移入通风贮藏库内，避免受潮受冻。</p><p>4．砻糠埋藏：在箱、筐或埋藏坑的底部先铺一层厚约2cm的砻糠，然后一层蒜头(2--3只蒜头高)一层砻糠，层层堆积至离容器口5cm左右，用砻糠覆盖，使蒜头不暴露在空气中。</p><p>5．化学贮藏：在大蒜收获前7天内，用0.1％--0.15％浓度的青鲜素(MH)水溶液喷洒。药液一定要喷均匀，叶片上下须布满雾珠。药后24小时内如遇雨，天晴后须重喷一次，一般喷药后可存放到新蒜上市。蒜头可悬挂、码堆在通风阴凉的地方保存。留种大蒜不能喷洒青鲜素。</p><p></p><p>6．涂膜保鲜：先将石蜡熔成石蜡液，然后将晒干的蒜头在石蜡液中浸一下即可。石蜡液温度不宜太高，浸的时间不宜太长，只要浸匀、浸透即可。此法可将蒜头贮存到翌年5月。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:32:56',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','37df30e935a14706a981b0ca608587b2','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:32:56','1'),('99e6cac313ae4a64996b32fd2d4bc56f','大蒜','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:16:53',1,'f3a9891b90e94aaa8e732a0b7c5598b6','744fbd33cfd74b6f892ed7c16c0487ac','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:16:53','2'),('aef7467834c34b919f9354960579cba7','大蒜','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:17:18',1,'855087a95abf4ca3b5eed0ef98ee7a09','bbcf71e4607343a59cfead538b82102a','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:17:18','2'),('b875661bf0b34370aedbd2481199886a','大蒜简单贮藏三法','<p>大蒜属于葱科，系葱蒜类蔬菜。按色泽分为白皮蒜和紫皮蒜。大蒜耐寒，早春2月~4月即可播种，6月~7月收获；秋播经露地越冬，第二年5月~6月收获。经贮藏后可常年供应市场。 贮藏特性 当蒜薹收获20天左右，叶片约有1/2或2/3变黄、鳞茎已充分肥大时为蒜头的适宜收获期。采收前3天~5天要停止灌水，以降低土壤的湿度，提高鳞茎的品质和耐藏性。大蒜具有明显的生理休眠期，休眠期长达2个~3个月。大蒜在脱离休眠期后，环境温度高于5℃时易萌芽，高于10℃时易腐烂。休眠期过后，控制零下低温及干燥的贮藏条件，也可抑制萌芽及生根。一般适宜的贮藏温度为0℃左右，相对湿度为70%左右。大蒜的冰点为-0.83℃。 贮藏方法 大蒜采后经过充分晾晒便进入休眠状态，可采用编制袋组织贮藏运输。贮藏方法有如下3种。</p><p>1、挂藏法 大蒜晾干后，经挑选，剔除机械伤、腐烂或皱缩的蒜头，编成辫。夏秋之间放在临时晾棚、冷宅或通风的贮藏库内；冬季为避免受潮、受冻，最好放在通风贮藏库内；也可将蒜头假茎用铁丝串起来，悬挂在通风保温处，使蒜头自然风干，采用此方法，鳞茎不易腐烂、质量好，简单易行。</p><p>2、窖藏法 选择地势较高、干燥、土质坚厚、地下水位低的地块挖窖。在窖底铺一层蒜，堆一层草，但堆积的厚度不可过高，以便通风。窖内安设通风道，窖藏期间要定期检查，随时除去腐烂或变质的蒜头。此法利用窖的低温和低湿等条件使大蒜有一个稳定的贮藏环境。在东北等寒冷地区使用窖藏的效果较好。</p><p></p><p>3、冷库贮藏法 入库前先将大蒜放入预冷间进行降温，当大蒜的品温接近0℃时，便可入库贮藏。贮藏时摆放在架子上，以便通风。要保持库内温度均匀一致。入库后要及时控制温度。根据包装和堆码方式的不同，库温可控制在-1℃~-3℃之间。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:30:54',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','222094b42c4748c297038d32cc5e86c3','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:30:54','1'),('bb592a1483ab45999c23b0c6501093f6','大蒜种植技术的五个要点','<p>在大蒜种植技术中，进行大蒜种植时需要注意的工作有很多，下面就详细介绍大蒜种植技术的五个要点。 大蒜种植技术的五个要点具体介绍如下。</p><p>(一) 品种选择 在大蒜种植技术当中，目前比较适宜麒麟区种植的主要品种有四川红七星（一代、二代）、软叶蒜、一级早、二级早、本地紫皮蒜等。</p><p>(二)播种时间 坝子区域一般是早蒜苗、红七星（一代、二代）种植时间是在白露节令（9月上旬）。二级早最佳播种期是在秋分节令（9月中下旬）前后。迟蒜播期要以秋分至寒露为宜（即9月23-10月8日）。高海拔地区就可提前一个节令种植。</p><p>(三)种子处理 播种前，先将蒜瓣选择和分级，除去病虫害侵染的蒜瓣，按大、小分开，分级播种。 (四)整地作墒，合理密植 整地作墒时要求上细下粗，墒面宽3-4米。在大蒜种植技术中，播种不宜过深，土壤要保持一定的湿润，促进提早出苗。红七星亩用种量需100-120公斤，其它品种蒜瓣小，亩用种量只需60―75公斤，每亩密度在3万―4万株，行距15―18厘米，株距8―10厘米，早熟品种偏密，晚熟品种偏稀。</p><p>（五）田间管理</p><p>1、除草松土：大蒜出苗后如果土壤板结、杂草丛生，应结合除草进行中耕，当苗高10－13厘米，有2-3片叶时进行第一次中耕，苗高26-33厘米有5-6片叶时进行第二次浅中耕。在大蒜种植技术中，蒜地除草比较费工，为了提高功效应推广化学除草，化学药剂除草是：在大蒜栽后3天施药，用果尔、杀草丹进行喷施防草，出苗后应中耕1―2次防止土壤板结、杂草丛生。若苗期杂草较多，可针对不同种类的杂草选用不同除草剂防除一次。</p><p>2、灌水：大蒜播种后如果土壤潮润一般不需灌水即可出苗，土壤干燥时应灌一次齐苗水。在幼苗期一般应少灌水、多中耕，适当控制水分，防止徒长和“退母”过早。在抽苔和鳞茎肥大期要增加灌水，避免受旱，保持土壤湿润。灌水应以沟灌为主，水不淹过墒面，土潮即撤，避免大水漫灌，蒜头采收前5～7天应停止灌水，否则容易产生散瓣蒜。</p><p>3、施肥：大蒜生长期长，需肥量大，但根系浅，吸肥能力弱，所以应施足底肥，分期追肥，不断满足大蒜生长发育的需要。大蒜施肥应以有机肥为主，占总用肥量的70%，氮、磷、钾比例1：0.8：1为宜，亩用纯氮12.5公斤左右，注意补充含钙肥料，增施硼肥，施肥上可使用“一道清”或重底早追方法。一般底肥亩用30公斤复合肥（15：15：15）加猪粪50―80担，退母期（出苗后5叶左右）追一次提苗肥，亩用尿素30公斤或35公斤碳铵，圆脚期（8片叶后），可每亩施20公斤尿素或30公斤碳铵。</p><p></p><p>4、病虫害防病：大蒜生长期间，特别是12月份以后，寒潮出现较多、低寒寡照、高湿度易导致大蒜病虫害发生。在大蒜种植技术中，病害主要有锈病、叶枯病、疫病。防治药剂：粉锈宁、百菌清、敌菌特、速克灵，可以在发病初期或发病期间施药；虫害有葱蓟马，可用敌百虫进行扑杀。立春以后，随着气温的上升，日照增长，大蒜进入快速生长阶段，此时需肥水最多，应注意看苗补肥、适时灌水，同时降低田间温度，以满足大蒜生长需要。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:32:09',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','64e40feec43d456db3cbf1070309d481','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:32:09','1'),('bc039944e1734535a6164d498e7774af','正宗四六瓣蒜米系列','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:16:15',1,'855087a95abf4ca3b5eed0ef98ee7a09','4d60ab285e464249a270ba9b065c5346','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:16:15','2'),('defa326b302240cabeecfac9e6e2f9f8','新型生物源新农药取材自大蒜','<p>从人类的食物当中提取防治农作物病虫害的有效成分--这听起来似乎悬乎的想法，如今在贵州变成了现实。贵州大学生物技术系教授周建正式公布了他采用生物技术，从食用大蒜等食物中提取有效成分研发而成的新型农药--菌妥。据介绍，这是我国第一个直接从可食性植物中提取的生物源新农药。 这种农药适用于严格要求无残留、无污染、安全的有机食品和绿色食品(AA)级等农作物的病害防治，已获准国家农药临时登记证，是国内外农药产品中的领先品种。</p><p></p><p>据周教授介绍，菌妥的主要成分是大蒜素，过去也有在原料中添加大蒜素的抗生素，但大都是化学合成，不同于菌妥这种直接从天然大蒜中提取而成的可食性生物农药。菌妥的成分中有98%是食物，而其余的2%是食品添加剂，其生产过程低污染、使用过程无污染。经国内外许多地区的田间药效试验证明，菌妥对黄瓜、荷兰豆等蔬菜、果树以及中药材、花卉的白粉病等病害防治率可达到80%左右。此前，周建教授曾被美国JH公司聘为生物技术专家，他在美国研制的与菌妥同类型的农药产品已获美国发明专利。由于这种农药产品完全达到了国际上对有机食品的病害防治要求，美国有关农药监管部门将其列为具最小危害性的“豁免农药”，享受免于农药登记的特殊政策，而在美国能得豁免的农药目前只有百余种。周教授说，与菌妥相比，在美国研发的受豁免农药还只是初级阶段产品，菌妥在配方、药效方面更具先进性。 有关专家指出，菌妥的研发展示了用人类食物防治农作物病虫害的新方向，它既是我国农业科研领域的新成果，也是有机食品、绿色食品生产领域急需的宝贵生产原料。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 12:01:25',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','6e32c6684834490bb9eab87a3d5425a3','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 12:01:25','1'),('eacb3b63e90f40f6804921e9ca51bd49','杂交蒜米','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 11:59:54',1,'f3a9891b90e94aaa8e732a0b7c5598b6','67c00238e02642b1909ac9b3e216767a','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 12:00:21','2'),('f10b797b613c4dff91a95b11867cd7b9','大蒜','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:17:36',1,'f3a9891b90e94aaa8e732a0b7c5598b6','95e04e9a127b420cb83f30cd5fe65f76','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:17:36','2'),('f11446e50b484bee98b0369540b803b7','大蒜','<p>舌尖上的大蒜5cm左右农家种植 无农药 放心食用 物理除虫远离污染施农用肥 真正来自大蒜之乡的绿色农家蒜！2017年新蒜个头均匀新鲜的大蒜个头大大蒜果型大小均匀 汁多味浓大蒜肉质白嫩蒜瓣大，汁多蒜味浓郁 美食搭配</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:18:27',1,'855087a95abf4ca3b5eed0ef98ee7a09','8fd48c1a58e6479b9b93f445655a0e97','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 16:18:27','2'),('f443b07ca0834e17aacbebb577fcf76e','大蒜加工技术','<p>1、蒜片、蒜粉 选择成熟充分、瓣粒饱满、辣味浓香、个头肥大的蒜头。采后削去须根，剪去茎盘，浸泡1－2天，搓洗去皮，洗净晾干切成薄片，蒸气烫漂5分钟。采用远红外线干燥装置，装载量每平方米5－6千克，温度65℃－75℃，6－7小时，当干制品含水量达5％－8％时，随即包装贮藏。成品率15％－20％。蒜粉制作只需在蒜片加工基础上，增添一道碾磨过筛工序，即得成品。</p><p>2、泡菜 蒜头采收后，削去须根，剥外皮晾干。在50千克水中加盐4千克，煮沸冷却，另外加花椒50克，红辣椒1千克，姜1．5千克，酒1．5千克，制成囟汁。然后将整蒜头装缸或泡坛内，倒入囟汁，用冷开水扣碗加盖封口。一般常温下发酵10天即成。</p><p>3、糖醋蒜薹 将蒜薹摘掉缨帽，清水淘洗，切成3厘米长的小段。入缸加水加盐，每天倒缸两次，四五天后捞出，放在席片上轻揉；入缸，加囟汁腌制。囟汁配方：糖、醋、水混合煮沸，冷却入缸，10天倒缸一次，30天即成。</p><p></p><p>4、五香糖醋蒜 蒜头50千克，食盐2千克，红（白）糖10千克，酱油0．5千克，蒜头处理如上。将大蒜洗净晾干，一层蒜撒一层盐，腌制24小时，重新入缸，糖、醋、酱油、五香粉加凉开水配制囟汁，再次腌制。油纸或薄膜封口扎严。每天转缸两次，隔天开缸散气4－5小时，半个月后改为3天散气一次，1个月即成。</p><p><br></p>','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:23:24',1,'6b73df2ce98f45fe9f8cf85a8816ee8f','5aac4ee859f948368badb958ca64defc','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 15:23:24','1');

UNLOCK TABLES;

/*Table structure for table `t_friend_link` */

DROP TABLE IF EXISTS `t_friend_link`;

CREATE TABLE `t_friend_link` (
  `id` varchar(64) NOT NULL,
  `link` varchar(200) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `create_user_id` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `edit_user_id` varchar(64) DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_friend_link` */

LOCK TABLES `t_friend_link` WRITE;

insert  into `t_friend_link`(`id`,`link`,`name`,`create_user_id`,`create_date`,`edit_user_id`,`edit_date`) values ('096b3aadca8346a0aa0cbdedcb7ac57a','https://www.alipay.com','支付宝','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:19:45','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:19:45'),('60ec82aab096492896d438ccc19290e6','https://github.com','github','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:19:12','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:19:12'),('67c3c26869274c709b57afc4f3a61b4e','https://www.csdn.net','csdn','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:18:55','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:18:55'),('6d4b76da515d494a876df74865afd119','https://www.zhihu.com','知乎','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:18:38','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:18:38'),('cdf2460d19f7430d87565db220554dc7','www.qq.com','腾讯网','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:19:30','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:19:30'),('f5a9607480a44181acc8a9fc2a30f6c3','https://www.baidu.com','百度','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:18:22','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 13:18:22');

UNLOCK TABLES;

/*Table structure for table `t_leave_word` */

DROP TABLE IF EXISTS `t_leave_word`;

CREATE TABLE `t_leave_word` (
  `id` varchar(64) NOT NULL,
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(64) DEFAULT NULL COMMENT '电话',
  `qq` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_leave_word` */

LOCK TABLES `t_leave_word` WRITE;

insert  into `t_leave_word`(`id`,`title`,`content`,`name`,`phone`,`qq`,`create_date`) values ('7ddfaf307eb247dab91794f0f665cdbb','1111','111','1','1','1','2018-04-18 14:10:06');

UNLOCK TABLES;

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` varchar(64) NOT NULL COMMENT 'uuid',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '上级菜单Id',
  `host_path` varchar(255) DEFAULT NULL COMMENT 'web跳转地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理菜单表';

/*Data for the table `t_menu` */

LOCK TABLES `t_menu` WRITE;

insert  into `t_menu`(`id`,`name`,`parent_id`,`host_path`,`remark`,`sort`) values ('2a3843a4d3324f8a85a9f276c4171b47','关于我们','b335246b358f43c1b0552456d8d118da','/admin/aboutus/aboutus','',1),('2a927849b97e48fea70a3bd6982bcaa2','留言管理','eaca2e4d86474829bcdd07c92f3f7f0f','','fa-comments-o',4),('3e47da42f231415e942a53a75e5fa7d9','留言管理','2a927849b97e48fea70a3bd6982bcaa2','/admin/leaveword/leaveword','',1),('3e7aea60ea50426db4a661e586ce8218','系统设置','eaca2e4d86474829bcdd07c92f3f7f0f','','fa-cogs',10),('45d6d647ac404b388796b696cf6fce70','用户管理','eaca2e4d86474829bcdd07c92f3f7f0f','/admin/user','fa-male',9),('55609020c955426b911ded25c7d4e0df','友情链接','881e824be88b40819d33c202c24d68b8','/admin/friendlink/friendlink','',1),('5d778c0a35704120b2361f28f23b8c27','新闻管理','a68620cf7caf4d40b287aa831c353ac9','/admin/article/newsInfo',NULL,2),('61a285e65f8c4317ab2058b0db61cd3b','系统菜单','3e7aea60ea50426db4a661e586ce8218','/admin/system/sysMenu',NULL,2),('6e70c77f646e4024a9a148bf1a5a0b9f','产品分类','7e0366b9c0114939a0df3e5d93d50ff1','/admin/article/productClass','',1),('7e0366b9c0114939a0df3e5d93d50ff1','产品管理','eaca2e4d86474829bcdd07c92f3f7f0f','','fa-truck',2),('881e824be88b40819d33c202c24d68b8','友情链接','eaca2e4d86474829bcdd07c92f3f7f0f','','fa-anchor',5),('941cdf0371bc479cbd40c5c3fede52fe','产品管理','7e0366b9c0114939a0df3e5d93d50ff1','/admin/article/productInfo','',1),('a68620cf7caf4d40b287aa831c353ac9','新闻管理','eaca2e4d86474829bcdd07c92f3f7f0f','','fa-book',1),('a76a508f96ce400389ac2e7df26171dd','角色权限','3e7aea60ea50426db4a661e586ce8218','/admin/system/roleMenu',NULL,1),('b335246b358f43c1b0552456d8d118da','关于我们','eaca2e4d86474829bcdd07c92f3f7f0f','','fa-globe',3),('b3d66f0dfc2c4ee9866d675d8572bfa4','项目配置','eaca2e4d86474829bcdd07c92f3f7f0f','','fa-bars',6),('c3c26138d8a843c9b8aaf578cfeb1c22','项目配置','b3d66f0dfc2c4ee9866d675d8572bfa4','/admin/projectconfig/projectconfig','',1),('c77d5a8ba54f433895d04dc7b07cbc90','新闻分类','a68620cf7caf4d40b287aa831c353ac9','/admin/article/newsClass','',1),('eaca2e4d86474829bcdd07c92f3f7f0f','根目录','',NULL,NULL,1),('fea1f66bc2b843789c437eda66b8e38d','用户管理','45d6d647ac404b388796b696cf6fce70','/admin/user/userInfo','',4);

UNLOCK TABLES;

/*Table structure for table `t_project_config` */

DROP TABLE IF EXISTS `t_project_config`;

CREATE TABLE `t_project_config` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `phone` varchar(64) DEFAULT NULL COMMENT '电话',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `content` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `edit_user_id` varchar(64) DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_project_config` */

LOCK TABLES `t_project_config` WRITE;

insert  into `t_project_config`(`id`,`name`,`address`,`phone`,`email`,`content`,`create_user_id`,`create_date`,`edit_user_id`,`edit_date`) values ('7fb5bf3253764e8a8586ed14b09faaa8','某某食品有限公司欢迎您','江苏省南京市玄武区玄武湖','020-22043297','1063642821@qq.com','本页面内容为网站演示数据，前台页面内容都可以在后台修改。','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 11:30:56','0fd2261e8a2b423ca5cb6370d0f34cd5','2018-04-18 11:30:56');

UNLOCK TABLES;

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(64) NOT NULL COMMENT 'uuid',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名字',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `t_role` */

LOCK TABLES `t_role` WRITE;

insert  into `t_role`(`id`,`role_name`,`remark`,`create_date`) values ('7594e5d29f7111e7b41c060400ef5315','系统管理员','管理员','2017-09-22 16:38:49'),('7594e5d39f7111e7b41c060400ef5315','普通用户','普通用户没有权限登录后台','2017-09-22 16:38:29');

UNLOCK TABLES;

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `id` varchar(64) NOT NULL,
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(64) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

/*Data for the table `t_role_menu` */

LOCK TABLES `t_role_menu` WRITE;

insert  into `t_role_menu`(`id`,`role_id`,`menu_id`) values ('06f4dbe34c2a4f2e8e08ff120c2d5acc','7594e5d39f7111e7b41c060400ef5315','5d778c0a35704120b2361f28f23b8c27'),('0c13c2c8014243b4b33189a93d8ab67f','7594e5d29f7111e7b41c060400ef5315','5d778c0a35704120b2361f28f23b8c27'),('163c47acb9a449a4b67ec83fbdc4d155','7594e5d39f7111e7b41c060400ef5315','c3c26138d8a843c9b8aaf578cfeb1c22'),('1eeaa6b0e34d48d9b5e1a3bf5c98265d','7594e5d29f7111e7b41c060400ef5315','b335246b358f43c1b0552456d8d118da'),('20b32f011d834cfebabef859318c23a2','7594e5d29f7111e7b41c060400ef5315','881e824be88b40819d33c202c24d68b8'),('390da641a78b444faa0ae8e9dd0f039f','7594e5d39f7111e7b41c060400ef5315','b335246b358f43c1b0552456d8d118da'),('3ef8efcd79db4fb49442895b97bb02fe','7594e5d29f7111e7b41c060400ef5315','a76a508f96ce400389ac2e7df26171dd'),('48631725120743baac5cd51c2b0d547b','7594e5d39f7111e7b41c060400ef5315','2a3843a4d3324f8a85a9f276c4171b47'),('4e67559220d4427688e6e28a73100d15','7594e5d29f7111e7b41c060400ef5315','3e47da42f231415e942a53a75e5fa7d9'),('534ba686e4db49e59904fd42b69d62da','7594e5d29f7111e7b41c060400ef5315','c3c26138d8a843c9b8aaf578cfeb1c22'),('61132b9f91cc4b13bc1cb45ab864e62f','7594e5d39f7111e7b41c060400ef5315','941cdf0371bc479cbd40c5c3fede52fe'),('6a63abc86cff4b1390cba26c0d56ef9c','7594e5d29f7111e7b41c060400ef5315','6e70c77f646e4024a9a148bf1a5a0b9f'),('74a90184b4994d4f8a0e6598204d3e95','7594e5d39f7111e7b41c060400ef5315','eaca2e4d86474829bcdd07c92f3f7f0f'),('76d33ec433894869b575ec61a883b9f5','7594e5d29f7111e7b41c060400ef5315','2a3843a4d3324f8a85a9f276c4171b47'),('781a9a7aef6f4d84b7602e916ddc3c3b','7594e5d39f7111e7b41c060400ef5315','3e47da42f231415e942a53a75e5fa7d9'),('7da99c97cc354344969379cbf799ac52','7594e5d29f7111e7b41c060400ef5315','b3d66f0dfc2c4ee9866d675d8572bfa4'),('922351e746fb4e56a15c33f14a0aff2d','7594e5d39f7111e7b41c060400ef5315','2a927849b97e48fea70a3bd6982bcaa2'),('944edd4e9ecd45d9b83b7c33f71f8010','7594e5d39f7111e7b41c060400ef5315','6e70c77f646e4024a9a148bf1a5a0b9f'),('96525da8d6b446e6b26b77a1828e2594','7594e5d29f7111e7b41c060400ef5315','a68620cf7caf4d40b287aa831c353ac9'),('a28ea9678d60411cbb1244c18578155d','7594e5d39f7111e7b41c060400ef5315','7e0366b9c0114939a0df3e5d93d50ff1'),('a9504b8cb8cf4c8ea21608833b84718e','7594e5d39f7111e7b41c060400ef5315','b3d66f0dfc2c4ee9866d675d8572bfa4'),('ac05468937c3485fb853d8b9b3e51ebc','7594e5d39f7111e7b41c060400ef5315','c77d5a8ba54f433895d04dc7b07cbc90'),('acd6a19400b645faa22f03f2fef9a85e','7594e5d29f7111e7b41c060400ef5315','c77d5a8ba54f433895d04dc7b07cbc90'),('b3ce2d25076f4929a4c7887aab00ecaa','7594e5d29f7111e7b41c060400ef5315','3e7aea60ea50426db4a661e586ce8218'),('b89f87d25dae47f68432901c5a6af20d','7594e5d29f7111e7b41c060400ef5315','941cdf0371bc479cbd40c5c3fede52fe'),('bb733ea3129b45c3b6b273631ad0d49a','7594e5d39f7111e7b41c060400ef5315','55609020c955426b911ded25c7d4e0df'),('c1226c1fd4774a97876363e84abf1a21','7594e5d29f7111e7b41c060400ef5315','fea1f66bc2b843789c437eda66b8e38d'),('c84572551f0c4c2d827b49d90662a229','7594e5d39f7111e7b41c060400ef5315','a68620cf7caf4d40b287aa831c353ac9'),('d0bc050b57194c719a91abddb3388522','7594e5d29f7111e7b41c060400ef5315','55609020c955426b911ded25c7d4e0df'),('d110242b28094dcc8796f2358a63857f','7594e5d29f7111e7b41c060400ef5315','eaca2e4d86474829bcdd07c92f3f7f0f'),('d325f0e0637f484187349308a2558b4e','7594e5d29f7111e7b41c060400ef5315','61a285e65f8c4317ab2058b0db61cd3b'),('d6f8be55b90a4c2fa10258a21456f1b8','7594e5d29f7111e7b41c060400ef5315','2a927849b97e48fea70a3bd6982bcaa2'),('e400657cadc34ea0bccd8896563279d5','7594e5d29f7111e7b41c060400ef5315','45d6d647ac404b388796b696cf6fce70'),('e9864642051247b99f89bb5f69d27fae','7594e5d29f7111e7b41c060400ef5315','7e0366b9c0114939a0df3e5d93d50ff1'),('fa14fccfdfe74364a244dcaaba888ace','7594e5d39f7111e7b41c060400ef5315','881e824be88b40819d33c202c24d68b8');

UNLOCK TABLES;

/*Table structure for table `t_upload_file` */

DROP TABLE IF EXISTS `t_upload_file`;

CREATE TABLE `t_upload_file` (
  `id` varchar(64) NOT NULL COMMENT 'uuid',
  `file_type` varchar(64) DEFAULT NULL COMMENT '文件类型',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件访问路径',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名字',
  `create_date` datetime DEFAULT NULL COMMENT '上传日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='OSS文件本地库';

/*Data for the table `t_upload_file` */

LOCK TABLES `t_upload_file` WRITE;

insert  into `t_upload_file`(`id`,`file_type`,`file_path`,`file_size`,`file_name`,`create_date`) values ('0356423ecc7a44ffa91e47ec68fc130e','image/jpeg','/upload/608bde0be3e3445cbe6894f3adbaab65.jpg',27236,'1518177550.jpg','2018-04-18 16:16:36'),('039a14769dae4be1b15534de1f7ff93b','image/jpeg','/upload/d0ebe02d4ee340119129d8fc26b01c85.jpg',81303,'201803281642209666.jpg','2018-04-18 16:14:22'),('0528388ffbec4496b98124e9494144c0','image/jpeg','/upload/4a906afe4aba459881a1327601ddcf69.jpg',361008,'5876748292133427.jpg','2018-04-18 00:38:18'),('05e395dbe5364497b985f6016c7429ea','image/jpeg','/upload/1ac85f6ef1d24e67932d2850392e9338.jpg',81022,'7852156981351786.jpg','2018-04-17 23:33:52'),('08d4cbd5ca244d9ebbd670ba0820c993','image/png','/upload/5d49a9552b4a44d69f213f42262d0878.png',6397,'404-error.png','2018-04-18 01:10:52'),('222094b42c4748c297038d32cc5e86c3','image/jpeg','/upload/ac2639e2eb6c47198bdd59f3f6c1b000.jpg',426601,'1518177981.jpg','2018-04-18 15:30:54'),('31ec73321a3d4d2a88b8c5aeaf92c070','image/jpeg','/upload/bed09a1f09ec4d6d8b23635bccfe885e.jpg',424303,'1518177847.jpg','2018-04-18 16:17:47'),('332975b79fa74aa58c42c3bb1793c179','image/jpeg','/upload/c0031be6558b4ddf955408ec7f8145cf.jpg',6278,'danshengou.jpg','2018-04-17 23:24:28'),('34a2a091ca464832b5748c081679a5cc','image/jpeg','/upload/0117208583714dbbbc28f0264d75ff73.jpg',81022,'961111168617806.jpg','2018-04-18 00:35:02'),('37df30e935a14706a981b0ca608587b2','image/jpeg','/upload/e3a7dae3c5cb484e8d456daecfad1629.jpg',63182,'1518177734.jpg','2018-04-18 15:32:56'),('3eeb106fcd68442b96dc0f6c0d307dd7','image/jpeg','/upload/63f1849c-aeb6-4964-a3fc-765761858a94.jpg',247267,'1518178208.jpg','2018-04-17 00:06:37'),('471a3f3e119b4ce083a2e8bbf65a00d1','image/jpeg','/upload/fa25cc36d874432fa46407af7bf3a850.jpg',443344,'6109266205831183.jpg','2018-04-18 00:57:12'),('4d60ab285e464249a270ba9b065c5346','image/jpeg','/upload/0a91929378b7409c84d2dcf10a7289bc.jpg',420407,'1518179197.jpg','2018-04-18 16:16:15'),('58cf99105b82473d972ee340533391cd','image/jpeg','/upload/a6c700c44a2c43ab82e902917732b72f.jpg',203840,'1518177279.jpg','2018-04-18 15:34:32'),('5aac4ee859f948368badb958ca64defc','image/jpeg','/upload/e0881931610c4292a178e3eabcd62ce7.jpg',140441,'1518178119.jpg','2018-04-18 15:23:24'),('64e40feec43d456db3cbf1070309d481','image/jpeg','/upload/b059914b019041a6a54c5fb744ed3694.jpg',424303,'1518177847.jpg','2018-04-18 15:32:09'),('67c00238e02642b1909ac9b3e216767a','image/jpeg','/upload/33293b745f2746339cf7c64bbabe1da9.jpg',443344,'1518179473.jpg','2018-04-18 11:59:55'),('6e32c6684834490bb9eab87a3d5425a3','image/jpeg','/upload/f8686dec2e3840da8e53ec9c72b0155d.jpg',247267,'1518178208.jpg','2018-04-18 12:01:25'),('744fbd33cfd74b6f892ed7c16c0487ac','image/jpeg','/upload/2c60cc436b434c04b5287a30bbc54576.jpg',247267,'1518178208.jpg','2018-04-18 16:16:53'),('8d689be8cd3d4f96a2b31c6e43de48bd','image/jpeg','/upload/9ed71a1c-f9a1-469b-828a-3f18bc2140b6.jpg',361008,'1518111335.jpg','2018-04-17 00:07:03'),('8fd48c1a58e6479b9b93f445655a0e97','image/jpeg','/upload/2ebc3e7b50934b26ae0790e4f61f80ee.jpg',63182,'1518177734.jpg','2018-04-18 16:18:27'),('95e04e9a127b420cb83f30cd5fe65f76','image/jpeg','/upload/a4e02b2a352a4735b0d7d4ab7c53b5ff.jpg',443344,'1518179473.jpg','2018-04-18 16:17:36'),('993009742c004aca975fa3f992066ba5','image/jpeg','/upload/d6a1d7f89230484d8a1c9287c1de242f.jpg',56771,'1518177152.jpg','2018-04-18 15:35:22'),('a3136396dd88411ab0e021239d3a13db','image/jpeg','/upload/87957fe36127495d9fc634c03696a44f.jpg',81022,'8097160580287384.jpg','2018-04-18 00:35:48'),('a8a90a4026d04e10a6099435ff458f99','image/jpeg','/upload/9c49457b368444169e3aa603e4079438.jpg',81022,'037929624968062736.jpg','2018-04-18 00:59:39'),('b3d912205bd04a85adef5542b1727f42','image/jpeg','/upload/ff3ef38735734f01a8bf2fe86e7867a8.jpg',81022,'8595362842984873.jpg','2018-04-17 23:40:26'),('b54dd937a4f94338b8a409dfd9448eb9','image/jpeg','/upload/ba3e6608f2f24f0981a138ccca0befbe.jpg',140441,'1518178119.jpg','2018-04-18 16:18:09'),('bbcf71e4607343a59cfead538b82102a','image/jpeg','/upload/bc02af35003547e884dd74401598b704.jpg',63182,'1518177734.jpg','2018-04-18 16:17:18'),('c800d2190d854e9e980b640d9e7f9562','image/jpeg','/upload/7ad9af8128dc4759a089dc76f5f9f464.jpg',3742,'5650835265375405.jpg','2018-04-18 01:10:51'),('cb2d4040e9b0408c8e96cb81bc07ccfb','image/jpeg','/upload/a440826f14b04199a6baaec560ce9a4e.jpg',81022,'1518112177.jpg','2018-04-18 11:36:23'),('d84871e9e2ce43c88a4cd332281189eb','image/jpeg','/upload/e7654991200248cd8283cbb9aeadf5bc.jpg',27236,'1518177550.jpg','2018-04-18 15:34:00'),('dc220643e4944df7a19b4d1c88f93f7f','image/jpeg','/upload/8e95e0da50394c12b7d1eff3f0c13233.jpg',200779,'1518177635.jpg','2018-04-18 15:33:35'),('e2402c6ba6c24116975f491daf86c2b9','image/png','/upload/ef41e1e9c2aa4d8a990246baa5d477f7.png',8309,'6877738998315541.png','2018-04-18 00:50:00');

UNLOCK TABLES;

/*Table structure for table `t_userinfo` */

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `id` varchar(64) NOT NULL,
  `role_id` varchar(64) DEFAULT NULL COMMENT '用户角色',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `phone_num` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `e_mail` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(100) DEFAULT NULL COMMENT '登陆密码',
  `img_id` varchar(64) DEFAULT NULL COMMENT '头像',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别 1.男 2.女',
  `status` varchar(2) DEFAULT NULL COMMENT '状态 1.有效 2.注销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `t_userinfo` */

LOCK TABLES `t_userinfo` WRITE;

insert  into `t_userinfo`(`id`,`role_id`,`name`,`login_name`,`phone_num`,`e_mail`,`password`,`img_id`,`sex`,`status`) values ('0fd2261e8a2b423ca5cb6370d0f34cd5','7594e5d29f7111e7b41c060400ef5315','admin','admin','15524009478','1063642821@qq.com','36e6b58eab08ec367b679f08cab6c7e9e1256845e56125e40b9c7c7f4fb0692f','8d689be8cd3d4f96a2b31c6e43de48bd','1','1'),('7594e5ca9f7111e7b41c060400ef5315','7594e5d39f7111e7b41c060400ef5315','测试用户','test','111','11@qq','36e6b58eab08ec367b679f08cab6c7e9e1256845e56125e40b9c7c7f4fb0692f',NULL,'1','1');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
