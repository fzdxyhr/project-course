<template>
	<div class="index-how-content">
		<el-row :gutter="20">
			<el-col :span="12">
				<div class="item clearfix">
					<div class="img">
						<img src="/static/img/front/9.png" alt="企业内部管理系统">
					</div>
					<div class="item-text">
						<div class="name">企业内部管理系统</div>
						<ul class="detail ie10">
							<li class="ie10">
								<div class="text">自动化办公OA</div>
							</li>
							<li class="ie10">
								<div class="text">客户关系管理CRM</div>
							</li>
							<li class="ie10">
								<div class="text">人力资源HR</div>
							</li>
							<li class="ie10">
								<div class="text">供应链/进销存SCM</div>
							</li>
							<li class="ie10">
								<div class="text">财务（全部功能模块）</div>
							</li>
							<li class="ie10">
								<div class="text">企业资源计划ERP</div>
							</li>
							<li>
								<div class="text">其他个性化软件</div>
							</li>
						</ul>
					</div>
				</div>
			</el-col>
			<el-col :span="12">
				<div class="item clearfix">
					<div class="img">
						<img src="/static/img/front/10.png" alt="APP">
					</div>
					<div class="item-text">
						<div class="name">手机APP</div>
						<ul class="detail">
							<li>
								<div class="text">企业内部管理APP（零代码快速开发）</div>
							</li>
							<li>
								<div class="text">企业外部客户使用的消费端APP（零代码快速开发）</div>
							</li>
						</ul>
					</div>
				</div>
			</el-col>
		</el-row>
		<el-row :gutter="20">
			<el-col :span="12">
				<div class="item clearfix">
					<div class="img">
						<img src="/static/img/front/11.png" alt="APP">
					</div>
					<div class="item-text">
						<div class="name">微信小程序/公众号后台</div>
						<ul class="detail">
							<li>
								<div class="text">通过企业微信进行流程审批、公告和提醒接收</div>
							</li>
							<li>
								<div class="text">企业自己的微信公众号、小程序后台数据库及管理</div>
								<div class="text">控制台</div>
							</li>
							<li>
								<div class="text">微信公众号、小程序产生的数据与企业内部管理软</div>
								<div class="text">件自动对接引用</div>
							</li>
						</ul>
					</div>
				</div>
			</el-col>
			<el-col :span="12">
				<div class="item clearfix">
					<div class="img">
						<img src="/static/img/front/12.png" alt="APP">
					</div>
					<div class="item-text">
						<div class="name">企业网站</div>
						<ul class="detail">
							<li>
								<div class="text">可灵活配置及调整的网站后台数据库及管理控制台</div>
							</li>
							<li>
								<div class="text">网站产生的数据与企业内部管理软件自动对接引用</div>
							</li>
						</ul>
					</div>
				</div>
			</el-col>
		</el-row>
		<el-row :gutter="20">
			<el-col :span="12">
				<div class="item clearfix">
					<div class="img">
						<img src="/static/img/front/12-5.png" alt="APP">
					</div>
					<div class="item-text">
						<div class="name">多系统整合平台</div>
						<ul class="detail">
							<li>
								<div class="text">可作为多系统整合的中心数据库及中心系统</div>
							</li>
							<li>
								<div class="text">可作为多系统的单一用户认证中心</div>
							</li>
							<li>
								<div class="text">可动态适应其它系统数据格式，动态创建数据表，</div>
								<div class="text">统一接口读写</div>
							</li>
						</ul>
					</div>
				</div>
			</el-col>
			<el-col :span="12">
				<div class="item clearfix">
					<div class="img">
						<img src="/static/img/front/13.png" alt="APP">
					</div>
					<div class="item-text">
						<div class="name">行业和垂直领域SaaS平台</div>
						<ul class="detail">
							<li>
								<div class="text">快速零代码搭建和部署行业或垂直领域的个性化
								</div>
								<div class="text">SaaS平台（含网站及手机端APP），如：各行业的
								</div>
								<div class="text">中介服务、交易撮合平台
								</div>
							</li>
						</ul>
					</div>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		data() {
			return {

			};
		}
	}
</script>

<style lang="scss">
	.index-how-content {
		display: block;
		max-width: 1200px;
		margin: 30px auto 40px;

		.item {
			width: 588px;
			min-height: 270px;
			border: 1px solid #d4d4d4;
			margin-bottom: 20px;
			box-sizing: content-box;

			.img {
				float: left;
				padding: 0 10px;
				min-height: 270px;
			}

			.img>img {
				width: 90px;
				position: relative;
				top: 50px;
			}

			.item-text {
				.name {
					font-size: 21px;
					margin: 35px 0 25px 0px;
					padding-left: 8px;
					overflow: hidden;
					font-weight: bold;
				}

				.detail li {
					float: left;
					list-style-type: disc;
					color: #0ad0fd;
					min-width: 33%;
					margin-left: 25px;
				}

				.detail .text {
					font-size: 16px;
					margin-bottom: 15px;
					color: #000000;
				}
			}
		}
	}
</style>
