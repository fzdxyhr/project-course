<template>
	<div class="front-index">
		<div class="header-div">
			<div class="divLogoImg">
				<img class="header-img" src="/static/img/front/logoA.png">
			</div>
			<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
				<el-menu-item index="1">首页</el-menu-item>
				<el-menu-item index="2">培训与认证</el-menu-item>
				<el-menu-item index="3">价格</el-menu-item>
				<el-menu-item index="4">开发者</el-menu-item>
				<el-menu-item index="5">合作伙伴</el-menu-item>
			</el-menu>
			<div class="divLogin">
				<ul class="header_ul">
					<li class="login-regist">
						<span class="btnLoginRegist">登录/注册</span>
					</li>
					<li class="activeLogin welcomeLi" style="display: none;">
						<div class="font_05d" style="position: relative;">
							<span style="color:#FFF">欢迎您：</span>
							<span class="font_05d userName"></span>
							<div class="dropdownIcon"></div>
						</div>
						<ul class="dropdown">
							<li class="accountsCtrl">账户管理</li>
							<li class="outLogin">退出登录</li>
						</ul>
					</li>
					<li class="user-control">
						<span class="toUserControl">控制台</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="carousel-content">
			<el-carousel trigger="click" height="600px" arrow="never">
				<el-carousel-item v-for="(item,index) in imgList">
					<img :src="item.url" height="600px" />
					<div v-if="index == 0" class="main">
						<h1>
							<span style="margin-right: 190px;">在线灵活搭软件</span>
							<br>
							<span style="margin-left: 135px;">管理轻松又方便</span>
						</h1>
						<p style="right: 5px;top: 310px;">一个云端账户使用所有软件功能，数据互通，从此远离碎片化</p>
					</div>
					<div v-if="index == 1" class="main">
						<h1 style="top: 215px;right: 80px;">边用边改,&nbsp;&nbsp;即改即用</h1>
						<p style="top: 325px;right: 9px;">软件功能在线实时调整，移动端实时同步，随时应对业务变化</p>
					</div>
					<div v-if="index == 2" class="main">
						<h1 style="top: 117px;right: 0;line-height: 80px;">移动端免开发<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实时同步PC端定制功能</h1>
						<p style="top: 315px;right: 235px;">原生全动态解析技术，简洁流畅灵活</p>
					</div>
					<div v-if="index == 3" class="main">
						<h1 style="top: 220px;right: 0;font-size: 55px;">让 SaaS 进入全面定制时代</h1>
						<p style="top: 325px;right: 210px;">谁说 SaaS 就只能完成简单的工作呢？</p>
					</div>
				</el-carousel-item>
			</el-carousel>
			<div class="buttons">
				<a class="btn" href="javascript:void(0);" onclick="videoPlay()">观看视频：搭搭云介绍-企业篇</a>
				<a class="btn" href="/market" target="_blank">免费体验</a>
			</div>
		</div>
		<div>
			<h2 class="index-module-title">搭搭云是什么？</h2>
			<div class="row-content">
				<el-row :gutter="20">
					<el-col :span="12">
						<div class="image">
							<img src="/static/img/front/4.png" alt="搭搭云是什么？">
						</div>
					</el-col>
					<el-col :span="12">
						<div class="text">
							搭搭云是非常灵活的企业管理超级平台，企业只要一个云端账户就可以全面管理各种业务，如行政办公、客户、销售、采购、库存、人事、财务，甚至是制造业ERP等。软件功能允许用户修改，即改即用，手机APP免开发实时同步。
							<br />
							<p style="margin-top:20px">同时，搭搭云还是一个低代码PaaS平台，在服务器端、web前端和移动端，全面帮助开发人员大幅提高开发效率、降低开发成本。
							</p>
						</div>
					</el-col>
				</el-row>
			</div>
		</div>
		<div style="background:#e8f0f9;">
			<h2 class="index-module-title">搭搭云的特点</h2>
			<div class="card-content">
				<el-row :gutter="35">
					<el-col :span="6">
						<div class="card-case">
							<div class="case-context">
								<div class="case-img">
									<img class="image" src="/static/img/front/5.png"></img>
								</div>
								<div class="case-text">
									<div class="title">功能全</div>
									<div class="description">一个云端账户内集成多种企业管理软件，软件模块之间的数据天然互通</div>
								</div>
							</div>
						</div>
					</el-col>
					<el-col :span="6">
						<div class="card-case">
							<div class="case-context">
								<div class="case-img">
									<img class="image" src="/static/img/front/6.png"></img>
								</div>
								<div class="case-text">
									<div class="title">个性化</div>
									<div class="description">所有管理软件功能都可以在标准功能基础上通过零代码配置方式进行修改、添加新功能，或者通过编程方式进行扩展、外部对接。</div>
								</div>
							</div>
						</div>
					</el-col>
					<el-col :span="6">
						<div class="card-case">
							<div class="case-context">
								<div class="case-img">
									<img class="image" src="/static/img/front/7.png"></img>
								</div>
								<div class="case-text">
									<div class="title">超灵活</div>
									<div class="description">所有软件功能可通过零代码配置方式在线实时调整，边用边改，即改即用，紧随企业变化</div>
								</div>
							</div>
						</div>
					</el-col>
					<el-col :span="6">
						<div class="card-case">
							<div class="case-context">
								<div class="case-img">
									<img class="image" src="/static/img/front/8.png"></img>
								</div>
								<div class="case-text">
									<div class="title">移动端免开发</div>
									<div class="description">电脑和手机端功能实时同步</div>
								</div>
							</div>
						</div>
					</el-col>
				</el-row>
			</div>
		</div>
		<div class="how-content">
			<h2 class="index-module-title">搭搭云能做什么？</h2>
			<div class="text-title">搭搭云帮助企业以零代码配置方式，低成本、快速地拥有专属于自己的个性化IT系统，包括：</div>
			<howContent></howContent>
		</div>
		<advantageContent></advantageContent>
		<benefitContent></benefitContent>
		<caseContent></caseContent>
		<footerContent></footerContent>
	</div>
