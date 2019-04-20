<template>
	<div class="science-package-index">
		<headerContent menu="science"></headerContent>
		<div class="science-content">
			<div class="chapter-content">
				<h3>平台介绍</h3>
				<p>
					JavaFast是一款基于代码生成器的智能快速开发平台，基于代码生成器的开发方式，可以将编码工作量减少80%以上，为企业提高开发效率，为企业节省项目研发成本，减少开发周期。
					JavaFast基于流行的JAVA开源技术上构建，扩展容易，学习成本低，可快速构建企业级项目，即满足企业项目实现快速开发的需求。
				</p>
			</div>
			<div class="chapter-content">
				<h3>技术特点</h3>
				<p>
					1. 采用主流的SpringMVC + MyBatis + Apache Shiro + Jquery + Bootstrap + Ehcache + Redis + Ztree等基础架构。
					<br>
					2.
					代码生成器，通过在线配置实现一个表模型的增删改查导入导出等功能，无需一行代码。支持单表、一对多、树结构，左树右表等多种数据模型，根据表生成对应的Entity,Service,Dao,Sql,Controller,JSP等，增删改查导入导出功能生成直接使用。
					<br>
					3. 丰富的页面组件库：工具栏组件，列表组件，树形组件，弹窗组件，表单校验，文件上传等，开发维护非常高效。
					<br>
					4. 自动生成增删改通用SQL，Mybatis动态SQL可以根据条件智能生成SQL语句；支持多种匹配方式（全匹配/模糊查询/包含查询/不匹配查询）。
					<br>
					5. 强大的Excel导出导入工具封装，支持大数据量，注解定义，简单配置即可实现。
					<br>
					6. 系统支持多种风格的自动切换，可满足企业对UI的美观要求。
					<br>
					7. 前端支持手机或平板访问，响应式样式。
					<br>
					8. 支持多数据源，支持配置文件里面配置多数据源。
					<br>
					9. 支持报表在线配置，通过简单配置SQL语句即可实现图形报表展示。
					<br>
					10. 常用的工具类封装，缓存、校验、字典、组织权限等。
					<br>
					11. 操作权限控制精密细致，控制到行列级，表单字段级，按钮级，通过数据权限实现不同的人看不同数据。
					<br>
					12. 分层设计：（数据库层，数据访问层，业务逻辑层，展示层）层次清楚，低耦合，各层必须通过接口才能接入并进行参数校验（如：在展示层不可直接操作数据库），保证数据操作的安全
					<br>
					13. 适合分布式部署，云平台，移动平台开发，具有卓越的通用性、高效性、平台移植性和安全性。
					<br>
					14. 经过了专业压力测试，保证后台数据的准确性和页面访问速度
				</p>
			</div>
			<div class="chapter-content">
				<h3>安全考虑</h3>
				<p>
					1、 分层设计：（数据访问层，业务逻辑层，展示层）层次清楚，低耦合，各层必须通过接口才能接入并进行参数校验（如：在展示层不可直接操作数据库），保证数据操作的安全。
					<br>
					2、 双重验证：用户表单提交双验证：包括服务器端验证及客户端验证，防止用户通过浏览器恶意修改（如不可写文本域、隐藏变量篡改、上传非法文件等）而跳过客户端验证操作数据库。
					<br>
					3、 安全编码：用户表单提交所有数据，在服务器端都进行安全编码，防止用户提交非法脚本及SQL注入获取敏感数据等，确保数据安全。
					<br>
					4、 密码加密：登录用户密码进行SHA1散列加密，此加密方法是不可逆的。保证密文泄露后的安全问题。
					<br>
					5、 访问验证：系统对所有管理端链接都进行用户身份权限验证，防止用户直接通过URL进行未授权页面。
					<br>
					6、 数据验证：对指定数据集权限进行过滤，3种数据权限可供选择（所有权限，本部门，本人数据），可根据需要自由扩展。
				</p>
			</div>
			<div class="chapter-content">
				<h3>技术选型</h3>
				<p>
					1、后端框架
					<br>
					Spring Framework 4.0
					<br>
					MyBatis 3.2
					<br>
					Hibernate Validator 5.1
					<br>
					Apache Shiro 1.2
					<br>
					Spring Task
					<br>
					Alibaba Druid
					<br>
					Log4j
					<br>
					2、视图框架
					<br>
					Spring MVC 4.0
					<br>
					JSP
					<br>
					3、JS框架
					<br>
					JQuery 2.1
					<br>
					Layer3.0
					<br>
					jQuery Select2
					<br>
					jQuery zTree
					<br>
					My97DatePicker
					<br>
					CKEcitor
					<br>
					4、前端样式
					<br>
					Bootstrap3.0
					<br>
					H+
					<br>
					5、缓存框架
					<br>
					Ehcache 2.6
					<br>
					Redis
					<br>
					6、工具类
					<br>
					Apache Commons
					<br>
					Jackson
					<br>
					Xstream
					<br>
					Dozer
					<br>
					POI
					<br>
					7、数据库支持
					<br>
					Mysql5.5+
					<br>
					Oracle
					<br>
					MSSQL Server
					<br>
					8、开发环境
					<br>
					JDK1.7+
					<br>
					Eclipse 3+
					<br>
					Maven3+
				</p>
			</div>
			<div class="chapter-content">
				<h3>企业愿景</h3>
				<p>
					企业在项目开发的过程中，需要要写很多很多的代码，但仔细的分析来看，很多代码基本上都是重复的编写，功能大同小异。
					<br>
					快速开发平台作为一种新型的开发工具，它的出现减少了代码编写量，简化了开发过程，缩短了开发周期，提高了开发效率，节省了开发成本。越来越多的开发者使用快速开发平台来完成自己的开发工作。
				</p>
			</div>
		</div>
		<div style="clear: both;"></div>
		<footerContent></footerContent>
	</div>
</template>

<script>
	import headerContent from './headerContent.vue'
	import footerContent from './footerContent.vue'

	export default {
		components: {
			headerContent,
			footerContent
		},
		data() {
			return {

			};
		}
	}
</script>

<style lang="scss">
	.science-package-index {
		.science-content {
			width: 1200px;
			margin: 0 auto;
			clear: both;
			padding-top: 100px;

			.chapter-content {
				padding: 30px;
				margin-bottom: 30px;
				background: #fff;
				font-family: "Microsoft Yahei", "HanHei SC", PingHei, "PingFang SC", "Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
				font-weight: 400;
				box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
				color: #333;

				h3 {
					font-size: 20px;
					margin-bottom: 20px;
					line-height: 1.5;
				}

				p {
					font-size: 14px;
					line-height: 1.7;
				}
			}
		}
	}
</style>
