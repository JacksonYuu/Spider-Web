/*==============================================================*/
/* 数据库脚本                        */
/*==============================================================*/
drop database if exists recomm;
create database recomm character set utf8;
use recomm;
	
drop table if exists t_user;
create table t_user
(
  id          int not null AUTO_INCREMENT,
  username    varchar(255) not null,
  realname    varchar(255) not null,
  email       varchar(255) not null,
  password    varchar(255) not null,
  joindate    varchar(255) default null,
  lastdate    varchar(255) default null,
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into t_user(id,username,realname,email,password) values ('1', 'admin','管理员', 'admin@qq.com', 'e10adc3949ba59abbe56e057f20f883e');

/*==============================================================*/
/* 岗位                        */
/*==============================================================*/
drop table if exists t_job;
create table t_job
(
  id               int not null AUTO_INCREMENT,
  url              text default null,
  job              varchar(255) default null,
  city          varchar(255) default null,
  company             varchar(255) default null,
  salary           varchar(255) default null,
  seniority         varchar(255) default null,
  education          longtext default null,
  status           varchar(20) default null,
  createtime       varchar(20) default null,
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* 岗位-技能点-岗位个数                        */
/*==============================================================*/
drop table if exists t_jobskill;
create table t_jobskill
(
  id               int not null AUTO_INCREMENT,
  name             varchar(255) not null,
  jobskill         text default null,
  jobcounts        varchar(100) default null,
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* 岗位变化                        */
/*==============================================================*/
drop table if exists t_jobchange;
create table t_jobchange
(
  id               int not null AUTO_INCREMENT,
  name             varchar(255) not null,
  jobcounts        varchar(100) default null,
  jobdate          varchar(100) default null,
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into t_job set url='88194674.html',job='云计算大数据开发学徒助理（无经验均可）',city='广州-天河区',company='广州东紫华清信息科技有限公司',salary='3-4.5千/月',seniority='无工作经验',education='null',status='招聘2人',createtime='04-14发布';
insert into t_job set url='57664151.html',job='云计算大客户销售经理',city='成都',company='四川建华软件有限公司',salary='4.5-6千/月',seniority='2年经验',education='大专',status='招聘1人',createtime='04-14发布';
insert into t_job set url='88216021.html',job='云计算工程师-事业部',city='江苏省',company='南京嘉环科技有限公司',salary='6-8千/月',seniority='null',education='大专',status='招聘3人',createtime='04-14发布';
insert into t_job set url='81857201.html',job='云计算&大数据架构师',city='西安',company='美林数据技术股份有限公司',salary='1-2万/月',seniority='5-7年经验',education='本科',status='招聘2人',createtime='04-13发布';
insert into t_job set url='85329602.html',job='云计算中心-云计算维护岗',city='北京',company='中国移动通信有限公司政企客户分公司',salary='',seniority='null',education='null',status='招聘若干人',createtime='04-13发布';
insert into t_job set url='84481511.html',job='工业云计算研究工程师',city='北京',company='台达电子企业管理（上海）有限公司',salary='1-2万/月',seniority='2年经验',education='本科',status='招聘若干人',createtime='04-13发布';
insert into t_job set url='82361309.html',job='云计算实施工程师',city='上海-杨浦区',company='上海骞云信息科技有限公司',salary='1-2万/月',seniority='3-4年经验',education='大专',status='招聘2人',createtime='04-13发布';
insert into t_job set url='79969317.html',job='云计算架构师 (职位编号：2)',city='南京',company='江苏云途腾科技有限责任公司',salary='1.5-3万/月',seniority='5-7年经验',education='硕士',status='招聘1人',createtime='04-13发布';
insert into t_job set url='86220503.html',job='云计算支撑工程师',city='广州',company='越亮传奇科技股份有限公司',salary='0.7-1万/月',seniority='3-4年经验',education='本科',status='招聘5人',createtime='04-13发布';
insert into t_job set url='84056800.html',job='运维工程师（云存储/云计算）',city='深圳',company='华大基因',salary='1-2万/月',seniority='null',education='大专',status='招聘2人',createtime='04-12发布';
insert into t_job set url='85684911.html',job='云计算平台专家（管理信息化） (职位编号：Transfar000722)',city='杭州-萧山区',company='传化公路港物流有限公司',salary='25-50万/年',seniority='5-7年经验',education='null',status='招聘1人',createtime='04-12发布';
insert into t_job set url='86238959.html',job='云计算/网络运维/自动化脚本/Linux运维',city='廊坊',company='深圳市飞尔跃科技发展有限公司',salary='7-8千/月',seniority='3-4年经验',education='大专',status='招聘2人',createtime='04-12发布';
insert into t_job set url='85920651.html',job='Java云计算开发实习生',city='武汉-洪山区',company='武汉美软动力信息技术有限公司',salary='6.5-8千/月',seniority='null',education='null',status='招聘5人',createtime='04-12发布';
insert into t_job set url='85316708.html',job='云计算产品经理-北京（J10013）',city='北京',company='绿盟科技',salary='1.5-2.2万/月',seniority='3-4年经验',education='本科',status='招聘1人',createtime='04-12发布';
insert into t_job set url='77220231.html',job='云计算售前技术支持工程师',city='杭州-西湖区',company='北京中宇华兴科技有限公司',salary='0.8-1万/月',seniority='1年经验',education='大专',status='招聘若干人',createtime='04-12发布';
insert into t_job set url='81706265.html',job='云计算高级工程师（容器方向）',city='上海-徐汇区',company='中国太平洋保险（集团）股份有限公司',salary='10-20万/年',seniority='2年经验',education='本科',status='招聘若干人',createtime='04-12发布';
insert into t_job set url='82459802.html',job='云计算架构师',city='杭州',company='嘉善安瑞信息技术有限公司',salary='2.5-5万/月',seniority='5-7年经验',education='硕士',status='招聘2人',createtime='04-12发布';
insert into t_job set url='83613922.html',job='WEB前端工程师云计算方向',city='武汉-洪山区',company='北京数字绿土科技有限公司武汉研发中心',salary='1.8-3万/月',seniority='null',education='本科',status='招聘1人',createtime='04-12发布';
insert into t_job set url='69815029.html',job='云计算大客户经理（上海张江）',city='上海-浦东新区',company='苏州蓝海彤翔系统科技有限公司',salary='1-1.5万/月',seniority='null',education='本科',status='招聘2人',createtime='04-12发布';
insert into t_job set url='86836690.html',job='急招云计算实习生',city='武汉-洪山区',company='翰竺科技（北京)有限公司武汉办事处',salary='4.5-6千/月',seniority='null',education='大专',status='招聘10人',createtime='04-12发布';
insert into t_job set url='85519783.html',job='云计算Openstack存储研发工程师',city='北京-海淀区',company='北京同有飞骥科技股份有限公司',salary='1-1.5万/月',seniority='null',education='本科',status='招聘2人',createtime='04-12发布';
insert into t_job set url='86684880.html',job='云计算系统应用软件开发及维护工程师',city='桂林',company='深圳鑫岳电子科技有限公司',salary='3-4.5千/月',seniority='1年经验',education='本科',status='招聘2人',createtime='04-12发布';
insert into t_job set url='84693086.html',job='云计算工程师(002020) (职位编号：Aspire002020)',city='南昌',company='卓望控股有限公司',salary='0.6-1.2万/月',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='04-12发布';
insert into t_job set url='87965195.html',job='云计算大数据催收',city='郑州-郑东新区',company='郑州竞合房地产咨询有限公司',salary='3.5-7千/月',seniority='无工作经验',education='高中',status='招聘若干人',createtime='04-12发布';
insert into t_job set url='84561769.html',job='高级软件工程师（云计算）',city='广州-番禺区',company='广州市爱递思电子科技有限公司',salary='1.5-2万/月',seniority='null',education='null',status='招聘1人',createtime='04-12发布';
insert into t_job set url='87840448.html',job='Java大数据 云计算IT人员（有无经验均可d',city='青岛-黄岛区',company='青岛东软睿道教育信息技术有限公司',salary='4.5-6千/月',seniority='null',education='大专',status='招聘8人',createtime='04-12发布';
insert into t_job set url='83254343.html',job='云计算工程师（应届生）',city='上海-浦东新区',company='上海帜讯信息技术股份有限公司',salary='0.8-1.4万/月',seniority='无工作经验',education='本科',status='招聘2人',createtime='04-12发布';
insert into t_job set url='82083627.html',job='云计算讲师',city='西安',company='转点教育科技有限公司',salary='1-1.5万/月',seniority='3-4年经验',education='本科',status='招聘2人',createtime='04-12发布';
insert into t_job set url='85105182.html',job='云计算开发工程师',city='北京',company='中金数据系统有限公司',salary='1.5-2万/月',seniority='2年经验',education='大专',status='招聘2人',createtime='04-12发布';
insert into t_job set url='76160158.html',job='云计算行业解决方案专员',city='成都-锦江区',company='重庆天极网络有限公司',salary='12-18万/年',seniority='5-7年经验',education='null',status='招聘2人',createtime='04-12发布';
insert into t_job set url='83457912.html',job='云计算驻场运维合作工程师（重庆）',city='重庆-江北区',company='北京晓通网络科技有限公司',salary='6-8千/月',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='04-12发布';
insert into t_job set url='86166168.html',job='云计算Java架构师 (职位编号：HitachiCC000545)',city='深圳',company='日立咨询信息技术（广州）有限公司',salary='2-2.5万/月',seniority='8-9年经验',education='大专',status='招聘4人',createtime='04-11发布';
insert into t_job set url='86079490.html',job='云计算工程师',city='上海',company='上海仪电（集团）有限公司中央研究院',salary='1.5-2万/月',seniority='2年经验',education='本科',status='招聘若干人',createtime='04-10发布';
insert into t_job set url='83091663.html',job='云计算架构师',city='北京',company='金石易服（北京）科技有限公司',salary='2-2.5万/月',seniority='null',education='本科',status='招聘2人',createtime='04-10发布';
insert into t_job set url='86400808.html',job='云计算架构师',city='深圳-龙岗区',company='深圳市志行正科技有限公司',salary='4-5万/月',seniority='5-7年经验',education='本科',status='招聘5人',createtime='04-09发布';
insert into t_job set url='76447920.html',job='云计算架构规划运维管理（纯日资） (职位编号：JBS1603)',city='上海-浦东新区',company='杰碧思科技（上海）有限公司',salary='0.8-1万/月',seniority='3-4年经验',education='本科',status='招聘2人',createtime='04-09发布';
insert into t_job set url='76244666.html',job='高级云计算工程师',city='北京-海淀区',company='建投数据科技股份有限公司',salary='1.5-2万/月',seniority='5-7年经验',education='本科',status='招聘1人',createtime='04-07发布';
insert into t_job set url='87805147.html',job='云计算大数据、标准化工程师',city='呼和浩特',company='呼和浩特市中科院云计算中心智慧产业研究院',salary='3-4.5千/月',seniority='null',education='null',status='招聘1人',createtime='04-09发布';
insert into t_job set url='53838613.html',job='云计算工程师',city='北京',company='北京易通宏达科技股份有限公司',salary='1.5-2万/月',seniority='3-4年经验',education='大专',status='招聘若干人',createtime='04-07发布';
insert into t_job set url='79664249.html',job='云计算运维工程师',city='北京-海淀区',company='北京点石经纬科技有限公司',salary='1-1.5万/月',seniority='2年经验',education='本科',status='招聘1人',createtime='04-06发布';
insert into t_job set url='85468276.html',job='云计算实战培训课程顾问/招生顾问',city='上海-闵行区',company='大连立鑫计算机网络技术服务中心',salary='6-8千/月',seniority='null',education='null',status='招聘3人',createtime='04-06发布';
insert into t_job set url='83798942.html',job='云计算大数据讲师',city='北京-昌平区',company='北京网拓时代科技有限公司',salary='1.5-2万/月',seniority='3-4年经验',education='null',status='招聘2人',createtime='04-05发布';
insert into t_job set url='84729964.html',job='大数据云计算互联网技术总监',city='北京-海淀区',company='北京云海大业电力高科技有限公司',salary='2-2.5万/月',seniority='5-7年经验',education='null',status='招聘1人',createtime='04-05发布';
insert into t_job set url='68887276.html',job='微软云计算技术支持运维工程师operation engineer',city='北京',company='北京世纪互联宽带数据中心有限公司',salary='1-1.5万/月',seniority='null',education='大专',status='招聘10人',createtime='04-05发布';
insert into t_job set url='86410819.html',job='华为云计算工程师 存储工程师 系统工程师',city='福州',company='佳杰科技（中国）有限公司 华为安捷信产品事业部',salary='6-8千/月',seniority='1年经验',education='大专',status='招聘2人',createtime='04-04发布';
insert into t_job set url='87717075.html',job='云计算与虚拟化讲师/项目经理',city='北京-房山区',company='中经智业（北京）信息科技有限公司',salary='0.8-2.8万/月',seniority='3-4年经验',education='大专',status='招聘1人',createtime='04-04发布';
insert into t_job set url='79930754.html',job='云计算专业讲师',city='南京',company='江苏一道云科技发展有限公司无锡分公司',salary='0.7-1.5万/月',seniority='2年经验',education='本科',status='招聘2人',createtime='04-04发布';
insert into t_job set url='63841687.html',job='云计算软件开发工程师',city='天津-南开区',company='天津市网络技术研究所',salary='0.7-1.2万/月',seniority='2年经验',education='本科',status='招聘若干人',createtime='04-04发布';
insert into t_job set url='87400560.html',job='云计算安全架构师',city='北京-海淀区',company='北京吉大正元信息技术股份有限公司',salary='1.5-3万/月',seniority='null',education='null',status='招聘1人',createtime='04-03发布';
insert into t_job set url='81571294.html',job='java开发（云计算方向）',city='广州-越秀区',company='广州市申迪计算机系统有限公司',salary='0.8-1.2万/月',seniority='null',education='本科',status='招聘3人',createtime='04-03发布';
insert into t_job set url='87609244.html',job='项目经理（云计算）',city='深圳-南山区',company='中兴通讯集团财务有限公司',salary='1-2万/月',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='04-02发布';
insert into t_job set url='87526461.html',job='云计算解决方案架构师',city='上海-浦东新区',company='上海中企人力资源咨询有限公司',salary='1.5-2万/月',seniority='3-4年经验',education='null',status='招聘若干人',createtime='04-01发布';
insert into t_job set url='85419692.html',job='研发工程师（云计算Docker方向）(000481) (职位编号：zyhy000481)',city='杭州',company='中移（杭州）信息技术有限公司',salary='20-30万/年',seniority='3-4年经验',education='本科',status='招聘1人',createtime='04-01发布';
insert into t_job set url='84068282.html',job='云计算售前工程师',city='合肥',company='合肥昊邦信息科技有限公司',salary='4-8.9千/月',seniority='null',education='本科',status='招聘2人',createtime='04-01发布';
insert into t_job set url='59419878.html',job='云计算测试工程师',city='深圳-南山区',company='普联技术有限公司（TP-LINK)',salary='1-1.5万/月',seniority='1年经验',education='本科',status='招聘若干人',createtime='04-01发布';
insert into t_job set url='79460457.html',job='云计算方向专业教师',city='广州',company='广州白云工商高级技工学校',salary='0.4-1万/月',seniority='null',education='本科',status='招聘2人',createtime='03-31发布';
insert into t_job set url='85998181.html',job='云计算解决方案和咨询工程师（北京）(J10120)',city='北京-西城区',company='中移（苏州）软件技术有限公司',salary='1.5-2万/月',seniority='null',education='null',status='招聘若干人',createtime='03-30发布';
insert into t_job set url='87222916.html',job='云计算和大数据产品运维专家（杭州）',city='杭州-西湖区',company='杭州魔控科技有限公司',salary='0.8-1万/月',seniority='3-4年经验',education='本科',status='招聘5人',createtime='03-30发布';
insert into t_job set url='79372696.html',job='容器云计算架构师',city='济南-高新区',company='北京森实科技有限公司',salary='1.2-2.4万/月',seniority='5-7年经验',education='本科',status='招聘5人',createtime='03-30发布';
insert into t_job set url='87083324.html',job='云计算开发工程师',city='上海-浦东新区',company='上海达龙信息科技有限公司',salary='0.8-1万/月',seniority='2年经验',education='本科',status='招聘2人',createtime='03-30发布';
insert into t_job set url='78508954.html',job='政务云计算方案设计高级工程师',city='青岛',company='海信网络科技公司',salary='1-1.5万/月',seniority='3-4年经验',education='本科',status='招聘3人',createtime='03-31发布';
insert into t_job set url='82438793.html',job='云计算开发工程师',city='北京-海淀区',company='中国科学院计算机网络信息中心',salary='0.8-1.2万/月',seniority='3-4年经验',education='硕士',status='招聘1人',createtime='03-30发布';
insert into t_job set url='80357450.html',job='讲师（云计算）',city='南京-栖霞区',company='南京雨花中兴信雅达培训中心',salary='6-8千/月',seniority='3-4年经验',education='本科',status='招聘2人',createtime='03-29发布';
insert into t_job set url='78545913.html',job='高级软件工程师（云计算）',city='武汉-洪山区',company='国家数控系统工程技术研究中心',salary='6-8千/月',seniority='null',education='null',status='招聘4人',createtime='03-29发布';
insert into t_job set url='87592717.html',job='HY2-云计算开发工程师（深圳） (职位编号：29414)',city='深圳',company='深圳市腾讯计算机系统有限公司',salary='',seniority='3-4年经验',education='本科',status='招聘1人',createtime='03-29发布';
insert into t_job set url='85788014.html',job='销售经理(云计算、IDC)',city='广州',company='广东英拓旗网科技有限公司',salary='0.8-1万/月',seniority='3-4年经验',education='大专',status='招聘2人',createtime='03-29发布';
insert into t_job set url='70086156.html',job='云计算工程师',city='苏州',company='江苏天创科技有限公司',salary='3-4.5千/月',seniority='1年经验',education='本科',status='招聘1人',createtime='03-28发布';
insert into t_job set url='86952318.html',job='Project Analyst/云计算开发工程师',city='上海',company='马衡达信息技术（上海）有限公司',salary='0.8-1万/月',seniority='null',education='null',status='招聘若干人',createtime='03-28发布';
insert into t_job set url='78241550.html',job='云计算工程师',city='郑州',company='郑州旭林电子科技有限公司',salary='3.5-6.5千/月',seniority='null',education='本科',status='招聘3人',createtime='03-28发布';
insert into t_job set url='82278804.html',job='云计算 高级研发工程师',city='沈阳-东陵区（浑南新区）',company='沈阳东软睿道教育服务有限公司',salary='1-1.5万/月',seniority='5-7年经验',education='本科',status='招聘1人',createtime='03-27发布';
insert into t_job set url='86075908.html',job='资深云计算运维工程师',city='上海',company='盛大游戏',salary='1.5-2万/月',seniority='null',education='null',status='招聘3人',createtime='03-27发布';
insert into t_job set url='83221919.html',job='云计算开发实习生双休+五险一金',city='西安-雁塔区',company='西安网星软件咨询服务有限公司',salary='6-8千/月',seniority='无工作经验',education='本科',status='招聘5人',createtime='03-27发布';
insert into t_job set url='85640588.html',job='云计算全栈工程师',city='上海-徐汇区',company='慧捷（上海）信息技术有限公司',salary='1.5-2万/月',seniority='5-7年经验',education='本科',status='招聘若干人',createtime='03-25发布';
insert into t_job set url='71339082.html',job='云计算产品实施工程师',city='杭州',company='城云科技（中国）有限公司',salary='3-4.5千/月',seniority='无工作经验',education='大专',status='招聘2人',createtime='03-25发布';
insert into t_job set url='87272553.html',job='Linux系统工程师（云计算）',city='上海-杨浦区',company='上海沃帆信息科技有限公司',salary='0.8-1万/月',seniority='2年经验',education='本科',status='招聘2人',createtime='03-25发布';
insert into t_job set url='73508536.html',job='云计算工程师',city='北京',company='北京博奥晶典生物技术有限公司',salary='0.8-1万/月',seniority='null',education='本科',status='招聘若干人',createtime='03-24发布';
insert into t_job set url='83437052.html',job='资深架构师（云计算）',city='杭州',company='帆诗（上海）企业管理咨询有限公司',salary='50-60万/年',seniority='5-7年经验',education='null',status='招聘2人',createtime='03-24发布';
insert into t_job set url='87097097.html',job='存储、云计算工程师',city='南京',company='北京瑞得音信息技术有限公司',salary='0.6-1.2万/月',seniority='2年经验',education='大专',status='招聘1人',createtime='03-24发布';
insert into t_job set url='86339235.html',job='云计算销售经理/电话销售/互联网销售/客户经理',city='深圳-宝安区',company='深圳久云科技有限公司',salary='0.6-1.5万/月',seniority='3-4年经验',education='null',status='招聘2人',createtime='03-24发布';
insert into t_job set url='49584009.html',job='云计算高级系统开发工程师',city='嘉兴',company='浙江清华长三角研究院',salary='0.8-1万/月',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='03-24发布';
insert into t_job set url='84698372.html',job='云计算解决方案工程师',city='天津-西青区',company='天津滨海迅腾科技集团有限公司',salary='4-5万/年',seniority='2年经验',education='本科',status='招聘若干人',createtime='03-23发布';
insert into t_job set url='82277587.html',job='9.电信恒联-云计算售前工程师',city='上海-徐汇区',company='上海临港漕河泾人才有限公司',salary='0.8-1万/月',seniority='null',education='null',status='招聘2人',createtime='03-23发布';
insert into t_job set url='85068857.html',job='云计算/大数据系统研究工程师',city='苏州-高新区',company='苏州中晟宏芯信息科技有限公司',salary='10-50万/年',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='03-22发布';
insert into t_job set url='85350251.html',job='云计算服务运营工程师',city='上海-浦东新区',company='上海肯耐珂萨人才服务股份有限公司',salary='15-35万/年',seniority='2年经验',education='本科',status='招聘5人',createtime='03-22发布';
insert into t_job set url='74225086.html',job='云计算虚拟化工程师',city='郑州',company='郑州市景安网络科技股份有限公司',salary='4-8千/月',seniority='null',education='大专',status='招聘3人',createtime='03-22发布';
insert into t_job set url='85859929.html',job='高级项目经理（云计算）',city='北京-西城区',company='天闻数媒科技有限公司',salary='1.3-2.5万/月',seniority='null',education='本科',status='招聘1人',createtime='03-22发布';
insert into t_job set url='67475140.html',job='高级软件工程师-云计算',city='杭州-西湖区',company='浙江立元通信技术股份有限公司',salary='0.5-1.8万/月',seniority='2年经验',education='本科',status='招聘若干人',createtime='03-22发布';
insert into t_job set url='84454617.html',job='openstack云计算工程师',city='成都',company='成都市思叠科技有限公司',salary='5-8千/月',seniority='null',education='本科',status='招聘1人',createtime='03-21发布';
insert into t_job set url='87096545.html',job='测试工程师（云计算）',city='武汉-洪山区',company='达沃时代科技（武汉）有限公司',salary='8-15万/年',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='03-20发布';
insert into t_job set url='85304508.html',job='云计算销售人员',city='大连-沙河口区',company='大连神州云融合网络股份有限公司',salary='3-4.5千/月',seniority='1年经验',education='本科',status='招聘1人',createtime='03-21发布';
insert into t_job set url='86960267.html',job='云计算技术总监',city='北京-西城区',company='中国电信集团系统集成有限责任公司',salary='50-60万/年',seniority='8-9年经验',education='本科',status='招聘1人',createtime='03-20发布';
insert into t_job set url='69094116.html',job='云计算软件架构师',city='杭州-滨江区',company='信雅达系统工程股份有限公司',salary='0.8-1万/月',seniority='null',education='本科',status='招聘1人',createtime='03-16发布';
insert into t_job set url='77635495.html',job='云计算运维工程师',city='青岛',company='海尔集团公司',salary='10-15万/年',seniority='5-7年经验',education='本科',status='招聘1人',createtime='03-16发布';
insert into t_job set url='79847446.html',job='移动事业部_云计算高级软件架构师 (职位编号：000842)',city='北京-东城区',company='亚信科技（中国）有限公司',salary='2.5-3万/月',seniority='null',education='本科',status='招聘若干人',createtime='03-16发布';
insert into t_job set url='73044282.html',job='云计算研发工程师',city='成都-双流县',company='中国电子科技网络信息安全有限公司',salary='0.8-1.8万/月',seniority='2年经验',education='本科',status='招聘6人',createtime='03-15发布';
insert into t_job set url='86853482.html',job='高级销售经理-政务方向-云计算',city='济南',company='京东集团总部',salary='3-4万/月',seniority='8-9年经验',education='本科',status='招聘2人',createtime='03-15发布';
insert into t_job set url='84869954.html',job='企业云计算-大客户经理（上海）',city='上海-闸北区',company='北京荣之联科技股份有限公司',salary='0.8-1.6万/月',seniority='null',education='本科',status='招聘1人',createtime='03-14发布';
insert into t_job set url='85957323.html',job='系统运维工程师（云计算方向）',city='北京-海淀区',company='工信通（北京）信息技术有限公司',salary='0.8-1万/月',seniority='3-4年经验',education='本科',status='招聘1人',createtime='03-14发布';
insert into t_job set url='70902114.html',job='云计算产品销售部经理',city='深圳',company='中国智慧城市研究院',salary='6-8千/月',seniority='3-4年经验',education='本科',status='招聘2人',createtime='03-13发布';
insert into t_job set url='75892475.html',job='云计算数据中心技术总监',city='青岛',company='山东华高控股有限公司',salary='1-1.5万/月',seniority='8-9年经验',education='本科',status='招聘1人',createtime='03-13发布';
insert into t_job set url='63496628.html',job='实习生（云计算软件研发方向）',city='广州-天河区',company='广州云岫信息科技有限公司',salary='3-4.5千/月',seniority='null',education='本科',status='招聘4人',createtime='03-12发布';
insert into t_job set url='82178739.html',job='大数据和云计算研发工程师',city='苏州-姑苏区',company='苏州市测绘院有限责任公司',salary='15-20万/年',seniority='2年经验',education='本科',status='招聘2人',createtime='03-11发布';
insert into t_job set url='82950831.html',job='云计算电话销售/绩效奖金+各种补贴',city='大连-高新园区',company='广州欢创劳务派遣有限公司',salary='4.5-6.5千/月',seniority='3-4年经验',education='大专',status='招聘12人',createtime='03-11发布';
insert into t_job set url='81782691.html',job='云计算运维工程师',city='成都-高新区',company='四川虹微技术有限公司',salary='0.8-1万/月',seniority='3-4年经验',education='本科',status='招聘2人',createtime='03-10发布';
insert into t_job set url='80804583.html',job='云计算运维工程师',city='绵阳',company='四川长虹电器股份有限公司',salary='0.8-1万/月',seniority='3-4年经验',education='本科',status='招聘2人',createtime='03-10发布';
insert into t_job set url='85023449.html',job='数据中心云计算工程师',city='武汉',company='武汉烽火众智数字技术有限责任公司',salary='6-8千/月',seniority='null',education='本科',status='招聘1人',createtime='03-09发布';
insert into t_job set url='75875538.html',job='售前工程师--云计算方向',city='北京',company='北京联创信安科技股份有限公司',salary='1-1.5万/月',seniority='3-4年经验',education='null',status='招聘1人',createtime='03-07发布';
insert into t_job set url='83231160.html',job='云计算&虚拟化售前技术专家',city='上海',company='上海商众科技有限公司',salary='1-1.5万/月',seniority='5-7年经验',education='大专',status='招聘2人',createtime='03-07发布';
insert into t_job set url='86530837.html',job='物联网/云计算网络高级软件工程师',city='东莞',company='深圳创想信通科技有限公司',salary='1-1.5万/月',seniority='null',education='本科',status='招聘若干人',createtime='03-07发布';
insert into t_job set url='81823505.html',job='云计算开发工程师',city='上海',company='上海网达软件股份有限公司',salary='2-2.5万/月',seniority='null',education='本科',status='招聘若干人',createtime='03-03发布';
insert into t_job set url='85393042.html',job='云计算架构师',city='深圳-南山区',company='中兴通讯股份有限公司',salary='1.5-2万/月',seniority='null',education='本科',status='招聘1人',createtime='03-02发布';
insert into t_job set url='81176773.html',job='云计算工程师',city='上海',company='上海南天电脑系统有限公司',salary='1-1.5万/月',seniority='null',education='本科',status='招聘1人',createtime='03-02发布';
insert into t_job set url='84601563.html',job='云计算工程师',city='武汉',company='武汉美斯坦福信息技术有限公司',salary='1-1.5万/月',seniority='3-4年经验',education='null',status='招聘2人',createtime='03-02发布';
insert into t_job set url='72013582.html',job='高级云计算工程师',city='广州',company='广州迅科数码科技有限公司',salary='0.8-1万/月',seniority='null',education='本科',status='招聘1人',createtime='03-01发布';
insert into t_job set url='86198676.html',job='云计算文案策划',city='武汉-武昌区',company='武汉天楚云计算有限公司',salary='4.5-6千/月',seniority='null',education='本科',status='招聘若干人',createtime='02-28发布';
insert into t_job set url='78954967.html',job='云计算工程师-lenovo ms',city='上海-浦东新区',company='北京金蓝人力资源服务有限公司',salary='0.8-1.2万/月',seniority='3-4年经验',education='null',status='招聘1人',createtime='02-27发布';
insert into t_job set url='85474764.html',job='云计算架构师',city='深圳-龙岗区',company='比亚迪精密制造有限公司',salary='1.5-3万/月',seniority='3-4年经验',education='null',status='招聘2人',createtime='02-27发布';
insert into t_job set url='75538573.html',job='云计算大数据业务分析工程师',city='西安-雁塔区',company='陕西北斗金控信息服务有限公司',salary='0.8-1.5万/月',seniority='null',education='null',status='招聘2人',createtime='02-27发布';
insert into t_job set url='86100752.html',job='云计算开发高级工程师/云运维开发高级工程师',city='杭州',company='华为技术有限公司',salary='1.5-2.5万/月',seniority='2年经验',education='本科',status='招聘若干人',createtime='02-26发布';
insert into t_job set url='85394025.html',job='客户经理(高性能、云计算方向)',city='北京',company='北京石竹科技股份有限公司',salary='6-8千/月',seniority='null',education='本科',status='招聘1人',createtime='02-24发布';
insert into t_job set url='84852977.html',job='云计算工程',city='广州-天河区',company='广州极客优信息技术有限公司',salary='1.5-3万/月',seniority='null',education='null',status='招聘若干人',createtime='02-24发布';
insert into t_job set url='82125047.html',job='云计算开发工程师',city='南京',company='南京富士通南大软件技术有限公司',salary='10-15万/年',seniority='1年经验',education='本科',status='招聘2人',createtime='02-24发布';
insert into t_job set url='83981554.html',job='云计算产品经理(003705) (职位编号：suning003705)',city='南京',company='苏宁云商集团股份有限公司-IT总部',salary='1-2万/月',seniority='3-4年经验',education='本科',status='招聘1人',createtime='02-24发布';
insert into t_job set url='85424380.html',job='云计算安全工程师',city='上海',company='上海盛霄云计算技术有限公司',salary='1.6-2.5万/月',seniority='3-4年经验',education='大专',status='招聘1人',createtime='02-23发布';
insert into t_job set url='81956807.html',job='云计算研发工程师',city='西安',company='中国长城互联网',salary='1.5-2万/月',seniority='null',education='null',status='招聘2人',createtime='02-24发布';
insert into t_job set url='78109013.html',job='云计算产品经理',city='杭州',company='杭州炫阳科技有限公司',salary='4.5-6千/月',seniority='null',education='null',status='招聘3人',createtime='02-23发布';
insert into t_job set url='84872270.html',job='云计算讲师',city='北京',company='北京西普阳光教育科技股份有限公司',salary='1.5-2万/月',seniority='null',education='本科',status='招聘2人',createtime='02-22发布';
insert into t_job set url='84691973.html',job='大数据开发工程师',city='南京-鼓楼区',company='南京真云计算科技有限公司',salary='1-2万/月',seniority='null',education='null',status='招聘2人',createtime='04-14发布';
insert into t_job set url='88103337.html',job='java开发工程师（信息化方向）',city='深圳-南山区',company='深圳市铱云云计算有限公司',salary='1-2万/月',seniority='3-4年经验',education='大专',status='招聘若干人',createtime='04-14发布';
insert into t_job set url='87622301.html',job='运维部署工程师',city='南京',company='杭州云嘉云计算有限公司',salary='0.8-1.4万/月',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='04-14发布';
insert into t_job set url='85805646.html',job='java工程师',city='杭州',company='浙江银江云计算技术有限公司',salary='0.8-1.6万/月',seniority='2年经验',education='本科',status='招聘若干人',createtime='04-14发布';
insert into t_job set url='83218450.html',job='运维工程师',city='广州-天河区',company='广州擎云计算机科技有限公司',salary='0.6-1万/月',seniority='null',education='本科',status='招聘5人',createtime='04-14发布';
insert into t_job set url='87763907.html',job='中级java工程师',city='深圳-福田区',company='广东华邦云计算股份有限公司',salary='1.5-2万/月',seniority='null',education='本科',status='招聘5人',createtime='04-13发布';
insert into t_job set url='88167106.html',job='高级Java软件工程师（云平台）',city='武汉',company='立得空间信息技术股份有限公司',salary='1-1.5万/月',seniority='5-7年经验',education='null',status='招聘1人',createtime='04-14发布';
insert into t_job set url='85425818.html',job='大数据工程师',city='广州',company='北京中凯信通信息技术有限公司',salary='0.8-1.2万/月',seniority='null',education='大专',status='招聘1人',createtime='02-27发布';
insert into t_job set url='85425501.html',job='大数据工程师',city='北京',company='北京中凯信通信息技术有限公司',salary='0.8-1.2万/月',seniority='null',education='大专',status='招聘1人',createtime='02-27发布';
insert into t_job set url='85361932.html',job='系统运维工程师',city='南京-雨花台区',company='上海动米网络科技有限公司',salary='4.5-6千/月',seniority='1年经验',education='大专',status='招聘若干人',createtime='02-27发布';
insert into t_job set url='85329110.html',job='大学讲师（java方向）',city='杭州',company='北京华晟经世信息技术有限公司',salary='0.8-1万/月',seniority='null',education='null',status='招聘1人',createtime='02-27发布';
insert into t_job set url='75580484.html',job='高级运维工程师',city='西安-雁塔区',company='陕西北斗金控信息服务有限公司',salary='5-8千/月',seniority='null',education='null',status='招聘2人',createtime='02-27发布';
insert into t_job set url='51086791.html',job='咨询设计师(建筑智能化、信息系统集成与服务类)',city='南昌',company='思创数码科技股份有限公司',salary='6-8千/月',seniority='1年经验',education='本科',status='招聘若干人',createtime='02-27发布';
insert into t_job set url='71550668.html',job='运维工程师',city='郑州-二七区',company='河南优德大药房连锁有限公司',salary='3-4.5千/月',seniority='null',education='大专',status='招聘2人',createtime='02-25发布';
insert into t_job set url='75482542.html',job='系统资深工程师',city='上海-浦东新区',company='台达电子企业管理（上海）有限公司',salary='',seniority='null',education='大专',status='招聘若干人',createtime='02-24发布';
insert into t_job set url='78914169.html',job='虚拟网络开发工程师(数据面方向)(001990) (职位编号：suning001990)',city='南京',company='苏宁云商集团股份有限公司-IT总部',salary='1.5-2万/月',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='02-24发布';
insert into t_job set url='80083090.html',job='安全开发工程师（云安全）(002351) (职位编号：suning002351)',city='南京',company='苏宁云商集团股份有限公司-IT总部',salary='1.5-3万/月',seniority='3-4年经验',education='本科',status='招聘若干人',createtime='02-24发布';
insert into t_job set url='83473680.html',job='docker工程师(003590) (职位编号：suning003590)',city='南京',company='苏宁云商集团股份有限公司-IT总部',salary='1-2万/月',seniority='3-4年经验',education='本科',status='招聘1人',createtime='02-24发布';
insert into t_job set url='84584271.html',job='大数据项目助理/产品助理',city='广州-天河区',company='广东广业开元科技有限公司',salary='0.6-1万/月',seniority='2年经验',education='本科',status='招聘1人',createtime='02-24发布';
insert into t_job set url='83583585.html',job='java讲师/java项目经理',city='遵义',company='贵州海文信息技术有限公司',salary='5-9千/月',seniority='null',education='大专',status='招聘3人',createtime='02-24发布';
insert into t_job set url='67258567.html',job='java中级软件工程师',city='西安',company='中国长城互联网',salary='6-8千/月',seniority='null',education='null',status='招聘15人',createtime='02-24发布';
insert into t_job set url='78653626.html',job='云端产品方案专家-Internet+(北京)',city='北京',company='中企网络通信技术有限公司',salary='1.5-2.5万/月',seniority='null',education='null',status='招聘若干人',createtime='02-24发布';
insert into t_job set url='78652943.html',job='软件架构师-Internet+(北京)',city='北京',company='中企网络通信技术有限公司',salary='1.5-2.5万/月',seniority='null',education='null',status='招聘若干人',createtime='02-24发布';
insert into t_job set url='75530780.html',job='云端方案架构师（北京） (职位编号：994)',city='北京',company='中企网络通信技术有限公司',salary='1.5-2万/月',seniority='5-7年经验',education='大专',status='招聘若干人',createtime='02-24发布';
insert into t_job set url='80090208.html',job='桌面云研发工程师',city='北京',company='北京易讯通信息技术股份有限公司',salary='1-1.5万/月',seniority='null',education='null',status='招聘若干人',createtime='02-23发布';
insert into t_job set url='80090136.html',job='云平台研发工程师',city='北京',company='北京易讯通信息技术股份有限公司',salary='1-1.5万/月',seniority='null',education='null',status='招聘若干人',createtime='02-23发布';
insert into t_job set url='74104670.html',job='云安全研发工程师--第四事业部',city='北京-西城区',company='长安通信科技有限责任公司',salary='20-30万/年',seniority='null',education='null',status='招聘若干人',createtime='02-23发布';
insert into t_job set url='84914077.html',job='物联网平台软件架构师',city='南京-雨花台区',company='中兴通讯股份有限公司',salary='1-1.5万/月',seniority='null',education='null',status='招聘2人',createtime='02-23发布';
insert into t_job set url='84914076.html',job='物联网平台软件架构师',city='南京-雨花台区',company='中兴通讯股份有限公司',salary='1-1.5万/月',seniority='null',education='null',status='招聘2人',createtime='02-23发布';
insert into t_job set url='76600275.html',job='架构师',city='重庆',company='重庆同远能源技术有限公司',salary='1-1.5万/月',seniority='null',education='null',status='招聘2人',createtime='02-22发布';
insert into t_job set url='77115518.html',job='系统集成部经理 (职位编号：1)',city='珠海',company='广东亿网通电子商务发展有限公司',salary='20-30万/年',seniority='5-7年经验',education='大专',status='招聘1人',createtime='02-22发布';
insert into t_job set url='84957714.html',job='大数据开发工程师（JAVA） 华网',city='杭州',company='杭州天卓网络有限公司',salary='1.5-2万/月',seniority='3-4年经验',education='本科',status='招聘2人',createtime='02-21发布';
insert into t_job set url='79448980.html',job='软件架构师',city='杭州-余杭区',company='中电海康集团有限公司',salary='30-50万/年',seniority='null',education='本科',status='招聘1人',createtime='02-19发布';
insert into t_job set url='87085975.html',job='云计算/大数据开发工程师助理',city='大连-沙河口区',company='大连中科云信息技术有限公司',salary='3-4.5千/月',seniority='null',education='中专',status='招聘10人',createtime='04-14发布';
insert into t_job set url='87560160.html',job='云计算开发工程师（大数据平台）-技术储备岗',city='沈阳',company='大连摩比维迪视频系统有限公司',salary='3-5千/月',seniority='null',education='大专',status='招聘7人',createtime='04-14发布';
insert into t_job set url='76523778.html',job='大数据、云计算方向的讲师or咨询顾问',city='北京-海淀区',company='北京火龙果软件工程技术中心',salary='5-7万/月',seniority='null',education='null',status='招聘5人',createtime='04-14发布';
insert into t_job set url='76523775.html',job='大数据、云计算方向的讲师or咨询顾问',city='北京-海淀区',company='北京火龙果软件工程技术中心',salary='5-7万/月',seniority='null',education='null',status='招聘5人',createtime='04-14发布';
insert into t_job set url='86732559.html',job='云计算行业解决方案架构师-杭州研究院849 (职位编号：J175241)',city='杭州',company='网易集团',salary='5-7万/月',seniority='3-4年经验',education='本科',status='招聘10人',createtime='04-14发布';
insert into t_job set url='86732558.html',job='云计算行业线客户经理-杭州研究院849 (职位编号：J175240)',city='杭州',company='网易集团',salary='5-7万/月',seniority='3-4年经验',education='本科',status='招聘20人',createtime='04-14发布';
insert into t_job set url='84859732.html',job='云计算事业部_销售助理实习生 (职位编号：baidu015442)',city='北京-海淀区',company='百度在线网络技术（北京）有限公司',salary='100元/天',seniority='null',education='null',status='招聘若干人',createtime='04-13发布';
insert into t_job set url='77768859.html',job='WEB开发工程师（云计算方向）',city='沈阳',company='淘车网络科技有限公司',salary='2-4千/月',seniority='无工作经验',education='大专',status='招聘7人',createtime='04-13发布';
insert into t_job set url='69500622.html',job='云计算产品研发工程师',city='上海-浦东新区',company='上海有云信息技术有限公司',salary='2-3千/月',seniority='null',education='null',status='招聘若干人',createtime='04-13发布';
insert into t_job set url='87348830.html',job='云计算/大数据开发工程师助理',city='大连',company='大连摩比维迪视频系统有限公司',salary='3-4.5千/月',seniority='null',education='中专',status='招聘10人',createtime='04-13发布';
insert into t_job set url='69722185.html',job='产品经理（云计算企业级应用）',city='贵阳',company='北京讯鸟软件有限公司',salary='1.2+ 千/月',seniority='2年经验',education='本科',status='招聘1人',createtime='04-10发布';
insert into t_job set url='87134878.html',job='零基础 云计算开发工程师（大数据平台）',city='西安',company='北京优才创智科技有限公司西安分公司',salary='4-6千/月',seniority='1年经验',education='大专',status='招聘5人',createtime='04-13发布';
insert into t_job set url='86228601.html',job='云计算高级咨询顾问(007775) (职位编号：DCITS007775)',city='上海-长宁区',company='神州数码系统集成服务有限公司上海分公司',salary='1千以下/年',seniority='8-9年经验',education='本科',status='招聘1人',createtime='04-01发布';
insert into t_job set url='86735177.html',job='OpenStack技术支持工程师 （云计算 软件研发）',city='北京-西城区',company='中国电信集团系统集成有限责任公司',salary='1-2万/年',seniority='1年经验',education='本科',status='招聘5人',createtime='03-20发布';
insert into t_job set url='83318654.html',job='云计算实施工程师',city='深圳',company='宝德科技集团股份有限公司',salary='1.5千以下/月',seniority='null',education='本科',status='招聘若干人',createtime='03-02发布';
insert into t_job set url='78954746.html',job='云计算工程师-lenovo ms',city='杭州-滨江区',company='北京金蓝人力资源服务有限公司',salary='1千以下/月',seniority='3-4年经验',education='null',status='招聘1人',createtime='02-27发布';
insert into t_job set url='72955375.html',job='大型上市集团 智慧城市云计算架构师',city='上海',company='上海英格人才服务有限公司',salary='30-40万/年',seniority='null',education='null',status='招聘1人',createtime='04-13发布';
insert into t_job set url='72861392.html',job='云计算事业部（子公司）总经理',city='上海',company='上海英格人才服务有限公司',salary='40+ 万/年',seniority='8-9年经验',education='本科',status='招聘1人',createtime='04-06发布';
insert into t_job set url='79177879.html',job='高级java开发工程师',city='福州',company='福州艾游网络技术有限公司',salary='1.5千以下/月',seniority='null',education='大专',status='招聘3人',createtime='04-14发布';
insert into t_job set url='83783829.html',job='实习销售专员（云产品）',city='福州',company='福州靠谱云科技有限公司',salary='1.5-3千/月',seniority='null',education='本科',status='招聘若干人',createtime='04-14发布';
insert into t_job set url='81607565.html',job='高级系统架构师',city='杭州-拱墅区',company='杭州杭测信息技术有限公司',salary='1-5千/月',seniority='5-7年经验',education='本科',status='招聘1人',createtime='04-14发布';
insert into t_job set url='80438785.html',job='运维经理',city='上海',company='山东浪潮云服务信息科技有限公司',salary='4-8千/月',seniority='5-7年经验',education='本科',status='招聘1人',createtime='04-14发布';
insert into t_job set url='88022528.html',job='基础架构师',city='广州-天河区',company='广东网金控股股份有限公司',salary='15-30万/月',seniority='null',education='本科',status='招聘1人',createtime='04-14发布';
insert into t_job set url='69568734.html',job='大数据开发工程师',city='成都',company='成都天心悦高科技发展有限公司',salary='3-4.5千/月',seniority='null',education='本科',status='招聘若干人',createtime='04-13发布';
insert into t_job set url='86009933.html',job='运维值班工程师',city='南京-江宁区',company='江苏曙光信息技术有限公司',salary='1.5-2千/月',seniority='无工作经验',education='大专',status='招聘若干人',createtime='04-13发布';
insert into t_job set url='87980705.html',job='系统工程师',city='上海',company='上海晶芮信息科技有限公司',salary='0.1-2万/月',seniority='2年经验',education='大专',status='招聘5人',createtime='04-13发布';
insert into t_job set url='83524455.html',job='java开发工程师',city='西安',company='软通动力信息技术（集团）有限公司',salary='0.8-1万/月',seniority='1年经验',education='本科',status='招聘40人',createtime='04-14发布';
insert into t_job set url='79211878.html',job='Java面向大数据SOA工程师-SSE高级工程师/TL项目组长',city='西安',company='埃森哲信息技术（大连）有限公司',salary='',seniority='null',education='大专',status='招聘若干人',createtime='04-11发布';
insert into t_job set url='78659888.html',job='java开发工程师（西安职位）',city='西安',company='软通动力信息技术（集团）有限公司',salary='6-8千/月',seniority='1年经验',education='本科',status='招聘40人',createtime='03-30发布';
insert into t_job set url='78660349.html',job='运维工程师（西安职位）',city='西安',company='软通动力信息技术（集团）有限公司',salary='6-8千/月',seniority='1年经验',education='本科',status='招聘40人',createtime='03-30发布';
insert into t_job set url='78396591.html',job='架构师',city='武汉',company='软通动力信息技术（集团）有限公司',salary='0.9-1.5万/月',seniority='null',education='null',status='招聘若干人',createtime='03-30发布';
insert into t_job set url='78309040.html',job='高级系统架构师',city='北京',company='软通动力信息技术（集团）有限公司',salary='1-1.5万/月',seniority='5-7年经验',education='大专',status='招聘1人',createtime='03-30发布';
insert into t_job set url='77329850.html',job='高级系统工程师（非外包）',city='北京',company='软通动力信息技术（集团）有限公司',salary='6-8千/月',seniority='3-4年经验',education='大专',status='招聘1人',createtime='03-30发布';
insert into t_job set url='85564381.html',job='java开发高级工程师-上海（应用软件）',city='上海-徐汇区',company='锐捷网络股份有限公司',salary='1.5-2万/月',seniority='2年经验',education='大专',status='招聘6人',createtime='03-27发布';
insert into t_job set url='85115166.html',job='云平台架构师',city='福州',company='锐捷网络股份有限公司',salary='1.5-2.5万/月',seniority='5-7年经验',education='本科',status='招聘若干人',createtime='03-27发布';
insert into t_job set url='85115165.html',job='云平台架构师',city='福州',company='锐捷网络股份有限公司',salary='1.5-2.5万/月',seniority='5-7年经验',education='本科',status='招聘若干人',createtime='03-27发布';
insert into t_job set url='85115164.html',job='云平台架构师',city='福州',company='锐捷网络股份有限公司',salary='1.5-2.5万/月',seniority='5-7年经验',education='本科',status='招聘若干人',createtime='03-27发布';
insert into t_job set url='84072846.html',job='云数据中心方案架构师',city='北京',company='锐捷网络股份有限公司',salary='3.5-6万/月',seniority='null',education='null',status='招聘若干人',createtime='02-22发布';
insert into t_job set url='85115225.html',job='测试开发工程师（云数据中心）',city='福州',company='锐捷网络股份有限公司',salary='1-1.5万/月',seniority='null',education='本科',status='招聘若干人',createtime='02-22发布';
insert into t_job set url='85115163.html',job='云平台架构师',city='福州',company='锐捷网络股份有限公司',salary='1.5-2.5万/月',seniority='5-7年经验',education='本科',status='招聘若干人',createtime='02-22发布';