</template>

<script>
	import header from './header.vue'
	import howContent from './howContent.vue'
	import advantageContent from './advantageContent.vue'
	import benefitContent from './benefitContent.vue'
	import caseContent from './caseContent.vue'
	import footerContent from './footerContent.vue'

	export default {
		components: {
			header,
			howContent,
			advantageContent,
			benefitContent,
			caseContent,
			footerContent
		},
		data() {
			return {
				activeIndex: "1",
				imgList: [{
					url: "/static/img/front/1.jpg"
				}, {
					url: "/static/img/front/2.jpg"
				}, {
					url: "/static/img/front/3.jpg"
				}, {
					url: "/static/img/front/4.jpg"
				}]
			};
		}
	}
</script>

<style lang="scss">
	.front-index {
		width: 100%;

		.carousel-content {
			.el-carousel__button {
				width: 148px;
				border-radius: 15px;
				height: 7px;
				opacity:1;
			}
			.el-carousel__indicator.is-active button {
				background: #5ec9ff;
			}

			.el-carousel__indicator {
				padding: 30px 4px;
			}

			.main h1 {
				color: #fff;
				position: absolute;
				right: 0;
				top: 110px;
				font-size: 50px;
				font-weight: 300;
				letter-spacing: 5px;
				line-height: 80px;
			}

			.main p {
				color: #fff;
				position: absolute;
				font-size: 19px;
				letter-spacing: 2px;
				font-weight: 200;
			}

			.buttons {
				width: 1200px;
				display: flex;
				justify-content: flex-end;
				position: absolute;
				bottom: 40px;
				left: 0;
				right: 39px;
				margin: auto;
				line-height: normal;
				font-size: 20px;
				z-index: 100;

				.btn {
					height: 60px;
					line-height: 40px;
					display: flex;
					justify-content: center;
					align-items: center;
					border: 1px solid #fff;
					color: #fff;
					margin-right: 25px;
					border-radius: 30px;
					font-size: 20px;
					letter-spacing: 1px;
					padding: 0 35px;
				}

				.btn:hover {
					background: #fff;
					color: #24085f;
				}
			}

			.btn {
				display: inline-block;
				padding: 6px 12px;
				margin-bottom: 0;
				font-size: 14px;
				font-weight: 400;
				line-height: 1.42857143;
				text-align: center;
				white-space: nowrap;
				vertical-align: middle;
				-ms-touch-action: manipulation;
				touch-action: manipulation;
				cursor: pointer;
				-webkit-user-select: none;
				-moz-user-select: none;
				-ms-user-select: none;
				user-select: none;
				background-image: none;
				border: 1px solid transparent;
				border-radius: 4px;
			}
		}

		.index-module-title {
			display: flex;
			padding: 110px 0 25px 0;
			font-size: 30px;
			line-height: normal;
			color: #5a2ac9;
			letter-spacing: 3px;
			justify-content: center;
			position: relative;
		}

		.index-module-title::after {
			content: "";
			width: 85px;
			height: 3px;
			position: absolute;
			left: 0;
			right: 0;
			bottom: 0;
			margin: auto;
			background-color: #bfbfbf;
		}

		.row-content {
			max-width: 1200px;
			margin: 50px auto 75px;

			.image {
				display: flex;
				flex: 1;
				justify-content: flex-end;
			}

			.text {
				line-height: 44px;
				font-size: 20px;
				flex: 1;
				margin: 30px 0;
				padding-left: 20px;
			}
		}

		.card-content {
			display: flex;
			max-width: 1200px;
			margin: 60px auto 0;

			.el-row {
				margin-bottom: 115px;
			}

			.card-case {
				width: 100%;
				height: 100%;
				flex: 1;
				background-image: linear-gradient(#5fc8fd, #261969);
				padding: 3px;
			}

			.case-context {
				display: flex;
				padding: 15px;
				background: #e8f0f9;
				min-height: 200px;
			}

			.case-img {
				width: 25%;
				margin-right: 5%;
				margin-top: 5px;
				float: left;
			}

			.case-text {
				width: 70%;
				float: left;
			}

			.title {
				font-weight: bolder;
				font-size: 21px;
				margin-bottom: 20px;
			}

			.description {
				color: #303030;
				font-size: 15px;
			}
		}

		.how-content {
			.text-title {
				font-size: 22px;
				max-width: 1200px;
				margin: 40px auto 0;
				color: #5a2ac9;
			}
		}

		.divLogoImg {
			min-width: 170px;
			padding-top: 24px;
			margin-right: 80px;

			.header-img {
				max-height: 60px;
				width: 100%;
				width: 170px;
				height: 40px;
			}
		}

		.divLogin {
			min-width: 400px;
			margin-left: 80px;

			.header_ul {
				height: 100px;
				list-style: none;
				font-size: 14px;
				position: relative;
			}

			.header_ul>li {
				height: 100px;
				line-height: 100px;
				float: left;
				margin: 0 10px;
				/* cursor: pointer; */
			}

			.welcomeLi {
				max-width: 180px;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 1;
				overflow: hidden;
				cursor: pointer;
			}

			.activeLogin {
				display: none;
			}

			.btnLoginRegist,
			.toUserControl {
				border-radius: 25px;
				padding: 8px 20px;
				color: #FFF;
				outline: none;
				height: 40px;
				border: 1px solid #5e6dfe;
				cursor: pointer;
			}
		}

		.header-div {
			background: #333;
			height: 100px;
			min-width: 1200px;
			display: flex;
			justify-content: center;

			.el-menu--horizontal>.el-menu-item {
				height: 100px;
				line-height: 100px;
			}

			.el-menu {
				background-color: #333;
			}

			.el-menu--horizontal>.el-menu-item.is-active {
				color: #00b0ff;
			}

			.el-menu--horizontal>.el-menu-item.is-active {
				border-bottom: 3px solid #00b0ff!important;
				background: rgba(0, 0, 0, 0.1);
				font-weight: bold;
			}

			.el-menu--horizontal>.el-menu-item:not(.is-disabled):hover {
				background: rgba(0, 0, 0, 0.1);
				color: #EEE;
			}

			.el-menu--horizontal>.el-menu-item {
				color: #EEE;
			}

			.el-menu--horizontal>.el-menu-item {
				border-bottom: 2px solid #333;
				border-bottom-color: #333!important;
			}
		}
	}
</style>
