<template>
	<div id="fh5co-explore" class="fh5co-bg-section">
		<div class="container">
			<div class="row animate-box fadeInUp animated-fast">
				<div class="col-md-12 text-center fh5co-heading">
					<h2>为什么选择JavaFast</h2>
					<p>JavaFast是一款基于代码生成器的智能快速开发平台，基于代码生成器的开发方式，可以将编码工作量减少80%以上，为企业提高开发效率，为企业节省项目研发成本，减少开发周期。
						JavaFast基于流行的JAVA开源技术上构建，扩展容易，学习成本低，可快速构建企业级项目，即满足企业项目实现快速开发的需求。 </p>
				</div>
			</div>
		</div>
		<div class="fh5co-explore fh5co-explore1">
			<div class="container">
				<el-row :gutter="20">
					<el-col :xs="12" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="mt">
							<div>
								<h4><i class="course-icon-user" style="font-size: 25px;"></i>基础架构</h4>
								<p>采用 SpringMVC + MyBatis + BootStrap + Apache Shiro + Ehcache 基础架构. </p>
							</div>
							<div>
								<h4><i class="course-icon-video2" style="font-size: 25px;"></i>轻标准化</h4>
								<p>简单功能由代码生成器生成使用，复杂业务采用表单自定义，只需要写极少代码，即可实现复杂的业务逻辑. </p>
							</div>
							<div>
								<h4><i class="course-icon-shield" style="font-size: 25px;"></i>高效稳定</h4>
								<p>经过了专业压力测试，百亿级别数据性能测试，保证后台数据的准确性和页面访问速度和稳定性. </p>
							</div>
						</div>
					</el-col>
					<el-col :xs="12" :sm="16" :md="16" :lg="16" :xl="16">
						<img class="img-responsive left-space" src="/static/img/front2/work_2.png" alt="work">
					</el-col>
				</el-row>
			</div>
		</div>

		<div class="fh5co-explore">
			<div class="container">
				<el-row :gutter="20">
					<el-col :xs="12" :sm="16" :md="16" :lg="16" :xl="16">
						<img class="img-responsive" style="margin-left: -100px;" src="/static/img/front2/work_3.png" alt="work">
					</el-col>
					<el-col :xs="12" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="mt" style="padding-left: 10%;">
							<h3>代码生成</h3>
							<p>代码生成，支持多种数据模型,根据表生成对应的Entity,Service,Dao,Action,JSP等,增删改查/排序/导出导入Excel/权限控制/功能生成直接使用 .</p>
							<ul class="list-nav">
								<li><i class="el-icon-check"></i>生成前端代码</li>
								<li><i class="el-icon-check"></i>生成后端代码</li>
								<li><i class="el-icon-check"></i>生成高性能SQL</li>
							</ul>
							<p><a class="btn btn-primary btn-lg popup-vimeo btn-video" href="http://demo.javafast.cn" target="_blank">
              <i class="course-icon-play" style="font-size: 34px;"></i> 查看演示</a></p>
						</div>
					</el-col>
				</el-row>
			</div>
		</div>
		<br><br><br><br>
		<div class="fh5co-explore fh5co-explore1">
			<div class="container">
				<el-row :gutter="20">
					<el-col :xs="12" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="mt">
							<h3>多种UI支持</h3>
							<p>系统支持多种风格的自动切换，可满足企业对UI的美观要求 .</p>
							<ul class="list-nav">
								<li><i class="el-icon-check"></i>Bootstrap前端框架</li>
								<li><i class="el-icon-check"></i>支持手机或平板</li>
								<li><i class="el-icon-check"></i>集成企业微信</li>
							</ul>
							<p><a class="btn btn-primary btn-lg popup-vimeo btn-video" href="http://demo.javafast.cn" target="_blank">
              <i class="course-icon-play" style="font-size: 34px;"></i> 查看演示</a></p>
						</div>
					</el-col>
					<el-col :xs="12" :sm="16" :md="16" :lg="16" :xl="16">
						<img class="img-responsive left-space" src="/static/img/front2/work_5.png" alt="work">
					</el-col>
				</el-row>
			</div>
		</div>

		<div class="fh5co-explore">
			<div class="container">
				<el-row :gutter="20">
					<el-col :xs="12" :sm="16" :md="16" :lg="16" :xl="16">
						<img class="img-responsive" style="margin-left: -100px;" src="/static/img/front2/work_6.png" alt="work">
					</el-col>
					<el-col :xs="12" :sm="8" :md="8" :lg="8" :xl="8">
						<div class="mt" style="padding-left: 10%;">
							<h3>简易报表配置</h3>
							<p>支持报表在线配置，通过简单配置SQL语句即可实现图形报表展示.</p>

							<ul class="list-nav">
								<li><i class="el-icon-check"></i>SQL配置</li>
								<li><i class="el-icon-check"></i>图形报表</li>
								<li><i class="el-icon-check"></i>数据列表</li>
							</ul>
							<p><a class="btn btn-primary btn-lg popup-vimeo btn-video" href="http://demo.javafast.cn" target="_blank">
              <i class="course-icon-play" style="font-size: 34px;"></i> 查看演示</a></p>
						</div>

					</el-col>
				</el-row>
			</div>
		</div>


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
	.fh5co-bg-section {
		background: rgba(0, 0, 0, 0.05);

		.container {
			width: 1200px;
			margin: 0 auto;
			padding: 0;
		}

		.row {
			margin-left: -15px;
			margin-right: -15px;
		}

		.fadeInUp {
			-webkit-animation-name: fadeInUp;
			animation-name: fadeInUp;
		}

		.animated-fast {
			-webkit-animation-duration: .5s;
			animation-duration: .5s;
			-webkit-animation-fill-mode: both;
			animation-fill-mode: both;
		}

		.fh5co-heading {
			margin-bottom: 5em;
		}

		.col-md-12 {
			width: 100%;
		}

		.col-md-12 {
			float: left;
		}

		.col-md-12,
		.col-lg-12 {
			position: relative;
			min-height: 1px;
			padding-left: 15px;
			padding-right: 15px;
		}

		.text-center {
			text-align: center;
		}

		.fh5co-heading h2 {
			font-size: 32px;
			margin-bottom: 20px;
			line-height: 1.5;
			color: #000;
			font-weight: 400;
		}

		.fh5co-heading p {
			font-size: 18px;
			line-height: 1.5;
			color: #828282;
		}

		.img-responsive {
			display: block;
			max-width: 100%;
			height: auto;
			// padding-left: 10%;
		}

		.left-space {
			padding-left: 15%;
		}

		img {
			vertical-align: middle;
		}

		.mt {
			margin-top: 70px;
		}

		.mt>div {
			margin-bottom: 40px;
		}

		.mt h4 {
			position: relative;
			padding-left: 40px;
			font-size: 20px;
			font-weight: 400;
		}

		.mt h4 i {
			position: absolute;
			left: 0;
			top: 0;
			color: #EA9215;
		}

		.mt .list-nav {
			margin: 50px 0 30px 0;
			padding: 0;
		}

		.mt .list-nav li {
			list-style: none;
			margin: 0;
			padding: 0;
			font-size: 16px;
			padding-left: 30px;
			margin-bottom: 10px;
			position: relative;
		}

		.mt .list-nav li i {
			position: absolute;
			left: 0;
			top: 0;
			font-size: 18px;
			color: #EA9215;
		}

		.btn.btn-lg {
			padding: 18px 36px !important;
		}

		.btn-primary {
			background: #EA9215;
			color: #fff;
			border: 2px solid #EA9215;
		}

		.btn {
			margin-right: 4px;
			margin-bottom: 4px;
			font-family: "Work Sans", Arial, sans-serif;
			font-size: 16px;
			font-weight: 400;
			-webkit-border-radius: 30px;
			-moz-border-radius: 30px;
			-ms-border-radius: 30px;
			border-radius: 30px;
			-webkit-transition: 0.5s;
			-o-transition: 0.5s;
			transition: 0.5s;
			padding: 8px 20px;
		}

		h1,
		h2,
		h3,
		h4,
		h5,
		h6 {
			font-weight: 400;
		}

		h1,
		h2,
		h3,
		h4,
		h5,
		h6,
		figure {
			color: #000;
			font-family: "Work Sans", Arial, sans-serif;
			font-weight: 400;
			margin: 0 0 20px 0;
		}

		h3,
		.h3 {
			font-size: 24px;
		}

		h1,
		h2,
		h3,
		h4,
		h5,
		h6,
		.h1,
		.h2,
		.h3,
		.h4,
		.h5,
		.h6 {
			font-family: inherit;
			font-weight: 500;
			line-height: 1.1;
			color: inherit;
		}

		.mt p {
			font-family: "Microsoft Yahei", "HanHei SC", PingHei, "PingFang SC", "Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
			font-size: 14px;
		}
	}

	.fh5co-explore {
		clear: both;
	}

	#fh5co-explore {
		padding: 7em 0;
		clear: both;

		.fh5co-explore1 {
			margin-bottom: 7em;
		}
	}
</style>
